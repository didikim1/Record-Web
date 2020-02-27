package com.lab603.record.web.recordplay.act;

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
import com.lab603.record.web.recordplay.biz.RecordPlayBiz;

@Controller
@RequestMapping("/recordPlay")
public class RecordPlayAct
{
	final String pagePrefix = "recordPlay";

	private static final Logger logger = LoggerFactory.getLogger(RecordPlayAct.class);


	@Resource(name="com.lab603.record.web.recordplay.biz.RecordPlayBiz")
	RecordPlayBiz mBiz;

	@RequestMapping(value = { "/" ,  "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap 		paramMap 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		BasicBean 	resultBean  = null;

		resultBean = mBiz.ListPagingData( paramMap );

		model.addAttribute("Data", 		resultBean);
		model.addAttribute("paramMap",  paramMap);

		return pagePrefix + "/ListPagingData";
	}

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		MyMap 		paramMap 	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 	= new MyCamelMap();
		String 		strPlayUrl	= null;

		String		strServerIP	= null;
		String		strWavDir	= null;
		String		strWavFile	= null;

		resultMap = mBiz.SelectOneData( paramMap );

		logger.debug( resultMap.toString() );

		strServerIP = resultMap.getStr("serverIp");
		strWavDir 	= resultMap.getStr("dirname");
		strWavFile 	= resultMap.getStr("filename");

		if ( "127.0.0.1".equals( strServerIP ))
		{
			strServerIP = "211.61.220.42";
		}

		strWavDir	= strWavDir.replace("/var/spool/asterisk", "");
		strPlayUrl  = "http://"+strServerIP+"/"+strWavDir+"/"+strWavFile+".wav";

		resultMap.put("mp3path", strPlayUrl);

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
