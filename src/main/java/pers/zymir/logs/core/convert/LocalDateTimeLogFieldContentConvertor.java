package pers.zymir.logs.core.convert;

import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalDateTimeLogFieldContentConvertor extends AbstractLogFieldContentConvertor {
    @Override
    protected String doConvert(Object object) {
        return DateUtil.formatLocalDateTime((LocalDateTime) object);
    }

    @Override
    public Class<?> getType() {
        return LocalDateTime.class;
    }
}
