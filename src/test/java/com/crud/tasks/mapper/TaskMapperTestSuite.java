package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void testMapToTask() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertNotNull(task);
        assertEquals(1L, task.getId());
        assertEquals("Test Title", task.getTitle());
        assertEquals("Test Content", task.getContent());
    }

    @Test
    void testMapToTaskDto() {
        // Given
        Task task = new Task(1L, "Test Title", "Test Content");

        // When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertNotNull(taskDto);
        assertEquals(1L, taskDto.getId());
        assertEquals("Test Title", taskDto.getTitle());
        assertEquals("Test Content", taskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        // Given
        Task task1 = new Task(1L, "Test Title 1", "Test Content 1");
        Task task2 = new Task(2L, "Test Title 2", "Test Content 2");
        List<Task> taskList = List.of(task1, task2);

        // When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());

        TaskDto taskDto1 = taskDtoList.get(0);
        assertEquals(1L, taskDto1.getId());
        assertEquals("Test Title 1", taskDto1.getTitle());
        assertEquals("Test Content 1", taskDto1.getContent());

        TaskDto taskDto2 = taskDtoList.get(1);
        assertEquals(2L, taskDto2.getId());
        assertEquals("Test Title 2", taskDto2.getTitle());
        assertEquals("Test Content 2", taskDto2.getContent());
    }

}
