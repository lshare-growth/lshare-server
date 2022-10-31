package com.example.backend.common.exception;

public class ErrorField extends RuntimeException {

    private final String fieldName;
    private final Object value;

    private ErrorField(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = convertValue(value);
    }

    private Object convertValue(Object value) {
        if (value == null) {
            return "Null";
        }
        if (value == "")
            return "''";
        return value;
    }

    public static ErrorField of(String fieldName, Object value) {
        return new ErrorField(fieldName, value);
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("field: %s, value: %s", fieldName, value);
    }
}
