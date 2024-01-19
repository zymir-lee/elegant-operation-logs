package pers.zymir.logs.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pers.zymir.logs.core.anno.LogField;
import pers.zymir.logs.core.convert.LocalDateTimeLogFieldContentConvertor;

import java.time.LocalDateTime;

@Data
public class Activity {
    private Long id;

    @LogField(name = "活动名称")
    private String name;

    @LogField(name = "活动描述")
    private String description;

    @LogField(name = "开启状态")
    private boolean enable;

    @LogField(name = "开始时间", contentConvertor = LocalDateTimeLogFieldContentConvertor.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @LogField(name = "结束时间", contentConvertor = LocalDateTimeLogFieldContentConvertor.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
