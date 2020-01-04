package com.eagle.cloud.route.controller;
/**
 * @title: RoadController
 * @projectName display-route-sync
 * @description: TODO
 * @date 2019-11-8 15:24
 */

//import com.eagle.cloud.route.service.RoadService;

import com.eagle.cloud.route.dao.SysScreenDao;
import com.eagle.cloud.route.service.ScreenService;
import com.eagle.cloud.route.vo.StepsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.eagle.cloud.route.vo.StepsRecordNew;

/**
 *@Description: TODO
 *@Author: yunbao
 *@Date: 2019-11-8 15:24
 *@Verion: 1.0
 **/
@RestController
@RequestMapping(value = "/roadInfo")
public class RoadController {



//    @Autowired
//    private ScreenService screenService;

    @Autowired
    private SysScreenDao screenDao;

    //测试0
    @RequestMapping(value = "/ArealsCarNum",method = RequestMethod.GET)
    public List<resultModel> ArealsCarNum()  {
        List<resultModel> allGantryNumber = screenDao.getAllGantryNumber();
        return allGantryNumber;
    }

}
