package com.cinevault.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "The category name is required.") String name) {
}
