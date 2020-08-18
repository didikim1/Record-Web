package com.lab603.record.web.trunk.act;

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
import com.lab603.record.web.trunk.biz.TrunkBiz;

@Controller
@RequestMapping("/trunk")
public class TrunkAct 
{
	final String pagePrefix = "trunk";

	private static final Logger logger = LoggerFactory.getLogger(TrunkAct.class);
	
	@Resource(name="com.lab603.record.web.trunk.biz.TrunkBiz")
	TrunkBiz mBiz;
	
	@RequestMapping(value = { "/" ,  "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap 			 paramMap 		  	  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap 			 findMainTrunkMap 	  = new MyMap();
		MyCamelMap		 resultMainTrunk	  = null;
		BasicBean 		 resultBean  	      = null;
		
		findMainTrunkMap.put("status", "A");
		resultMainTrunk = mBiz.SelectOneData( findMainTrunkMap );
		
		resultBean = mBiz.ListPagingData( paramMap );

		model.addAttribute("Data", 					resultBean);
		model.addAttribute("MainTrunk", 			resultMainTrunk);
		model.addAttribute("paramMap",  			paramMap);

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		return pagePrefix + "/SelectOneData";
	}

	@RequestMapping(value = { "/RegisterData.do" })
	public @ResponseBody ResultMessage RegisterData(Model model)
	{
		MyMap 				paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap 				rtnMap 				= new MyMap();
		StringBuffer		rtnDocument				= new StringBuffer();
		
		System.out.println("TrunkFileName:" + paramMap.getStr("TrunkFileName"));
		System.out.println("TrunkName:" + paramMap.getStr("TrunkName"));
		System.out.println("TrunkNumber:" + paramMap.getStr("TrunkNumber"));
		System.out.println("TrunkIP:" + paramMap.getStr("TrunkIP"));
		System.out.println("TrunkPort:" + paramMap.getStr("TrunkPort"));
		
		
		
		List<String> documentLines = TrunkFileView.documentView(paramMap.getStr("TrunkName"), paramMap.getStr("TrunkNumber"), paramMap.getStr("TrunkIP"), paramMap.getStr("TrunkPort"));
		
		for (String line : documentLines) 
		{
			rtnDocument.append(line + "<br/>");
		}
		
		rtnMap.put("data", 		rtnDocument.toString());
		rtnMap.put("fileName", 	paramMap.getStr("TrunkFileName"));
		
		
		
		return new ResultMessage(ResultCode.RESULT_OK, rtnMap);
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
	
	@RequestMapping(value = { "/ModifyMainTrunk.do" })
	public @ResponseBody ResultMessage ModifyMainTrunk(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		
		return mBiz.ModifyMainTrunk( paramMap );
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
