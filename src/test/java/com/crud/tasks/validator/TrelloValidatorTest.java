package com.crud.tasks.validator;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void validateCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1", "desc1", "top", "123");
        TrelloCard trelloCard2 = new TrelloCard("test", "desc1", "top", "123");

        //When&Then
        trelloValidator.validateCard(trelloCard);
        trelloValidator.validateCard(trelloCard2);

    }

    @Test
    void validateTrelloBoardsTest() {
        //Given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(new TrelloBoard("1", "test", trelloLists),
                        new TrelloBoard("1", "not a test", trelloLists));


        //When
        List<TrelloBoard> filteredTrelloBoardList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assertions.assertEquals(1, filteredTrelloBoardList.size());
    }
}