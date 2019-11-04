package com.jvmless.threecardgame.domain.shuffle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class GameMovesId {
    private String id;
}
