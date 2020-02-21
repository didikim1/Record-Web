package com.lab603.record.web.login.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab603.record.web.framework.beans.BasicBean;
import com.lab603.record.web.framework.beans.FrameworkBeans;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.result.ResultCode;
import com.lab603.record.web.framework.result.ResultMessage;
import com.lab603.record.web.login.service.LoginHistoryBiz;

@Controller
@RequestMapping("/loginHistory")
public class LoginHistoryAct
{
	final String pagePrefix = "loginHistory";

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(LoginHistoryAct.class.getName());

	@Resource(name="com.lab603.record.web.login.service.LoginHistoryBiz")
	LoginHistoryBiz mBiz;

	@RequestMapping(value = { "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap 		paramMap 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		BasicBean 	resultBean  = null;

		resultBean = mBiz.ListPagingData( paramMap );

		model.addAttribute("Data", resultBean);

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		MyMap 		paramMap 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 	= new MyCamelMap();

		resultMap = mBiz.SelectOneData(paramMap);

		model.addAttribute("Data", resultMap);

		return pagePrefix + "/SelectOneData";
	}

	@RequestMapping(value = { "/RegisterData.do" })
	public @ResponseBody ResultMessage RegisterData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultRegisterDataCount = 0;

		resultRegisterDataCount = mBiz.RegisterData( paramMap );

		resultMap.put("ResultDataCount", resultRegisterDataCount);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	}

	@RequestMapping(value = { "/ModifyData.do" })
	public @ResponseBody ResultMessage ModifyData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultModifyDataCount 	= 0;

		resultModifyDataCount = mBiz.ModifyData( paramMap );

		resultMap.put("ResultDataCount", resultModifyDataCount);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	}

	@RequestMapping(value = { "/DeleteData.do" })
	public @ResponseBody ResultMessage DeleteData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultDeleteDataCount 	= 0;

		resultDeleteDataCount = mBiz.DeleteData( paramMap );

		resultMap.put("ResultDataCount", resultDeleteDataCount);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	}
}
