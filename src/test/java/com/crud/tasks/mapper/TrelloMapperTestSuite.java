package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void testMapToBoards() {
        // Given
        List<TrelloListDto> trelloListDtoList = Arrays.asList(
                new TrelloListDto("1", "list1", false),
                new TrelloListDto("2", "list2", false)
        );

        List<TrelloListDto> trelloListDtoList2 = Arrays.asList(
                new TrelloListDto("3", "list3", false),
                new TrelloListDto("4", "list4", false)
        );

        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(
                new TrelloBoardDto("1", "board1", trelloListDtoList),
                new TrelloBoardDto("2", "board2", trelloListDtoList2)
        );


        // When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        // Then
        assertEquals(2, trelloBoardList.size());
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals("board1", trelloBoardList.get(0).getName());
        assertEquals(2, trelloBoardList.get(0).getLists().size());
        assertEquals("list1", trelloBoardList.get(0).getLists().get(0).getName());
    }

    @Test
    void testMapToBoardsDto() {
        // Given
        List<TrelloList> trelloLists1 = Arrays.asList(
                new TrelloList("1", "list1", false),
                new TrelloList("2", "list2", false)
        );
        List<TrelloList> trelloLists2 = Arrays.asList(
                new TrelloList("3", "list3", false),
                new TrelloList("4", "list4", false)
        );
        List<TrelloBoard> trelloBoardList = Arrays.asList(
                new TrelloBoard("1", "board1", trelloLists1),
                new TrelloBoard("2", "board2", trelloLists2)
        );

        // When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        // Then
        assertEquals(2, trelloBoardDtoList.size());
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("board1", trelloBoardDtoList.get(0).getName());
        assertEquals(2, trelloBoardDtoList.get(0).getLists().size());
        assertEquals("list1", trelloBoardDtoList.get(0).getLists().get(0).getName());
    }

    @Test
    void testMapToList() {
        // Given
        List<TrelloListDto> trelloListDtoList = Arrays.asList(
                new TrelloListDto("1", "list1", false),
                new TrelloListDto("2", "list2", false)
        );

        // When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDtoList);

        // Then
        assertEquals(2, trelloList.size());
        assertEquals("1", trelloList.get(0).getId());
        assertEquals("list1", trelloList.get(0).getName());
        assertFalse(trelloList.get(0).isClosed());
    }

    @Test
    void testMapToListDto() {
        // Given
        List<TrelloList> trelloList = Arrays.asList(
                new TrelloList("1", "list1", false),
                new TrelloList("2", "list2", false)
        );

        // When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloList);

        // Then
        assertEquals(2, trelloListDtoList.size());
        assertEquals("1", trelloListDtoList.get(0).getId());
        assertEquals("list1", trelloListDtoList.get(0).getName());
        assertFalse(trelloListDtoList.get(0).isClosed());
    }

    @Test
    void testMapToCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("card1", "desc1", "top", "1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertNotNull(trelloCardDto);
        assertEquals("card1", trelloCardDto.getName());
        assertEquals("desc1", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void testMapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "desc1", "top", "1");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertNotNull(trelloCard);
        assertEquals("card1", trelloCard.getName());
        assertEquals("desc1", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}