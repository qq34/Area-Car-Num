package com.eagle.cloud.route.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.eagle.cloud.route.controller.DateUtil;
import com.eagle.cloud.route.model.LSmodel;
import com.eagle.cloud.route.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagle.cloud.route.dao.SysScreenDao;
import com.eagle.cloud.route.service.ScreenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScreenServiceImpl implements ScreenService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysScreenDao screenDao;

	//门架之间前后关系数据表：获得对应流水实体类的+1、-1区间。
	private  Map<String, Map<String,List>> datamap= new HashMap<String, Map<String,List>>(){{

		put("mghd01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd01");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mghd02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd02");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mghd03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd03");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mghd04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd04");}});put("ReduceNumArea",new ArrayList(){{add("mghd03");}});}});
		put("mghd05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd05");}});put("ReduceNumArea",new ArrayList(){{add("mghd04");}});}});
		put("mghd06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd06");}});put("ReduceNumArea",new ArrayList(){{add("mghd05");}});}});
		put("mghd07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd07");}});put("ReduceNumArea",new ArrayList(){{add("mghd06");add("mbshb03");add("mbshn04");}});}});
		put("mghd08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd08");}});put("ReduceNumArea",new ArrayList(){{add("mghd07");}});}});
		put("mghd09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd09");}});put("ReduceNumArea",new ArrayList(){{add("mghd08");}});}});
		put("mghd10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd10");}});put("ReduceNumArea",new ArrayList(){{add("mghd09");add("mzcb05");add("mzcn04");}});}});
		put("mghd11",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghd11");}});put("ReduceNumArea",new ArrayList(){{add("mghd10");}});}});

		put("mghx01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx01");}});put("ReduceNumArea",new ArrayList(){{add("mghx02");}});}});
		put("mghx02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx02");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mghx03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx03");}});put("ReduceNumArea",new ArrayList(){{add("mghx04");}});}});
		put("mghx04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx04");}});put("ReduceNumArea",new ArrayList(){{add("mghx05");}});}});
		put("mghx05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx05");}});put("ReduceNumArea",new ArrayList(){{add("mghx06");}});}});
		put("mghx06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx06");}});put("ReduceNumArea",new ArrayList(){{add("mghx07");add("mbshb03");add("mbshn04");}});}});
		put("mghx07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx07");}});put("ReduceNumArea",new ArrayList(){{add("mghx08");}});}});
		put("mghx08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx08");}});put("ReduceNumArea",new ArrayList(){{add("mghx09");}});}});
		put("mghx09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx09");}});put("ReduceNumArea",new ArrayList(){{add("mghx10");add("mzcb05");add("mzcn04");}});}});
		put("mghx10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx10");}});put("ReduceNumArea",new ArrayList(){{add("mghx11");}});}});
		put("mghx11",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mghx11");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});

		put("mbshb01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb01");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mbshb02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb02");}});put("ReduceNumArea",new ArrayList(){{add("mbshb01");}});}});
		put("mbshb03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb03");}});put("ReduceNumArea",new ArrayList(){{add("mbshb02");}});}});
		put("mbshb04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb04");}});put("ReduceNumArea",new ArrayList(){{add("mbshb03");add("mghd06");add("mghx07");}});}});
		put("mbshb05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshb05");}});put("ReduceNumArea",new ArrayList(){{add("mbshb04");}});}});

		put("mbshn01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn01");}});put("ReduceNumArea",new ArrayList(){{add("mbshn02");}});}});
		put("mbshn02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn02");}});put("ReduceNumArea",new ArrayList(){{add("mbshn03");}});}});
		put("mbshn03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn03");}});put("ReduceNumArea",new ArrayList(){{add("mbshn04");add("mghd06");add("mghx07");}});}});
		put("mbshn04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn04");}});put("ReduceNumArea",new ArrayList(){{add("mbshn05");}});}});
		put("mbshn05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mbshn05");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});

		put("mzcb01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb01");}});put("ReduceNumArea",new ArrayList(){{add("mzcb02");}});}});
		put("mzcb02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb02");}});put("ReduceNumArea",new ArrayList(){{add("mzcb03");}});}});
		put("mzcb03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb03");}});put("ReduceNumArea",new ArrayList(){{add("mzcb04");}});}});
		put("mzcb04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb04");}});put("ReduceNumArea",new ArrayList(){{add("mzcb05");add("mghd09");add("mghx10");}});}});
		put("mzcb05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb05");}});put("ReduceNumArea",new ArrayList(){{add("mzcb06");}});}});
		put("mzcb06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb06");}});put("ReduceNumArea",new ArrayList(){{add("mzcb07");}});}});
		put("mzcb07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb07");}});put("ReduceNumArea",new ArrayList(){{add("mzcb08");}});}});
		put("mzcb08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb08");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mzcb09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb09");}});put("ReduceNumArea",new ArrayList(){{add("mzcb04");}});}});
		put("mzcb10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcb10");}});put("ReduceNumArea",new ArrayList(){{add("mzcb09");}});}});

		put("mzcn01",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn01");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
		put("mzcn02",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn02");}});put("ReduceNumArea",new ArrayList(){{add("mzcn01");}});}});
		put("mzcn03",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn03");}});put("ReduceNumArea",new ArrayList(){{add("mzcn02");}});}});
		put("mzcn04",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn04");}});put("ReduceNumArea",new ArrayList(){{add("mzcn03");add("mzcn09");}});}});
		put("mzcn05",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn05");}});put("ReduceNumArea",new ArrayList(){{add("mzcn04");add("mghd09");add("mghx10");}});}});
		put("mzcn06",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn06");}});put("ReduceNumArea",new ArrayList(){{add("mzcn05");}});}});
		put("mzcn07",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn07");}});put("ReduceNumArea",new ArrayList(){{add("mzcn06");}});}});
		put("mzcn08",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn08");}});put("ReduceNumArea",new ArrayList(){{add("mzcn07");}});}});
		put("mzcn09",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn09");}});put("ReduceNumArea",new ArrayList(){{add("mzcn10");}});}});
		put("mzcn10",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("mzcn10");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});

		put("mbdz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd03");add("mghx04");}});}});
		put("mfsz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd04");add("mghx05");}});}});
		put("mzscz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd05");add("mghx06");}});}});
		put("mzxz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd06");add("mghx07");add("mbshb03");add("mbshn04");}});}});
		put("melz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd07");add("mghx08");}});}});
		put("mlpz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd08");add("mghx09");}});}});
		put("mzgz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mghd10");add("mghx11");}});}});

		put("mlcz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mbshb01");add("mbshn02");}});}});
		put("mzcdz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mbshb02");add("mbshn03");}});}});
		put("mxhz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mbshb04");add("mbshn05");}});}});

		put("mzcz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb08");add("mzcn07");}});}});
		put("mzgnz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb07");add("mzcn06");}});}});
		put("mxlz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb06");add("mzcn05");}});}});
		put("mptz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb04");add("mzcn03");add("mzcn09");}});}});
		put("mgcz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb03");add("mzcn02");}});}});
		put("mtyz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb02");add("mzcn01");}});}});
		put("msfz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("mzcb09");add("mzcn10");}});}});
		put("mjkz",new HashMap<String, List>(){{put("AddNumArea",new ArrayList(){{add("m0");}});put("ReduceNumArea",new ArrayList(){{add("m0");}});}});
	}};

	/**
	 * 实时车辆数算法:
	 * 每10秒运行一次
	 * 更新gantry_car_number 表
	 */
	/*
	这里完成对结果表的修改并更新到数据库
	 */
	@Override
	public void getGantryCarNumberInfo(){

		//获取20秒前、30秒前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-60);
		Date twentySecondBefore=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-70);
		Date thirtySecondBefore=c.getTime();
		String twentySecondBeforeTime = df.format(twentySecondBefore);
		String thirtySecondBeforeTime = df.format(thirtySecondBefore);

		logger.info("20s前时间："+twentySecondBeforeTime);
		logger.info("30s前时间："+thirtySecondBeforeTime);


		//从总data表中取10分钟（前11分钟到前1分钟）的出口车流水，出口：28个：10个主线：mghd01,mghd02,mghx03,mghx01,mghd11,mbshn01,mbshb05,mzcn08,mzcb01,mzcb10 18个收费站
		List<LSmodel> query = screenDao.getLSFromDataByTime(twentySecondBeforeTime, thirtySecondBeforeTime);
