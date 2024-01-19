package pers.zymir.logs.core.convert;

import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class LocalDateTimeFieldLogConvertor implements FieldLogConvertor {

    @Override
    public String convert(Object object, String defaultValue) {
        if (Objects.isNull(object) || !getType().isInstance(object)) {
            return defaultValue;
        }
        return DateUtil.formatLocalDateTime((LocalDateTime) object);
    }

    @Override
    public Class<?> getType() {
        return LocalDateTime.class;
    }
}
