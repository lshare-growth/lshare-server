package com.example.backend.common.configuration.common.date;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class DateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return DateTimeFormatter.ISO_DATE.format(object);
    }
}
