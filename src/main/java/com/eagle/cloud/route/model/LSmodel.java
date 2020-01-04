package com.eagle.cloud.route.model;

import lombok.Data;

/**
 * @Author:FWJ
 * @Date:2019-11-6
 * @Description:IntelliJ IDEA
 * @version:1.0
 */
@Data
public class LSmodel {
    private String gantryID;
    private String plate;
    private int carType;
    private int carModel;
    private String time;
    private int fare;
    private double speed;
}
