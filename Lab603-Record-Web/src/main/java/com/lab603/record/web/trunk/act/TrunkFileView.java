package com.lab603.record.web.trunk.act;

import java.util.ArrayList;
import java.util.List;

public class TrunkFileView 
{
	public static List<String> documentView(String strTrunkName, String strMainNumber, String strTrunkIP, String strTurnkPort)
	{
		List<String> lines = new ArrayList<String>();
		
		lines.add(";[global]");
		lines.add(";type=global");
		lines.add(";user_agent=Asterisk-13");
		lines.add(";default_outbound_endpoint=dpma_endpoint");
		lines.add("");
		lines.add(";[0.0.0.0-udp]");
		lines.add(";type=transport");
		lines.add(";protocol=udp");
		lines.add(";bind=0.0.0.0:5060");
		lines.add(";allow_reload=yes");
		lines.add("");
		lines.add("["+strTrunkName+"]");
		lines.add("type=endpoint");
		lines.add("transport=0.0.0.0-udp");
		lines.add("context=from-pstn");
		lines.add("disallow=all");
		lines.add("allow=ulaw,alaw,gsm,g726,g722");
		lines.add("aors="+strTrunkName);
		lines.add("language=en");
		lines.add("outbound_auth="+strTrunkName);
		lines.add("t38_udptl=no");
		lines.add("t38_udptl_ec=none");
		lines.add("fax_detect=no");
		lines.add("t38_udptl_nat=no");
		lines.add("dtmf_mode=auto");
		lines.add("inband_progress=yes");
		lines.add("");
		lines.add(";[dpma_endpoint]");
		lines.add(";type=endpoint");
		lines.add(";context=dpma-invalid");
		lines.add("");
		lines.add("["+strTrunkName+"]");
		lines.add("type=aor");
		lines.add("qualify_frequency=60");
		lines.add("contact=sip:"+strMainNumber+"@"+strTrunkIP+":"+strTurnkPort);
		lines.add("");
		lines.add("["+strTrunkName+"]");
		lines.add("type=auth");
		lines.add("auth_type=userpass");
		lines.add("password="+strMainNumber);
		lines.add("username="+strMainNumber);
		lines.add("");
		lines.add("["+strTrunkName+"]");
		lines.add("type=registration");
		lines.add("transport=0.0.0.0-udp");
		lines.add("outbound_auth="+strTrunkName);
		lines.add("retry_interval=60");
		lines.add("max_retries=100");
		lines.add("expiration=3600");
		lines.add("auth_rejection_permanent=yes");
		lines.add("server_uri=sip:"+strTrunkIP+":"+strTurnkPort);
		lines.add("client_uri=sip:"+strMainNumber+"@"+strTrunkIP+":"+strTurnkPort);
		lines.add("");
		lines.add("["+strTrunkName+"]");
		lines.add("type=identify");
		lines.add("endpoint="+strTrunkName);
		lines.add("match="+strTrunkIP);
		
		return lines;
	}
}
