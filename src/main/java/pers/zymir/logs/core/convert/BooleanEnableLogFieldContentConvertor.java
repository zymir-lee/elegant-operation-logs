package pers.zymir.logs.core.convert;

import org.springframework.stereotype.Component;

@Component
public class BooleanEnableLogFieldContentConvertor extends AbstractLogFieldContentConvertor {
    @Override
    protected String doConvert(Object object) {
        return (boolean) object ? "开启" : "关闭";
    }

    @Override
    public Class<?> getType() {
        return Boolean.class;
    }
}
