package com.example.backend.common.exception;

import java.util.ArrayList;
import java.util.List;

public class FieldError extends Throwable {

    private final String field;
    private final Object value;

    public FieldError(String field, Object value, String message) {
        this.field = field;
        this.value = value;
    }

    public FieldError(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public FieldError(org.springframework.validation.FieldError fieldError) {
        this.field = fieldError.getField();
        this.value = fieldError.getRejectedValue();
    }

    public static List<FieldError> of(final String field, final String value, final String message) {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError(field, value, message));
        return fieldErrors;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", field, value);
    }
}
