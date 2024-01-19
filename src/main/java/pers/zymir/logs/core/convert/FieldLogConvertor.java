package pers.zymir.logs.core.convert;


// TODO 抽象出模版类
public interface FieldLogConvertor {
    String convert(Object object, String defaultValue);

    Class<?> getType();
}
