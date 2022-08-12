package com.lab603.record.web.comtn.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.mapper.comtn.serverInfo.ServerInfoMapper;

@Service("com.lab603.record.web.comtn.biz.ServerInfoBiz")
public class ServerInfoBiz
{
	@Resource(name="com.lab603.record.web.mapper.comtn.serverInfo.ServerInfoMapper")
	ServerInfoMapper mMapper;

	public MyMap SelectOneData(MyMap paramMap)
	{
		List<MyMap> result = mMapper.SelectOneData(paramMap);

		if( result == null ) 		return null;
		if( result.size() == 0 ) 	return null;

		return result.get(0);
	}
}
