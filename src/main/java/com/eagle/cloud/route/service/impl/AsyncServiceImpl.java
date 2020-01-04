package com.eagle.cloud.route.service.impl;

import com.eagle.cloud.route.dao.SysScreenDao;
import com.eagle.cloud.route.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author:FWJ
 * @Date:2019-12-8
 * @Description:IntelliJ IDEA
 * @version:1.0
 */

@Service
public class AsyncServiceImpl implements AsyncService   {
    @Autowired
    private SysScreenDao screenDao;

    @Override
        @Async("asyncServiceExecutor")
        public void executeAsync(String duration,String AddNumArea) {
            try{
                long sleeptime = Math.round(Double.valueOf(duration));
                Thread.sleep(sleeptime*1000);

                System.out.println("休眠完毕，开始运行异步===============================================================================================================================================================================");
                int oldCarNumber = screenDao.getCarNumberByGantryName(AddNumArea);
                System.out.println("oldCarNumber="+oldCarNumber);
                screenDao.updateGantryCarNumber(AddNumArea,oldCarNumber-1);

                System.out.println("成功运行异步+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            }catch(Exception e){
                e.printStackTrace();
            }
        }


}
