package com.lab603.record.web.gnrlmber.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab603.record.web.framework.beans.BasicBean;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.utils.FrameworkPagingUtils;
import com.lab603.record.web.mapper.gnrlmber.GnrlmberMapper;

@Service("com.lab603.record.web.gnrlmber.biz.GnrlmberBiz")
public class GnrlmberBiz
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(GnrlmberBiz.class.getName());

	@Resource(name = "com.lab603.record.web.mapper.gnrlmber.GnrlmberMapper")
	GnrlmberMapper mMapper;

	/**
	* 페이징 데이터
	* @param paramMap
	* @return
	*/
	public BasicBean ListPagingData(MyMap paramMap) {
		BasicBean resultBean = null;

		FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));
		resultBean = FrameworkPagingUtils.pagingData(paramMap, paramMap.getInt("rows", 10),
				mMapper.SelectOnePagingCount(paramMap), mMapper.ListPagingData(paramMap));

		return resultBean;
	}

	/**
	* 상세 조회
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap) {
		return mMapper.SelectOneData(paramMap);
	}

	/**
	* 등록/수정
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap) {
		return mMapper.RegisterData(paramMap);
	}

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap) {
		return mMapper.ModifyData(paramMap);
	}

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap) {
		return mMapper.DeleteData(paramMap);
	}
}
