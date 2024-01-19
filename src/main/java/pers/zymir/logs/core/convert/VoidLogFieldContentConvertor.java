package pers.zymir.logs.core.convert;

import org.springframework.stereotype.Component;

@Component
public class VoidLogFieldContentConvertor extends AbstractLogFieldContentConvertor {
    @Override
    protected String doConvert(Object object) {
        return "";
    }

    @Override
    public Class<?> getType() {
        return void.class;
    }
}
