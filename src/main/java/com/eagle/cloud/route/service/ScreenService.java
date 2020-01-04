package com.eagle.cloud.route.service;

import com.eagle.cloud.route.vo.StepsRecord;
import com.eagle.cloud.route.vo.StepsRecordNew;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScreenService {

	
//	void getGDReadInfo();
//
//	void getSectionCountInfo();
//	/**
//	 * 得到所有的一次查询的 所有的路况信息
//	 *
//	 * @return
//	 */
//	List<StepsRecord> getRoadAll() ;
//
//	/**
//	 * 想要的格式
//	 * @param list
//	 * @return
//	 */
//	List<StepsRecordNew> getRoadAllNew(List<StepsRecord> list);

/*
实时车辆数算法
 */
	void getGantryCarNumberInfo();

	//下面10个是额外运行的程序，用于10个不能用出入量法的区间
	void reduceMghx01();
	void reduceMghx03();
	void reduceMghd01();
	void reduceMghd02();
	void reduceMghd11();
	void reduceMbshb05();
	void reduceMbshn01();
	void reduceMzcb01();
	void reduceMzcn08();
	void reduceMzcb10();

}
