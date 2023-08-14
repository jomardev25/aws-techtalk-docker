package com.github.jomardev25.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserListResponseDto {
    private int page;
	private int perPage;
	private int totalPages;
	private long totalRecord;
	private boolean first;
	private boolean last;
    private List<UserResponseDto> data;
}
