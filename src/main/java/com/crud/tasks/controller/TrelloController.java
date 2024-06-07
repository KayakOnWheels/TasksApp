package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloFacade trelloFacade;

@GetMapping("boards")
public void getTrelloBoards() {
    List<TrelloBoardDto> trelloBoards = trelloFacade.fetchTrelloBoards();

    trelloBoards.forEach(trelloBoardDto -> {
        System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
        System.out.println("This board contains lists: ");
        trelloBoardDto.getLists().forEach(trelloList -> {
            System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed());
        });
    });
}

    @PostMapping("cards")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
}