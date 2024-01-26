package pers.zymir.logs.demo.controller.log_proxy;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pers.zymir.logs.core.LogContentUtil;
import pers.zymir.logs.demo.Activity;
import pers.zymir.logs.demo.service.ActivityService;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class ActivityControllerLogProxy {

    @Resource
    ActivityService activityService;

    public void updateActivity(Activity activity) {
        Activity activityDB = activityService.getActivity(activity.getId());
        List<String> contents = LogContentUtil.buildContent(LogContentUtil.beanToMap(activityDB), LogContentUtil.beanToMap(activity));
        log.info("日志生成内容: {}\n\r", JSONUtil.toJsonPrettyStr(contents));
    }
}
