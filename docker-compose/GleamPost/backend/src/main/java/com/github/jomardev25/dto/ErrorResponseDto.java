package com.github.jomardev25.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private Date timestamp;
	private String message;
	private String details;
}
