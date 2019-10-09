package com.jvmless.threecardgame.handlers;

import com.jvmless.threecardgame.domain.game.Card;
import com.jvmless.threecardgame.domain.game.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayGameCommand {
    private String hostId;
    private List<Card> cards;
}
