package pers.zymir.logs.core;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import pers.zymir.logs.core.anno.LogField;
import pers.zymir.logs.core.convert.LogFieldContentConvertor;
import pers.zymir.logs.core.convert.LogFieldContentConvertorHolder;
import pers.zymir.logs.core.convert.VoidLogFieldContentConvertor;
import pers.zymir.logs.core.entity.FieldContentMeta;

import java.lang.reflect.Field;
import java.util.*;

public class LogContentUtil {
    public static Map<String, FieldContentMeta> beanToMap(Object bean) {
        if (Objects.isNull(bean)) {
            return new HashMap<>();
        }

        Map<String, FieldContentMeta> fieldMap = new HashMap<>();
        Field[] fields = ReflectUtil.getFields(bean.getClass());
        for (Field field : fields) {
            LogField logField = field.getAnnotation(LogField.class);
            if (Objects.isNull(logField) || StrUtil.isBlank(logField.name())) {
                fieldMap.put(field.getName(), new FieldContentMeta(ReflectUtil.getFieldValue(bean, field), null));
                continue;
            }
            fieldMap.put(logField.name(), new FieldContentMeta(ReflectUtil.getFieldValue(bean, field), logField));
        }

        return fieldMap;
    }

    public static List<String> buildContent(Map<String, FieldContentMeta> oldFieldMap, Map<String, FieldContentMeta> newFieldMap) {
        List<String> contents = new ArrayList<>();
        oldFieldMap.forEach((name, oldFieldMeta) -> {
            FieldContentMeta newFieldMeta = newFieldMap.get(name);
            String content = buildChangeValue(name, oldFieldMeta, newFieldMeta);
            if (StrUtil.isNotBlank(content)) {
                contents.add(content);
            }
        });

        return contents;
    }

    public static String buildChangeValue(String fieldName, FieldContentMeta oldValue, FieldContentMeta newValue) {
        String oldValueString = convert2String(oldValue);
        String newValueString = convert2String(newValue);
        if (StrUtil.equals(oldValueString, newValueString)) {
            return "";
        }
        return "将" + fieldName + "从 [" + oldValueString + "] 修改为：[" + newValueString + "]";
    }

    private static String convert2String(FieldContentMeta fieldContentMeta) {
        Object fieldValue = fieldContentMeta.getFieldValue();
        LogField logField = fieldContentMeta.getLogField();
        boolean customLogFiled = Objects.nonNull(logField);
        if (customLogFiled) {
            String emptyContent = logField.emptyContent();
            if (Objects.isNull(fieldValue)) {
                return emptyContent;
            }
            if (isCustomFiledContentConvert(logField)) {
                LogFieldContentConvertor convertor = SpringUtil.getBean(logField.contentConvertor());
                return convertor.convert(fieldValue, emptyContent);
            }
        }

        // 尝试按照类型查找系统内置的转换器
        LogFieldContentConvertor systemDefaultConvertor = LogFieldContentConvertorHolder.getByFieldClass(fieldValue.getClass());
        return Objects.isNull(systemDefaultConvertor) ? fieldValue.toString() : systemDefaultConvertor.convert(fieldValue, "空");
    }

    private static boolean isCustomFiledContentConvert(LogField logField) {
        return Objects.nonNull(logField.contentConvertor()) && logField.contentConvertor() != VoidLogFieldContentConvertor.class;
    }
}