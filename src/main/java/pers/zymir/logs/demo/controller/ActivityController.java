package pers.zymir.logs.demo.controller;

import org.springframework.web.bind.annotation.*;
import pers.zymir.logs.core.anno.EnableOperationLog;
import pers.zymir.logs.core.anno.OperationLog;
import pers.zymir.logs.demo.Activity;
import pers.zymir.logs.demo.controller.log_proxy.ActivityControllerLogProxy;
import pers.zymir.logs.demo.service.ActivityService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/activity")
@EnableOperationLog(module = ActivityController.MODULE_NAME, proxyClass = ActivityControllerLogProxy.class)
public class ActivityController {

    static final String MODULE_NAME = "活动管理";

    @Resource
    ActivityService activityService;

    @PutMapping
    public void updateActivity(@RequestBody Activity activity) {
        activityService.updateActivity(activity);
    }

    @PostMapping
    @OperationLog(module = MODULE_NAME, submodule = "添加活动", detail = "'添加活动' + #activity.getName()")
    public void addActivity(@RequestBody Activity activity) {
        activityService.addActivity(activity);
    }
}
