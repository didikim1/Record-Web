package com.lab603.record.web.framework.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("sessionBean")
@Scope(value="session")
public class SessionBean
{
	public String mberId 			= "";
	public String uniqId 			= "";
	public String mberName 			= "";
	public String mberSttus 		= "";
	public String mberRole 			= "";
	public String moblphonNo 		= "";
	public String emailAddress 		= "";
}

