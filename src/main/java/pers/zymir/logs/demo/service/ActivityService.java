package pers.zymir.logs.demo.service;

import org.springframework.stereotype.Service;
import pers.zymir.logs.demo.Activity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ActivityService {

    static final Map<Long, Activity> MOCK_DATABASE = new HashMap<>();

    static {
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

        MOCK_DATABASE.put(activity01.getId(), activity01);
        MOCK_DATABASE.put(activity02.getId(), activity02);
    }

    public Activity getActivity(long activityId) {
        return MOCK_DATABASE.get(activityId);
    }

    public void updateActivity(Activity activity) {
        Long id = activity.getId();
        if (!MOCK_DATABASE.containsKey(id)) {
            throw new RuntimeException("活动不存在，活动ID：" + id);
        }
        MOCK_DATABASE.put(id, activity);
    }

    public void addActivity(Activity activity) {
        MOCK_DATABASE.put(activity.getId(), activity);
    }

    public void delete(long activityId) {
        MOCK_DATABASE.remove(activityId);
    }
}
