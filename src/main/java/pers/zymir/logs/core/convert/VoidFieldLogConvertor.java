package pers.zymir.logs.core.convert;

import org.springframework.stereotype.Component;

@Component
public class VoidFieldLogConvertor implements FieldLogConvertor {
    @Override
    public String convert(Object object, String defaultValue) {
        return "";
    }

    @Override
    public Class<?> getType() {
        return void.class;
    }
}
