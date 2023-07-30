package com.bestbank.clientes.application.utils;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectValidator<T> {

    private Validator validator;

    public ObjectValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public Boolean validateObject(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        
        violations.forEach(t -> 
          log.error("error :" + t.getMessage())
        );
        
        return violations.isEmpty();
    }

    // You can add more utility methods here if needed
}