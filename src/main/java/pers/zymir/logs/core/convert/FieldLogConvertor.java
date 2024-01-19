package pers.zymir.logs.core.convert;


public interface FieldLogConvertor {
    String convert(Object object, String defaultValue);

    Class<?> getType();
}
