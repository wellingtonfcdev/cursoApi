package com.br.wellingtondev.api_.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandartError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;
}
