package com.trungtv.blogauth.exception;

import com.trungtv.blogauth.util.MessageUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Supplier;

@Data
public class EntityNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String message;

    public EntityNotFoundException(Class<?> entity, Serializable id) {
        this.message = MessageUtils.getMessage("not.found.entity.by.id", entity.getSimpleName(), id);
    }

    public static Supplier<EntityNotFoundException> throwException(Class<?> clazz, Serializable id) {
        return () -> new EntityNotFoundException(clazz, id);
    }

}
