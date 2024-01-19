package pers.zymir.logs.core.convert;

import org.springframework.stereotype.Component;

@Component
public class ToStringLogFieldContentConvertor extends AbstractLogFieldContentConvertor {
    @Override
    protected String doConvert(Object object) {
        return object.toString();
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
