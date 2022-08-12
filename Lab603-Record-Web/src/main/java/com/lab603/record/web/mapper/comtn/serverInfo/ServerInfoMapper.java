package com.lab603.record.web.mapper.comtn.serverInfo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lab603.record.web.framework.config.mybatis.support.Master;
import com.lab603.record.web.framework.mymap.MyMap;

@Master
@Repository("com.lab603.record.web.mapper.comtn.serverInfo.ServerInfoMapper")
public interface ServerInfoMapper
{
	public List<MyMap> SelectOneData(MyMap paramMap);
}
