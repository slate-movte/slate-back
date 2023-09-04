package com.movte.slate.global.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FieldError {

    private String field;
    private String value;
    private String reason;

    private FieldError(final String field, final String value, final String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    public static List<FieldError> of(final String field, final String value,
        final String reason) {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError(field, value, reason));
        return fieldErrors;
    }

    public static List<FieldError> of(final BindingResult bindingResult) {
        final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream()
            .map(error -> new FieldError(
                error.getField(),
                error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                error.getDefaultMessage()))
            .collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "FieldError{" +
            "field='" + field + '\'' +
            ", value='" + value + '\'' +
            ", reason='" + reason + '\'' +
            '}';
    }
}
