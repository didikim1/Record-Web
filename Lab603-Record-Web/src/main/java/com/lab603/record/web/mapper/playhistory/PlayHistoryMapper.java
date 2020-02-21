package com.lab603.record.web.mapper.playhistory;

import org.springframework.stereotype.Repository;

import com.lab603.record.web.framework.config.mybatis.support.Master;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;

@Master
@Repository("com.lab603.record.web.mapper.playhistory.PlayHistoryMapper")
public interface PlayHistoryMapper
{
	/**
	* 페이징 갯수
	* @param paramMap
	* @return
	*/
	public int SelectOnePagingCount(MyMap paramMap);

	/**
	* 페이징 목록
	* @param paramMap
	* @return
	*/
	public java.util.List ListPagingData(MyMap paramMap);

	/**
	* 상세
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap);

	/**
	* 추가
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap);

	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap);

	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap);
}
