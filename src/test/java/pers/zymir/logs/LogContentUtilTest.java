package pers.zymir.logs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.zymir.logs.core.LogContentUtil;
import pers.zymir.logs.core.convert.FieldLogConvertor;
import pers.zymir.logs.demo.Activity;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = ElegantOperationLogsApplication.class)
public class LogContentUtilTest {

    @Test
    public void logContentGenTest() {
        Activity activity01 = new Activity();
        activity01.setId(1L);
        activity01.setName("营销活动001");
        activity01.setDescription("营销活动001");
        activity01.setEnable(false);
        activity01.setStartTime(LocalDateTime.now());
        activity01.setEndTime(LocalDateTime.now().plusDays(2));

        Activity activity02 = new Activity();
        activity02.setId(2L);
        activity02.setName("营销活动002");
        activity02.setDescription("营销活动002");
        activity02.setEnable(true);
        activity02.setStartTime(LocalDateTime.now());
        activity02.setEndTime(LocalDateTime.now().plusDays(3));


        List<String> contents = LogContentUtil.buildContent(LogContentUtil.beanToMap(activity01), LogContentUtil.beanToMap(activity02));
        contents.forEach(System.out::println);
    }
}
