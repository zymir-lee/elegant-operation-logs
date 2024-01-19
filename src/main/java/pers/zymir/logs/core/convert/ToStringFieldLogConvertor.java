package pers.zymir.logs.core.convert;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ToStringFieldLogConvertor implements FieldLogConvertor {

    @Override
    public String convert(Object object, String defaultValue) {
        if (Objects.isNull(object) || !getType().isInstance(object)) {
            return defaultValue;
        }
        return object.toString();
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
