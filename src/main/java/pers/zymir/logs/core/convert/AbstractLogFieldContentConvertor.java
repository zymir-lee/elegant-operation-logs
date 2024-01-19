package pers.zymir.logs.core.convert;

import java.util.Objects;

public abstract class AbstractLogFieldContentConvertor implements LogFieldContentConvertor {
    @Override
    public String convert(Object object, String defaultValue) {
        if (Objects.isNull(object) || !getType().isInstance(object)) {
            return defaultValue;
        }
        return doConvert(object);
    }

    protected abstract String doConvert(Object object);
}
