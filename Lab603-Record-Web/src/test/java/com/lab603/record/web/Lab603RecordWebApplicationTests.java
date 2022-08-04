package com.lab603.record.web;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lab603.record.web.comtn.biz.TTSServerInfoBiz;
import com.lab603.record.web.comtn.biz.dto.TTSWavDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Lab603RecordWebApplicationTests {


	@Resource(name="com.lab603.record.web.comtn.biz.TTSServerInfoBiz")
	TTSServerInfoBiz mBiz;

	@Test
	public void contextLoads()
	{
		System.out.println("@@@");
		TTSWavDTO dto = mBiz.ttsMake("안녕하세요.");

		System.out.println( dto.toString());
	}

}
