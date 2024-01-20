package com.bezkoder.spring.jwt.mongodb.payload.request;

import jakarta.validation.constraints.NotBlank;

public class DeleteRequest {
	@NotBlank
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}