package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrelloServiceTest {


    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloBoardDto> expectedTrelloBoards = List.of(new TrelloBoardDto("boardId", "boardName", List.of()));

        when(trelloClient.getTrelloBoards()).thenReturn(expectedTrelloBoards);

        // When
        List<TrelloBoardDto> actualTrelloBoards = trelloService.fetchTrelloBoards();

        // Then
        assertNotNull(actualTrelloBoards);
        assertEquals(expectedTrelloBoards, actualTrelloBoards);
    }

    @Test
    void shouldCreateTrelloCardAndSendEmail() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("cardName", "cardDescription", "top", "listId");
        CreatedTrelloCardDto expectedCreatedTrelloCardDto = new CreatedTrelloCardDto("cardId", "cardName", "cardUrl", null);

        when(trelloClient.createNewCard(any(TrelloCardDto.class))).thenReturn(expectedCreatedTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        // When
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertNotNull(createdCard);
        assertEquals(expectedCreatedTrelloCardDto, createdCard);

        verify(emailService, times(1)).send(any(Mail.class));
    }
}