package com.lab603.record.web.comtn.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab603.record.web.framework.beans.BasicBean;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.utils.FrameworkPagingUtils;
import com.lab603.record.web.mapper.comtn.code.CompanyCodeMapper;

@Service("com.lab603.record.web.comtn.biz.CompanyCodeBiz")
public class CompanyCodeBiz 
{
	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(CompanyCodeBiz.class.getName());
	
	@Resource(name = "com.lab603.record.web.mapper.comtn.code.CompanyCodeMapper")
	CompanyCodeMapper mMapper;

	/**
	* 페이징 데이터
	* @param paramMap
	* @return
	*/
	public BasicBean ListPagingData(MyMap paramMap)
	{
		BasicBean resultBean = new BasicBean();
		
		resultBean.setList(  mMapper.ListPagingData(paramMap) );

		return resultBean;
	}

	/**
	* 상세 조회
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap)
	{
		return mMapper.SelectOneData(paramMap);
	}

	/**
	* 등록/수정
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap)
	{
		return mMapper.RegisterData(paramMap);
	}

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap)
	{
		return mMapper.ModifyData(paramMap);
	}

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap)
	{
		return mMapper.DeleteData(paramMap);
	}
}
