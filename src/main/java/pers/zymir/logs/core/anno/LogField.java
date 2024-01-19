package pers.zymir.logs.core.anno;

import pers.zymir.logs.core.convert.FieldLogConvertor;
import pers.zymir.logs.core.convert.VoidFieldLogConvertor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogField {
    String name() default "";

    String emptyContent() default "空";

    Class<? extends FieldLogConvertor> contentConvertor() default VoidFieldLogConvertor.class;
}
