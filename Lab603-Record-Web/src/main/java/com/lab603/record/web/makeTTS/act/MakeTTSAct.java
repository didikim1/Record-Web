package com.lab603.record.web.makeTTS.act;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.lab603.record.web.makeTTS.biz.MakeTTSBiz;
import com.lab603.record.web.trunk.biz.TrunkBiz;

@Controller
@RequestMapping("/makeTTS")
public class MakeTTSAct 
{
	final String pagePrefix = "makeTTS";

	private static final Logger logger = LoggerFactory.getLogger(MakeTTSAct.class);
	
	@Resource(name="com.lab603.record.web.makeTTS.biz.MakeTTSBiz")
	MakeTTSBiz mBiz;
	
	@RequestMapping(value = { "/" ,  "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap 			 paramMap 		  	  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		BasicBean 		 resultBean  	      = null;
		
		resultBean = mBiz.ListPagingData( paramMap );
		
		model.addAttribute("Data", 					resultBean);
		model.addAttribute("paramMap",  			paramMap);

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		MyMap                 paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap     		  resultMap       = new MyCamelMap();

        resultMap = mBiz.SelectOneData(paramMap);

        model.addAttribute("paramMap",      paramMap);
        model.addAttribute("Data",          resultMap);

		return pagePrefix + "/SelectOneData";
	}
	
	
	@RequestMapping(value = { "/RegisterContent.do" })
	public String RegisterContent(Model model)
	{
		MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap resultMap       = null;

		resultMap  = mBiz.SelectOneData(paramMap);

		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);

		model.addAttribute("paramMap",      paramMap);
		model.addAttribute("Info",          resultMap);

		return pagePrefix + "/RegisterContent";
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
		MyMap 	paramMap 				  	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		String 	resultCode			  		= ResultCode.RESULT_INTERNAL_SERVER_ERROR;
		int   	resultRegisterDataCount 	= 0;

		resultRegisterDataCount = mBiz.DeleteData( paramMap );

		if( resultRegisterDataCount > 0)
		{
			resultCode			  		= ResultCode.RESULT_OK;
		}

		 model.addAttribute("paramMap",      paramMap);

		 return new ResultMessage(ResultCode.RESULT_OK, null);
	}
}
