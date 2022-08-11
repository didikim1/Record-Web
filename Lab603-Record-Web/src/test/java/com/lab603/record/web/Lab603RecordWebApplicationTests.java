package com.lab603.record.web;

import java.io.Console;
import java.io.File;

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
	
    @Test
    public void test() {
        File targetFolder = new File("D:\\Temp50\\");
 //       File targetFolder = new File("D:\\Temp50\\sh");
        boolean isDelete = deleteDirectoryAndFiles(targetFolder);
    }
    
    private boolean deleteDirectoryAndFiles(File targetFolder) {
        if(!targetFolder.exists()) {
            return false;
        }
        
        File[] files = targetFolder.listFiles();
        for(File file : files) {
            if(file.isDirectory()) {
                deleteDirectoryAndFiles(file);
            }
            file.delete();
        }
        
        return targetFolder.delete();
    }

}
