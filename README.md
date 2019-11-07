`
create table moves
(
    moveId TEXT,
    movesId TEXT,
    gameId TEXT,
    current int,
    previous int,
    moveTime TIMESTAMP,
    primary key((gameId), moveId, movesId)
);
`