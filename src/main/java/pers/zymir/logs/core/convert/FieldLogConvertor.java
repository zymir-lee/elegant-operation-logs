package pers.zymir.logs.core.convert;

@FunctionalInterface
public interface FieldLogConvertor {
    String convert(Object object);
}
