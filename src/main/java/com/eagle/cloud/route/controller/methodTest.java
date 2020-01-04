package com.eagle.cloud.route.controller;

//import org.junit.jupiter.api.Test;
import com.eagle.cloud.route.model.LSmodel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author:FWJ
 * @Date:2019-11-6
 * @Description: 实时路段车辆数算法
 * @version:1.0
 */

public class methodTest {
//    public void text(){
////        //流水实体类用于测试
////    LSmodel ls = new LSmodel();
////    ls.setGantryID("mghd06");
////    ls.setSpeed(80.0);
////    ls.setTime("2019-11-10 13:00:00");
////    ls.setPlate("粤A665");
//
//        //门架之间前后关系数据表：获得对应流水实体类的+1、-1区间。
//        Map<String, Map<String,List>> datamap= new HashMap<String, Map<String,List>>(){{
//
//            put("mghd01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd01");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mghd02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd02");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mghd03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd03");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mghd04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd04");}});put("ReduceNumArea",new ArrayList(){{add("mghd03");}});}});
//            put("mghd05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd05");}});put("ReduceNumArea",new ArrayList(){{add("mghd04");}});}});
//            put("mghd06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd06");}});put("ReduceNumArea",new ArrayList(){{add("mghd05");}});}});
//            put("mghd07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd07");}});put("ReduceNumArea",new ArrayList(){{add("mghd06");add("mbshb03");add("mbshn04");}});}});
//            put("mghd08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd08");}});put("ReduceNumArea",new ArrayList(){{add("mghd07");}});}});
//            put("mghd09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd09");}});put("ReduceNumArea",new ArrayList(){{add("mghd08");}});}});
//            put("mghd10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd10");}});put("ReduceNumArea",new ArrayList(){{add("mghd09");add("mzcb05");add("mzcn04");}});}});
//            put("mghd11",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd11");}});put("ReduceNumArea",new ArrayList(){{add("mghd10");}});}});
//
//            put("mghx01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd01");}});put("ReduceNumArea",new ArrayList(){{add("mghx02");}});}});
//            put("mghx02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd02");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mghx03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd03");}});put("ReduceNumArea",new ArrayList(){{add("mghx04");}});}});
//            put("mghx04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd04");}});put("ReduceNumArea",new ArrayList(){{add("mghx05");}});}});
//            put("mghx05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd05");}});put("ReduceNumArea",new ArrayList(){{add("mghx06");}});}});
//            put("mghx06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx06");}});put("ReduceNumArea",new ArrayList(){{add("mghx07");add("mbshb03");add("mbshn04");}});}});
//            put("mghx07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd07");}});put("ReduceNumArea",new ArrayList(){{add("mghx08");}});}});
//            put("mghx08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd08");}});put("ReduceNumArea",new ArrayList(){{add("mghx09");}});}});
//            put("mghx09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx09");}});put("ReduceNumArea",new ArrayList(){{add("mghx10");add("mzcb05");add("mzcn04");}});}});
//            put("mghx10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd10");}});put("ReduceNumArea",new ArrayList(){{add("mghx11");}});}});
//            put("mghx11",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd11");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//
//            put("mbshb01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb01");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mbshb02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb02");}});put("ReduceNumArea",new ArrayList(){{add("mbshb01");}});}});
//            put("mbshb03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb03");}});put("ReduceNumArea",new ArrayList(){{add("mbshb02");}});}});
//            put("mbshb04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb04");}});put("ReduceNumArea",new ArrayList(){{add("mbshb03");add("mghd06");add("mghx07");}});}});
//            put("mbshb05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb05");}});put("ReduceNumArea",new ArrayList(){{add("mbshb04");}});}});
//
//            put("mbshn01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn01");}});put("ReduceNumArea",new ArrayList(){{add("mbshn02");}});}});
//            put("mbshn02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn02");}});put("ReduceNumArea",new ArrayList(){{add("mbshn03");}});}});
//            put("mbshn03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn03");}});put("ReduceNumArea",new ArrayList(){{add("mbshn04");add("mghd06");add("mghx07");}});}});
//            put("mbshn04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn04");}});put("ReduceNumArea",new ArrayList(){{add("mbshn05");}});}});
//            put("mbshn05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn05");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//
//            put("mzcb01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb01");}});put("ReduceNumArea",new ArrayList(){{add("mzcb02");}});}});
//            put("mzcb02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb02");}});put("ReduceNumArea",new ArrayList(){{add("mzcb03");}});}});
//            put("mzcb03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb03");}});put("ReduceNumArea",new ArrayList(){{add("mzcb04");}});}});
//            put("mzcb04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb04");}});put("ReduceNumArea",new ArrayList(){{add("mzcb05");add("mghd09");add("mghx10");}});}});
//            put("mzcb05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb05");}});put("ReduceNumArea",new ArrayList(){{add("mzcb06");}});}});
//            put("mzcb06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb06");}});put("ReduceNumArea",new ArrayList(){{add("mzcb07");}});}});
//            put("mzcb07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb07");}});put("ReduceNumArea",new ArrayList(){{add("mzcb08");}});}});
//            put("mzcb08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb08");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mzcb09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb09");}});put("ReduceNumArea",new ArrayList(){{add("mzcb05");}});}});
//            put("mzcb10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb10");}});put("ReduceNumArea",new ArrayList(){{add("mzcb09");}});}});
//
//            put("mzcn01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn01");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//            put("mzcn02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn02");}});put("ReduceNumArea",new ArrayList(){{add("mzcn01");}});}});
//            put("mzcn03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn03");}});put("ReduceNumArea",new ArrayList(){{add("mzcn02");}});}});
//            put("mzcn04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn04");}});put("ReduceNumArea",new ArrayList(){{add("mzcn03");add("mzcn09");}});}});
//            put("mzcn05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn05");}});put("ReduceNumArea",new ArrayList(){{add("mzcn04");add("mghd09");add("mghx10");}});}});
//            put("mzcn06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn06");}});put("ReduceNumArea",new ArrayList(){{add("mzcn05");}});}});
//            put("mzcn07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn07");}});put("ReduceNumArea",new ArrayList(){{add("mzcn06");}});}});
//            put("mzcn08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn08");}});put("ReduceNumArea",new ArrayList(){{add("mzcn07");}});}});
//            put("mzcn09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn09");}});put("ReduceNumArea",new ArrayList(){{add("mzcn10");}});}});
//            put("mzcn10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn10");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
//
//            put("mbdz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd03");add("mghx04");}});}});
//            put("mfsz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd04");add("mghx05");}});}});
//            put("mzscz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd05");add("mghx06");}});}});
//            put("mzxz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd06");add("mghx07");add("mbshb03");add("mbshn04");}});}});
//            put("melz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd07");add("mghx08");}});}});
//            put("mlpz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd08");add("mghx09");}});}});
//            put("mzgz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd10");add("mghx11");}});}});
//
//            put("mlcz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mbshb01");add("mbshn02");}});}});
//            put("mzcdz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mbshb02");add("mbshn03");}});}});
//            put("mxhz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mbshb04");add("mbshn05");}});}});
//
//            put("mzcz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb08");add("mzcn07");}});}});
//            put("mzgnz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb07");add("mzcn06");}});}});
//            put("mxlz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb06");add("mzcn05");}});}});
//            put("mptz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb04");add("mzcn03");add("mzcn09");}});}});
//            put("mgcz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb03");add("mzcn02");}});}});
//            put("mtyz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb02");add("mzcn01");}});}});
//            put("msfz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb09");add("mzcn10");}});}});
//            put("mjkz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb10");}});}});
//        }};
////密度法的数据表
//        Map<String,Double> DesityMethorDataMap = new HashMap();
//        DesityMethorDataMap.put("mghx01",1.8);
//        DesityMethorDataMap.put("mghx03",2.8);
//        DesityMethorDataMap.put("mghd01",1.8);
//        DesityMethorDataMap.put("mghd02",7.0);
//        DesityMethorDataMap.put("mghd11",16.8);
//        DesityMethorDataMap.put("mbshb05",4.0);
//        DesityMethorDataMap.put("mbshn01",1.8);
//        DesityMethorDataMap.put("mzcb01",4.6);
//        DesityMethorDataMap.put("mzcn08",3.4);
//        DesityMethorDataMap.put("mzcb10",10.8);
//
//
//        //数据库连接池对象
//        JdbcTemplate tem = new JdbcTemplate(JDBCUtil.getDataSource());
//        String sql = "SELECT * FROM `data` WHERE time = ?";
//
//        Date date = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar c = new GregorianCalendar();
//        System.out.println(df.format(date));
//        c.setTime(date);
//        c.add(Calendar.SECOND,-5);
//        date=c.getTime();
//        String fiveSecondTime = df.format(date);
//        System.out.println(fiveSecondTime);
//
//        List<LSmodel> query = tem.query(sql, new BeanPropertyRowMapper<LSmodel>(LSmodel.class), fiveSecondTime);
//        System.out.println( query.size());
//
//
//        //区间原始车辆数，响应的数据map
//        Map<String,Integer> result = new HashMap<String, Integer>(){{
//            put("mghd01",100);
//            put("mghd02",100);
//            put("mghd03",100);
//            put("mghd04",100);
//            put("mghd05",100);
//            put("mghd06",100);
//            put("mghd07",100);
//            put("mghd08",100);
//            put("mghd09",100);
//            put("mghd10",100);
//            put("mghd11",100);
//            put("mghx01",100);
//            put("mghx02",100);
//            put("mghx03",100);
//            put("mghx04",100);
//            put("mghx05",100);
//            put("mghx06",100);
//            put("mghx07",100);
//            put("mghx08",100);
//            put("mghx09",100);
//            put("mghx10",100);
//            put("mghx11",100);
//            put("mbshb01",100);
//            put("mbshb02",100);
//            put("mbshb03",100);
//            put("mbshb04",100);
//            put("mbshb05",100);
//            put("mbshn01",100);
//            put("mbshn02",100);
//            put("mbshn03",100);
//            put("mbshn04",100);
//            put("mbshn05",100);
//            put("mzcb01",100);
//            put("mzcb02",100);
//            put("mzcb03",100);
//            put("mzcb04",100);
//            put("mzcb05",100);
//            put("mzcb06",100);
//            put("mzcb07",100);
//            put("mzcb08",100);
//            put("mzcb09",100);
//            put("mzcb10",100);
//            put("mzcn01",100);
//            put("mzcn02",100);
//            put("mzcn03",100);
//            put("mzcn04",100);
//            put("mzcn05",100);
//            put("mzcn06",100);
//            put("mzcn07",100);
//            put("mzcn08",100);
//            put("mzcn09",100);
//            put("mzcn10",100);
//        }};
//        System.out.println(result);
//
//        while (true) {
//            for (LSmodel ls : query) {
//                //实时车流数变化
//                Map<String, String> reMap = CurrentCarChange(ls, datamap, DesityMethorDataMap, tem);
//                System.out.println(reMap);
//
//                //获取reMap中的AddNumArea，在最终结果表中处理：+1
//                if (reMap.get("AddNumArea").equals("m0")) {
//                } else {
//                    result.put(reMap.get("AddNumArea"), result.get(reMap.get("AddNumArea")) + 1);
//                }
//
//
//                //获取reMap中的ReduceNumArea，在最终结果表中处理：+1
//                if (reMap.get("ReduceNumArea").equals("m0")) {
//                } else {
//                    result.put(reMap.get("ReduceNumArea"), result.get(reMap.get("ReduceNumArea")) - 1);
//                }
//            }
//            System.out.println(result);
//        }
//
//
//    }

