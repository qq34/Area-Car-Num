package com.eagle.cloud.route.config;

import com.eagle.cloud.route.service.ScreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Slf4j
@Component
public class ScheduledTask {

    @Autowired
    private ScreenService screenService;

////    @Scheduled(fixedRate = 1000 * 60 * 2)
//    @Async
//    @Scheduled(cron = "0 0/2 * * * ?")
//    public void twoTesk() {
//        log.info("每 2 分钟执行一次");
//        screenService.getGDReadInfo();
//        //计算section 时间和距离
//        screenService.getSectionCountInfo();
//    }

    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void twoTesk() {
//        log.info("每10秒执行一次");
        screenService.getGantryCarNumberInfo();
    }

    /**
     * 下面10个程序是应对10个不能用常规方法的区间
     */
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMghx01() {
        screenService.reduceMghx01();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMghx03() {
        screenService.reduceMghx03();
    }

    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMghd01() {
        screenService.reduceMghd01();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMghd02() {
        screenService.reduceMghd02();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMghd11() {
        screenService.reduceMghd11();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMbshb05() {
        screenService.reduceMbshb05();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMbshn01() {
        screenService.reduceMbshn01();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMzcb01() {
        screenService.reduceMzcb01();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMzcn08() {
        screenService.reduceMzcn08();
    }
    @Async
    @Scheduled(cron = "*/10 * * * * ?")
    public void reduceMzcb10() {
        screenService.reduceMzcb10();
    }
    //	private  Map<String,Double> DesityMethorDataMap = new HashMap<String,Double>(){{
//        put("mghx01",1.8);
//        put("mghx03",2.8);
//        put("mghd01",1.8);
//        put("mghd02",7.0);
//        put("mghd11",16.8);
//        put("mbshb05",4.0);
//        put("mbshn01",1.8);
//        put("mzcb01",4.6);
//        put("mzcn08",3.4);
//        put("mzcb10",10.8);



}