//		List<LSmodel> query = screenDao.getLSFromDataByTime("2019-12-18 22:00:00", "2019-12-18 08:00:00");

		logger.info("拿到10s流水");

		logger.info("LSList的大小"+query.size());

		//处理这10秒的变化
			for (LSmodel ls : query) {
				//获取这个流水所产生的区间车辆数变化
				Map<String, String> reMap = EnterOutMethor(ls, datamap);
				logger.info("算法返回结果"+reMap);

				//获取reMap中的AddNumArea，在最终结果表中处理：+1
				if (reMap.get("AddNumArea").equals("m0")) {
				} else {
					int oldCarNumber = screenDao.getCarNumberByGantryName(reMap.get("AddNumArea"));
					screenDao.updateGantryCarNumber(reMap.get("AddNumArea"),oldCarNumber+1);
				}

				//获取reMap中的ReduceNumArea，在最终结果表中处理：-1
				if (reMap.get("ReduceNumArea").equals("m0")) {
				} else {
					int oldCarNumber = screenDao.getCarNumberByGantryName(reMap.get("ReduceNumArea"));
					screenDao.updateGantryCarNumber(reMap.get("ReduceNumArea"),oldCarNumber-1);
				}

			}
			log.info("处理完一次10s");
	}


	/*
    出入量法
     */
	public  Map<String,String> EnterOutMethor(LSmodel ls , Map<String,Map<String,List>> datamap){

		//建议在每个方法中都new一个新的SimpleDateFormat对象，因为SimpleDateFormat在多线程环境下，是线程不安全的
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		//获得该车辆通过门架的信息
		Map<String, List> lsMap = datamap.get(ls.getGantryID());
		//可能导致车辆数-1的区间列表
		List reduceList = lsMap.get("ReduceNumArea");
		//最多有3种前方门架，需要全部遍历该列表
		int i = 0;//用于记录循环次数，以便获知何时遍历到最后一个
		a:for(Object reduceArea : reduceList){
			i= i+1;

			Map<String, Object> redb= null;

			String oldTime = screenDao.getTimeWithPlateAndGantryID(ls.getPlate(), (String) reduceArea);
				//上面得到了该车牌是否在前一门架通过，如果没有则报错，抓起处理
				//下面返回该车牌通过前一门架的时间：YYYY-MM-DD hh:mm:ss

			if(oldTime != null) {

				logger.info("查到前门架时间" + oldTime);

				try {
					Date dateTime = simpleDateFormat.parse(ls.getTime());
					Date dateOldTime = simpleDateFormat.parse(oldTime);
					long l = dateTime.getTime() - dateOldTime.getTime();

					logger.info("间隔时间l=" + l);

					//判断有无超过5小时，5小时=18000
					if (l <= 18000) {
						return new HashMap() {{
							put("AddNumArea", lsMap.get("AddNumArea").get(0));
							put("ReduceNumArea", reduceArea);
						}};
					} else {
						continue a;
					}
				}catch (ParseException e){
					e.printStackTrace();
				}
			}
			else {
				logger.info("数据库查询结果为null "+oldTime);
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


	/**
	 * 下面10个是额外运行的程序，用于10个不能用出入量法的区间
	 */
	public void reduceMghx01(){
			//取预设的预留缓冲时间：60s，mghx01 1.8km，73km/h，89s， 60+89=149
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-149);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-159);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mghx01");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mghx01");
		screenDao.updateGantryCarNumber("mghx01",oldCarNumber-count);
	}
	public void reduceMghx03(){
		//取预设的预留缓冲时间：60s，mghx03 2.8km，90km/h，112s， 60+112=172
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-172);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-182);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mghx03");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mghx03");
		screenDao.updateGantryCarNumber("mghx03",oldCarNumber-count);
	}
	public void reduceMghd01(){
		//取预设的预留缓冲时间：60s，mghd01 1.8km，80km/h，81s， 60+81=141
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-141);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-151);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mghd01");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mghd01");
		screenDao.updateGantryCarNumber("mghd01",oldCarNumber-count);
	}
	public void reduceMghd02(){
		//取预设的预留缓冲时间：60s，mghd02 7km，90km/h，280s， 60+280=340
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-340);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-350);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mghd02");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mghd02");
		screenDao.updateGantryCarNumber("mghd02",oldCarNumber-count);
	}
	public void reduceMghd11(){
		//取预设的预留缓冲时间：60s，mghd11 16.8km，108km/h，560， 620
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-620);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-630);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mghd11");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mghd11");
		screenDao.updateGantryCarNumber("mghd11",oldCarNumber-count);
	}
	public void reduceMbshb05(){
		//取预设的预留缓冲时间：60s，mbshb05	 4km，95km/h，152， 212
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-212);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-222);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mbshb05");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mbshb05");
		screenDao.updateGantryCarNumber("mbshb05",oldCarNumber-count);
	}
	public void reduceMbshn01(){
		//取预设的预留缓冲时间：60s，mbshn01	1.8km，108km/h，60，120
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-120);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-130);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mbshn01");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mbshn01");
		screenDao.updateGantryCarNumber("mbshn01",oldCarNumber-count);
	}
	public void reduceMzcb01(){
		//取预设的预留缓冲时间：60s，mzcb01	4.6km，100km/h，166，226
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-226);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-236);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mzcb01");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mzcb01");
		screenDao.updateGantryCarNumber("mzcb01",oldCarNumber-count);
	}
	public void reduceMzcn08(){
		//取预设的预留缓冲时间：60s，mzcn08	3.4km，108km/h，113，173
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-173);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-183);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mzcn08");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mzcn08");
		screenDao.updateGantryCarNumber("mzcn08",oldCarNumber-count);
	}
	public void reduceMzcb10(){
		//取预设的预留缓冲时间：60s，mzcb10	3.4km，100km/h，389，449
		//获取之前的时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND,-449);
		Date befroetime=c.getTime();
		c.setTime(date);
		c.add(Calendar.SECOND,-459);
		Date aftertime=c.getTime();
		String befroeTime = df.format(befroetime);
		String afterTime = df.format(aftertime);

		int count = screenDao.getCountFromDataByTime(befroeTime, afterTime,"mzcb10");

		int oldCarNumber = screenDao.getCarNumberByGantryName("mzcb10");
		screenDao.updateGantryCarNumber("mzcb10",oldCarNumber-count);
	}

}
