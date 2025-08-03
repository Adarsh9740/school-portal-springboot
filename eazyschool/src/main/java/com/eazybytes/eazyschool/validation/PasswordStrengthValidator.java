package com.eazybytes.eazyschool.validation;

import com.eazybytes.eazyschool.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

   List<String> weekPwd;
   @Override
    public void initialize(PasswordValidator passwordValidator){
        weekPwd= Arrays.asList("12345","qwerty","password");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return value!=null && !weekPwd.contains(value);
    }
}
