package kszorin.internetbank.validation;

import kszorin.internetbank.models.Bill;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by kszorin on 13.02.2017.
 */
public class BillValidation implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Bill.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sum", "Empty textbox!");
    }
}
