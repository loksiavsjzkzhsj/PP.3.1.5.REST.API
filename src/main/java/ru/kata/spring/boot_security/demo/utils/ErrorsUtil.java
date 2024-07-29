package ru.kata.spring.boot_security.demo.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.kata.spring.boot_security.demo.exception_handling.UserNotCreatedException;

import java.util.List;

public class ErrorsUtil {
    public static void returnError(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError errors : fieldErrors) {
            errorMessage.append(errors.getField())
                    .append(": ")
                    .append(errors.getDefaultMessage())
                    .append("; ");
        }
        throw new UserNotCreatedException(errorMessage.toString());
    }
}

