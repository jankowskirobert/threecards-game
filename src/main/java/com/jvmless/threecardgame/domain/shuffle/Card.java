package com.jvmless.threecardgame.domain.shuffle;

import com.jvmless.threecardgame.domain.shared.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "position")
@Data
@AllArgsConstructor
public class Card {
    private Position position;
    private CardType cardType;
}
