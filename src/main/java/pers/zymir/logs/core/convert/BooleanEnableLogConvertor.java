package pers.zymir.logs.core.convert;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BooleanEnableLogConvertor implements FieldLogConvertor {
    @Override
    public String convert(Object object, String defaultValue) {
        if (Objects.isNull(object) || !getType().isInstance(object)) {
            return defaultValue;
        }
        return (boolean) object ? "开启" : "关闭";
    }

    @Override
    public Class<?> getType() {
        return Boolean.class;
    }
}
