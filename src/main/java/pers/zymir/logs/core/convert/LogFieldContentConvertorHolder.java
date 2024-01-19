package pers.zymir.logs.core.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LogFieldContentConvertorHolder {

    public static final Map<Class<?>, LogFieldContentConvertor> FIELD_LOG_CONTENT_CONVERTOR_MAP = new HashMap<>();

    public static LogFieldContentConvertor getByFieldClass(Class<?> type) {
        return FIELD_LOG_CONTENT_CONVERTOR_MAP.get(type);
    }

    @Autowired
    public void init(List<LogFieldContentConvertor> logFieldContentConvertors) {
        logFieldContentConvertors.forEach(convertor -> FIELD_LOG_CONTENT_CONVERTOR_MAP.put(convertor.getType(), convertor));
    }
}
