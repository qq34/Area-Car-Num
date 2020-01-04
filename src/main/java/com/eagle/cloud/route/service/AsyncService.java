package com.eagle.cloud.route.service;

import org.springframework.stereotype.Repository;

/**
 * @Author:FWJ
 * @Date:2019-12-8
 * @Description:IntelliJ IDEA
 * @version:1.0
 */

public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync(String duration,String AddNumArea);
}
