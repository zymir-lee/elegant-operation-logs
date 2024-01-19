package pers.zymir.logs.core.convert;

public interface LogFieldContentConvertor {
    String convert(Object object, String defaultValue);

    Class<?> getType();
}
