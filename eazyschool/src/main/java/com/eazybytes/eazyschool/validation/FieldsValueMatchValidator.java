package com.eazybytes.eazyschool.validation;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    String field;
    String matchingField;

   public void initialize(FieldsValueMatch fieldsValueMatch){
      field= fieldsValueMatch.field();
      matchingField=fieldsValueMatch.matchingField();
   }

   public boolean isValid(Object value, ConstraintValidatorContext context){
       Object fieldValue=new BeanWrapperImpl(value).getPropertyValue(field);
       Object matchingFieldValue=new BeanWrapperImpl(value).getPropertyValue(matchingField);

       if (fieldValue!=null){
           return fieldValue.equals(matchingFieldValue);
       }else{
           return matchingFieldValue==null;
       }
   }
}
