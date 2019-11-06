package com.jvmless.threecardgame.infra.domain.moves;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table("moves")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CassandraGameMoves {
    @PrimaryKeyColumn(
            name = "movesId",
            ordinal = 2,
            type = PrimaryKeyType.PARTITIONED,
            ordering = Ordering.DESCENDING)
    private String movesId;

    @PrimaryKeyColumn(
            name = "gameId",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private String gameId;
    private int current;
    private int previous;
    private LocalDateTime moveTime;
}
