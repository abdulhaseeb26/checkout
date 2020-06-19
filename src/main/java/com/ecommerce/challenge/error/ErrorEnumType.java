package com.ecommerce.challenge.error;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface ErrorEnumType<E extends Enum<E>> extends IntegerEnumType<E>, Serializable {
    String getAppCode();

    String getAppMessage();

    default HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    default String getExceptionMessage() {
        return String.format("[%s] %s", this.getAppCode(), this.getAppMessage());
    }
}
