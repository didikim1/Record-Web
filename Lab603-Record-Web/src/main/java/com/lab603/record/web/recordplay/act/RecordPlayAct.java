package com.lab603.record.web.recordplay.act;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lab603.record.web.framework.beans.FrameworkBeans;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.result.ResultMessage;

@Controller
@RequestMapping("/recordPlay")
public class RecordPlayAct
{
	final String pagePrefix = "recordPlay";

	private static final Logger log = LoggerFactory.getLogger(RecordPlayAct.class);


	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return null;
	}

	@RequestMapping(value = { "/RegisterData.do" })
	public ResultMessage RegisterData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return new ResultMessage("", null);
	}

	@RequestMapping(value = { "/ModifyData.do" })
	public ResultMessage ModifyData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return new ResultMessage("", null);
	}

	@RequestMapping(value = { "/DeleteData.do" })
	public ResultMessage DeleteData(Model model)
	{
		MyMap paramMap = FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		return new ResultMessage("", null);
	}
}
