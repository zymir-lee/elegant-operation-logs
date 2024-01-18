package pers.zymir.logs.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import pers.zymir.logs.core.anno.LogField;

@Data
@AllArgsConstructor
public class FieldContentMeta {
    private Object fieldValue;
    private LogField logField;
}