    /*
    实时路段车辆数算法
    输入：流水实体类
    输出：Map<String,String>{"AddNumArea" = 增加一辆车的区间的编号（例：ghd06），"ReduceNumArea" = "减少一辆车的区间的编号"，
    "duration"="增加的这辆车的持续时间（即多少秒后从此区间剔除，单位：秒：String）" }
    注：有duration则无ReduceNumArea，反之亦然，为互斥，因为是用了2种方法算出来的：密度法和出入量法。
    注："AddNumArea"、"duration"、"ReduceNumArea"都不一定会有，但是"AddNumArea"、"ReduceNumArea"至少有一个。
    检测有3种情况，当为收费站出口和辅助枪检测时，返回Map就只有"ReduceNumArea"。
     */
    public  Map<String,String> CurrentCarChange(LSmodel ls , Map datamap, Map<String,Double> DesityMethorDataMap, JdbcTemplate tem){

        Map<String,String> map = new HashMap<String,String>();
        //使用密度法的10个门架编号：
        String[] desityMethorList = {"mghx01","mghx03","mghd01","mghd02","mghd11","mbshb05","mbshn01","mzcb01","mzcn08","mzcb10"};
        for (String gantryID:desityMethorList) {
            if (ls.getGantryID().equals(gantryID) ){
                //符合则调用密度法
//                Map<String, String> DesityMethorMap = DesityMethor(ls ,datamap, DesityMethorDataMap);

                //1.用密度法的门架编号(共10个):DesityMethorDataMap
                //2.延迟时间
                double mile =  DesityMethorDataMap.get(gantryID);
                String sleepTime = String.valueOf(mile/ls.getSpeed()*3600);

                //3.调用出入量法
                Map<String, String> DesityMethorMap = EnterOutMethor(ls, datamap,tem);

                DesityMethorMap.put("duration",sleepTime);
                return DesityMethorMap;
                //密度法返回
            }
        }
        //不符合密度法则调用出入量法
        Map<String, String> EnterOutMethorMap = EnterOutMethor(ls,datamap,tem);
        return EnterOutMethorMap;
    }

