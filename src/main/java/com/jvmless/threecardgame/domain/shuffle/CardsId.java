package com.jvmless.threecardgame.domain.shuffle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CardsId {
    private String id;
}
