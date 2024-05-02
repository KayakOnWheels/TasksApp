package com.crud.tasks.domain;

import lombok.Data;

@Data
public class TrelloBadgeDto {

    private int votes;
    private TrelloAttachmentByType attachment;
}
