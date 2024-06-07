package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exceptions.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void shouldGetAllTasks() {
        // Given
        List<Task> expectedTasks = List.of(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(expectedTasks);

        // When
        List<Task> actualTasks = dbService.getAllTasks();

        // Then
        assertNotNull(actualTasks);
        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void shouldGetTaskById() throws TaskNotFoundException{
        // Given
        long taskId = 1L;
        Task expectedTask = new Task(taskId, "Test Task", "Test Content");
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(expectedTask));

        // When
        Task actualTask = dbService.getTask(taskId);

        // Then
        assertNotNull(actualTask);
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        // Given
        long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(taskId));
    }

    @Test
    void shouldSaveTask() {
        // Given
        Task taskToSave = new Task(123L, "Test Content", "content");
        when(taskRepository.save(any(Task.class))).thenReturn(taskToSave);

        // When
        Task savedTask = dbService.saveTask(taskToSave);

        // Then
        assertNotNull(savedTask);
        assertEquals(taskToSave, savedTask);
    }

    @Test
    void shouldDeleteTask() {
        // Given
        long taskId = 1L;

        // When
        dbService.deleteTask(taskId);

        // Then
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}