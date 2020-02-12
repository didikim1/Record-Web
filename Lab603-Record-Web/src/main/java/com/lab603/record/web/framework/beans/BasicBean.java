package com.lab603.record.web.framework.beans;

import java.util.List;

import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;

import egovframework.pagination.PaginationInfo;

public class BasicBean
{
    private PaginationInfo      paginationInfo;
    private MyMap               paramMap;
    private List<MyCamelMap>    list;

    public PaginationInfo       getPaginationInfo()     { return paginationInfo; }
    public MyMap                getParamMap()           { return paramMap; }
    public List<MyCamelMap>     getList()               { return list; }

    public void setPaginationInfo(PaginationInfo paginationInfo) { this.paginationInfo = paginationInfo; }
    public void setParamMap(MyMap paramMap)                      { this.paramMap = paramMap; }
    public void setList(List<MyCamelMap> list)                   { this.list = list; }
}
