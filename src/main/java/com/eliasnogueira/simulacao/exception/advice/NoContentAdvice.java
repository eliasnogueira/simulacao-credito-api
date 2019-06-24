package com.eliasnogueira.simulacao.exception.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@ControllerAdvice
public class NoContentAdvice implements ResponseBodyAdvice<List<?>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return List.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public List<?> beforeBodyWrite(List<?> body, MethodParameter returnType, MediaType selectedContentType,
                                   Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                   ServerHttpRequest request, ServerHttpResponse response) {
        if (body.isEmpty()) {
            response.setStatusCode(HttpStatus.NO_CONTENT);
        }
        return body;
    }
}