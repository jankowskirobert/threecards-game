package com.jvmless.threecardgame.handlers.commands.dto;

import com.jvmless.threecardgame.domain.shuffle.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayGameCommand {
    private String hostId;
    private List<CardView> cards;
}