    /*
    出入量法
     */
    public  Map<String,String> EnterOutMethor(LSmodel ls , Map<String,Map<String,List>> datamap, JdbcTemplate tem){

        //数据库连接池对象
//        JdbcTemplate tem = new JdbcTemplate(JDBCUtil.getDataSource());
        //获得该车辆通过门架的信息
        Map<String, List> lsMap = datamap.get(ls.getGantryID());
        //可能导致车辆数-1的区间列表
        List reduceList = lsMap.get("ReduceNumArea");
        //最多有3种前方门架，需要全部遍历该列表
        int i = 0;//用于记录循环次数，以便获知何时遍历到最后一个
        a:for(Object reduceArea : reduceList){
            i= i+1;
            //查data数据库表，查这个流水的车牌、5小时时间内有无该车牌流水
            String sql = "SELECT max(time) FROM `data` WHERE plate = ? and gantryID = ?";

            Map<String, Object> redb= null;
            try {
                redb = tem.queryForMap(sql, ls.getPlate(),reduceArea);
                //上面得到了该车牌是否在前一门架通过，如果没有则报错，抓起处理
                //下面返回该车牌通过前一门架的时间：YYYY-MM-DD hh:mm:ss
                String oldTime = (String) redb.get("time");
                //下面是最近一次通过前面门架与本次通过门架的时间之差，单位：天，即5小时=0.283天
                double doubleMargin = DateUtil.getDoubleMargin(ls.getTime(),oldTime);
                //判断有无超过5小时，5小时=0.283天
                if(doubleMargin<=0.283){
                    return new HashMap(){{put("AddNumArea",lsMap.get("AddNumArea").get(0));put("ReduceNumArea",reduceArea);}};
                }
                else {
                    continue a;
                }
            } catch (EmptyResultDataAccessException e) {
                if (i==reduceList.size()){
                    break a;
                }
                else {
                    continue a;
                }
            }
        }
        return new HashMap(){{put("AddNumArea",lsMap.get("AddNumArea").get(0));put("ReduceNumArea","m0");}};
    }

//    /*
//    密度法
//     */
//    public Map<String,String> DesityMethor(LSmodel ls,Map<String,Map<String,List>> datamap,Map<String,Double> DesityMethorDataMap){
//
//    }

}




