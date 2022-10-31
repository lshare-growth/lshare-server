package com.example.backend.common.configuration.common.page;

import com.example.backend.business.core.common.values.Cursor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class CursoPagerArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String CURSOR_TARGET = "cursorTarget";
    private static final String PAGE_SIZE = "size";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CursorPageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        String cursorTarget = webRequest.getParameter(CURSOR_TARGET);
        String pageSize = webRequest.getParameter(PAGE_SIZE);

        CursorPageable cursorPageable = parameter.getParameterAnnotation(CursorPageable.class);

        return Cursor.from(parseLong(cursorTarget), getPageSize(pageSize, cursorPageable));
    }

    private Long parseLong(String value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Long.parseLong(value);
    }

    private int getPageSize(String pageSize, CursorPageable cursorPageable) {
        if (Objects.isNull(pageSize)) {
            return cursorPageable.getDefaultPageSize();
        }

        Integer parsedPageSzie = Integer.parseInt(pageSize);

        if (parsedPageSzie > 5) {
            return 5;
        }
        return Integer.parseInt(pageSize);
    }
}
