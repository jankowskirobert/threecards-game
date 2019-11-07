package com.jvmless.threecardgame.handlers.commands.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CardView {
    private int position;
    private boolean type;
}
