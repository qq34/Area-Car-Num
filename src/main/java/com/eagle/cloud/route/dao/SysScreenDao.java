package com.eagle.cloud.route.dao;

import java.util.List;

import com.eagle.cloud.route.controller.resultModel;
import com.eagle.cloud.route.model.LSmodel;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysScreenDao {

//	List<SysScreen> getScreenList(Map<String, Object> params);
//
//	void deleteScreen(Long id);
//
//	void addScreen(SysScreen sysScreen);
//
//	List<Section> getTbl_sectionInfo();
//
//	/**
//	 * 更新tbl_section
//	 * @param tbl_section
//	 * @return
//	 */
//	int updateSectionBysectionId(Section section);
//
//	/**
//	 * 插入记录表
//	 * @param tbl_steps_record
//	 * @return
//	 */
//	int addRecordInfo(StepsRecord stepsrecord);
//
//	/**
//	 * 获取时间和距离总数
//	 * @return
//	 */
//	List<requestDto> getScrtionCountBypathId();
//
//	/**
//	 * 更新tbl_path
//	 * @param dto
//	 * @return
//	 */
//	int updatePathDurationAndDistance(requestDto dto);
//	/**
//	 * 得到最大的 sort 值
//	 * @return
//	 */
//	BigDecimal getSortMax();
//
//	/**
//	 * 通过最大的 sort 值  查询所有路况信息
//	 * @param bigDecimal
//	 * @return
//	 */
//	List<StepsRecord> getRoadAll(BigDecimal bigDecimal);
//
//	/**
//	 * 统计一次路段的数量
//	 * @param bigDecimal
//	 * @return
//	 */
//	int getSortMaxCount(BigDecimal bigDecimal);
//
//	/*
//	 * 得到第二大的 sort 值
//	 */
//	BigDecimal getSortSecondMax();

	/*
	读取54个区间的实时车辆数
	 */
	List<resultModel> getAllGantryNumber();

	/*
	获得某一个区间的实时车辆数
 	*/
	int getCarNumberByGantryName(String gantryID);

	/*
	更新某区间的实时车辆数
	 */
	int updateGantryCarNumber(String gantryID,int carnumber);

	/*
	根据车牌、门架号、查流水时间
	 */
	String getTimeWithPlateAndGantryID(String plate,String gantry);

	//根据时间查data流水
	List<LSmodel> getLSFromDataByTime(String beforetime , String aftertime);

	//根据时间查data流水,返回流水条数
	int getCountFromDataByTime(String beforetime , String aftertime, String gantryID);


}
