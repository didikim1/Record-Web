package com.lab603.record.web.trunk.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lab603.record.web.framework.beans.BasicBean;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.result.ResultCode;
import com.lab603.record.web.framework.result.ResultMessage;
import com.lab603.record.web.framework.utils.FrameworkPagingUtils;
import com.lab603.record.web.mapper.trunk.TrunkMapper;

@Service("com.lab603.record.web.trunk.biz.TrunkBiz")
public class TrunkBiz 
{
	@Resource(name="com.lab603.record.web.mapper.trunk.TrunkMapper")
	TrunkMapper mMapper;
	
	/**
	* 페이징 데이터
	* @param paramMap
	* @return
	*/
	public BasicBean ListPagingData(MyMap paramMap)
	{
		BasicBean resultBean = null;

		FrameworkPagingUtils.pagingRange(paramMap, paramMap.getInt("rows", 10));
		resultBean = FrameworkPagingUtils.pagingData(paramMap, paramMap.getInt("rows", 10),
				mMapper.SelectOnePagingCount(paramMap), mMapper.ListPagingData(paramMap));

		return resultBean;
	}

	/**
	* 상세 조회
	* @param paramMap
	* @return
	*/
	public MyCamelMap SelectOneData(MyMap paramMap)
	{
		return mMapper.SelectOneData(paramMap);
	}

	/**
	* 등록/수정
	* @param paramMap
	* @return
	*/
	public int RegisterData(MyMap paramMap)
	{
		return mMapper.RegisterData(paramMap);
	}
	
	/**
	* 수정
	* @param paramMap
	* @return
	*/
	public int ModifyData(MyMap paramMap)
	{
		return mMapper.ModifyData(paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public ResultMessage ModifyMainTrunk(MyMap paramMap)
	{
		String resultCode = ResultCode.RESULT_OK;
		String resultMsg  = "성공";
		
		int iRtnUpdateMain   = 0;
		int iRtnUpdateBackup = 0;
		int targetSeq	     = paramMap.getInt("seq");
		
		MyMap paramUpdateMain = new MyMap();
		paramUpdateMain.put("seq", 	  paramMap.getInt("seq"));
		paramUpdateMain.put("status", "A");
		
		iRtnUpdateMain = mMapper.ModifyData(paramUpdateMain);
		if ( iRtnUpdateMain >= 1 ) 
		{
			mMapper.ModifyMainData( paramUpdateMain );
			
			List<MyCamelMap> list = mMapper.ListData( new MyMap() );
			
			for (MyCamelMap info : list) 
			{
				int    iSeq 		= info.getInt("seq");
				String strStatus 	= info.getStr("status");
				
				System.out.println("iSeq:" + iSeq);
				System.out.println("strStatus:" + strStatus);
				
				if ( targetSeq == iSeq )    { continue; }
				if ( "B".equals(strStatus)) { continue; }
				
				MyMap paramUpdateBackup = new MyMap();
				
				paramUpdateBackup.put("seq", 	iSeq);
				paramUpdateBackup.put("status", "B");
				
				iRtnUpdateBackup = mMapper.ModifyData(paramUpdateBackup);
				
				if ( 0 >= iRtnUpdateBackup )
				{
					resultCode = ResultCode.RESULT_INTERNAL_SERVER_ERROR;
					resultMsg  = "이전 메인 트렁크를 비활성화하지 못함";
				}
			}
		}
		else
		{
			resultCode = ResultCode.RESULT_NOT_MODIFIED;
			resultMsg  = "선택된 트렁크로 변경하지 못함";
		}
		
		return new ResultMessage(resultCode, resultMsg);
	}
	
	/**
	* 삭제
	* @param paramMap
	* @return
	*/
	public int DeleteData(MyMap paramMap)
	{
		return mMapper.DeleteData(paramMap);
	}
}
