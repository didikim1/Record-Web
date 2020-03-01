package com.lab603.record.web.login.act;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.lab603.record.web.framework.utils.FrameworkUtils;
import com.lab603.record.web.login.service.LoginBiz;
import com.lab603.record.web.login.service.LoginHistoryBiz;

@Controller
@RequestMapping("/login")
public class LoginAct
{

	private static final org.apache.log4j.Logger Logger = org.apache.log4j.Logger.getLogger(LoginAct.class.getName());

	final String pagePrefix = "login";

	@Resource(name="com.lab603.record.web.login.service.LoginBiz")
	LoginBiz mBiz;

	@Resource(name="com.lab603.record.web.login.service.LoginHistoryBiz")
	LoginHistoryBiz mLoginHistoryBiz;

	@RequestMapping(value = { "/", "/index.do" })
	public String index(Model model)
	{
		return pagePrefix + "/index";
	}


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
	public @ResponseBody ResultMessage SelectOneData(Model model, HttpServletRequest request)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap		insertLoginHistoryMap	= new MyMap();
		MyCamelMap 	resultMap 				= null;

		String  	resultCode				= ResultCode.RESULT_OK;
		String		strClientIP				= FrameworkUtils.getClientIP(request);


		resultMap 			  = mBiz.SelectOneData(paramMap);

		mLoginHistoryBiz.RegisterDataFirst( insertLoginHistoryMap );

		Logger.debug("insertLoginHistoryMap="+ insertLoginHistoryMap);

		insertLoginHistoryMap.put("loginid", 	insertLoginHistoryMap.getInt("LOGINID"));
		insertLoginHistoryMap.put("mberId", 	paramMap.getInt("mberId"));
		insertLoginHistoryMap.put("ipaddr", 	strClientIP);

		if ( resultMap == null )
		{
			resultCode = ResultCode.RESULT_EMPTY;

			insertLoginHistoryMap.put("loginrtn", "N");
		}
		else
		{
			FrameworkBeans.findSessionBean().mberId 		= resultMap.getStr("mberId");
			FrameworkBeans.findSessionBean().uniqId 		= resultMap.getStr("uniqId");
			FrameworkBeans.findSessionBean().mberName 		= resultMap.getStr("mberName");
			FrameworkBeans.findSessionBean().mberSttus 		= resultMap.getStr("mberSttus");
			FrameworkBeans.findSessionBean().mberRole 		= resultMap.getStr("mberRole");
			FrameworkBeans.findSessionBean().moblphonNo 	= resultMap.getStr("moblphonNo");
			FrameworkBeans.findSessionBean().emailAddress 	= resultMap.getStr("emailAddress");

			insertLoginHistoryMap.put("loginrtn", "Y");
		}

		mLoginHistoryBiz.ModifyData( insertLoginHistoryMap );

		return new ResultMessage(resultCode, "success");
	}

	@RequestMapping(value = { "/RegisterData.do" })
	public ResultMessage RegisterData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultRegisterDataCount = 0;

		resultRegisterDataCount = mBiz.RegisterData( paramMap );

		resultMap.put("ResultDataCount", resultRegisterDataCount);

		return new ResultMessage( ResultCode.RESULT_OK, resultMap );
	}

	@RequestMapping(value = { "/ModifyData.do" })
	public ResultMessage ModifyData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultModifyDataCount 	= 0;

		resultModifyDataCount = mBiz.ModifyData( paramMap );

		resultMap.put("ResultDataCount", resultModifyDataCount);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	}

	@RequestMapping(value = { "/DeleteData.do" })
	public ResultMessage DeleteData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultDeleteDataCount 	= 0;

		resultDeleteDataCount = mBiz.DeleteData( paramMap );

		resultMap.put("ResultDataCount", resultDeleteDataCount);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	}
}
