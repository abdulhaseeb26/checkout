package com.ecommerce.challenge.service.exception;

import com.ecommerce.challenge.error.ErrorEnumType;

public class ServiceException extends BaseApplicationException {
    public ServiceException(ErrorEnumType<? extends Enum<?>> errorEnumType) {

        super(errorEnumType);
    }

}

