package com.cinevault.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "The streaming service name is required.") String name) {
}
