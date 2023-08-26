package com.github.jomardev25.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedArticleResponseDto {

	@JsonProperty("page_size")
    private int pageSize;

	@JsonProperty("page_number")
	private int pageNumber;

	@JsonProperty("total_pages")
	private int totalPages;

	@JsonProperty("total_elements")
	private long totalElements;
	private boolean first;
	private boolean last;
	private List<ArticleResponseDto> articles;
}
