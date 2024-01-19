package pers.zymir.logs.core.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FieldLogConvertorHolder {

    public static final Map<Class<?>, FieldLogConvertor> FIELD_LOG_CONVERTOR_MAP = new HashMap<>();

    public static FieldLogConvertor getByFieldClass(Class<?> type) {
        return FIELD_LOG_CONVERTOR_MAP.get(type);
    }

    @Autowired
    public void init(List<FieldLogConvertor> fieldLogConvertors) {
        fieldLogConvertors.forEach(convertor -> FIELD_LOG_CONVERTOR_MAP.put(convertor.getType(), convertor));
    }
}
