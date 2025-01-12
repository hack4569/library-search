package com.library.controller.response;

import com.library.exception.ErrorType;

public record ErrorResponse (String errorMsg, ErrorType errorType) {
}
