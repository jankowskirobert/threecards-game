package com.jvmless.threecardgame.infra.domain.moves;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CassandraGameMove {
    private int current;
    private int previous;
    private LocalDateTime moveTime;
}
