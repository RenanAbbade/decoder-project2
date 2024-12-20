package com.ead.authuser.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //para ser exibida no javadoc
@Constraint(validatedBy = PasswordConstraintImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})//Elabora onde essa annotation será utilizada, no caso em nivel de metodo e campo
@Retention(RetentionPolicy.RUNTIME) //Validação ocorrerá em tempo de execução
public @interface PasswordConstraint {

    String message() default """
           Invalid password! Password must contain at least one digit [0-9],
           at least one lowercase Latin character [a-z], at least one uppercase
           Latin character [A-Z], at least one special character like !@#&()-:;',?/*~$^+=<>,
           a length at least 6 characters and a maximum of 20 characters
           """;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
