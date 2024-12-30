package com.practice.blogging_app.common.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class ErrorResponseDTO {
	private String message;
	private String details;
}
