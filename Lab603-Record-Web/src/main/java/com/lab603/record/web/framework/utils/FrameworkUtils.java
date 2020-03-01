package com.lab603.record.web.framework.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
/**
 * @FileName : FrameworkUtils.java
 * @Project  : o2o-ui-auth-request
 * @Date     : 2016. 6. 12.
 * @작성자   : hsjeon1224(효성)
 * @변경이력 :
 * @프로그램 설명 :
 */
public class FrameworkUtils extends StringUtils{

	public final static String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";

	final static    LocalDateTime   kBaseTime       = LocalDateTime.of(2015, 1, 1, 0, 0, 0);

	public static synchronized String generateSessionID()
    {
        String          sessionID               = "";
        LocalDateTime   currentDate             = LocalDateTime.now();
        long            diffInSeconds           = ChronoUnit.SECONDS.between(kBaseTime, currentDate);
        int             currentSequenceNumber   = mSequenceNumber;

        diffInSeconds &= 0xffffffff;

        mSequenceNumber++;
        mSequenceNumber %= 10000;

        sessionID =
                "Local" +
                String.format("%08x", diffInSeconds) +
                String.format("%04d", currentSequenceNumber);

        return sessionID;
    }

	public static Long cookieTime()
    {
		SimpleDateFormat f  	= new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
		Date 			 d1 	= null;
		Date 			 d2 	= null;
		long 			 diff 	= 0l;
		long 			 sec 	= 0l;

		try
		{
			d1 		= f.parse("23:59:59");
			d2 		= f.parse(FrameworkUtils.getCurrentDateHour("HH:mm:ss"));
			diff 	= d1.getTime() - d2.getTime();
			sec 	= diff / 1000;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return sec;
    }

	public static String getCurrentDateHour(String strFormat)
	{
		SimpleDateFormat dayTime = new SimpleDateFormat(strFormat);
		return dayTime.format(new Date());
	}

	/**
	 * @Method Name  : convCamelCase
	 * @작성일    : 2016. 6. 12.
	 * @작성자    : hsjeon1224(효성)
	 * @변경이력  :
	 * @Method 설명 :
	 * @param _str
	 * @return
	 */
	public static String convCamelCase(String _str){
		String newColumnName=null;
		if(_str.indexOf("_")==-1){
			if(_str.matches("(.+)?[A-Z](.+)?")&&_str.matches("(.+)?[a-z](.+)?")) return _str;
			return _str.toLowerCase();
		}else{
			StringBuffer sb=new StringBuffer();
			boolean isFirst=true;
			StringTokenizer tokenizer=new StringTokenizer(_str,"_");
			while(tokenizer.hasMoreTokens()){
				if(isFirst) sb.append(tokenizer.nextToken().toLowerCase());
				else{
					sb.append(StringUtils.capitalize(tokenizer.nextToken().toLowerCase()));
				}
				isFirst=false;
			}
			newColumnName=sb.toString();
		}
		return newColumnName;
	}

	/**
	 * <pre>
	 * @Method Name  : unescapeHtml
	 * @작성일    : 2016. 6. 12.
	 * @작성자    : hsjeon1224(효성)
	 * @변경이력  :
	 * @Method 설명 :
	 * @param values
	 * @return
	 * </pre>
	 */
	public static String unescapeHtml(String values){
		if(values==null) return "";
		values=values.replaceAll("&lt;","<");
		values=values.replaceAll("&gt;",">");
		values=values.replaceAll("&amp;","&");
		values=values.replaceAll("&#38;","&");
		values=values.replaceAll("&quot;","\"");
		values=values.replaceAll("&#34;","\"");
		values=values.replaceAll("&#39;","'");
		values=values.replaceAll("&#36;","\\$");
		return values;
	}

	/**
	 * <pre>
	 * @Method Name  : escapeHtml
	 * @작성일    : 2016. 6. 12.
	 * @작성자    : hsjeon1224(효성)
	 * @변경이력  :
	 * @Method 설명 :
	 * @param values
	 * @return
	 * </pre>
	 */
	public static String escapeHtml(String values){
		if(values==null) return "";
		values=values.replaceAll("<(no)?script[^>]*>.*?</(no)?script>","");
		values=values.replaceAll("<style[^>]*>.*</style>","");
		values=values.replaceAll("&(?![#]?[a-z0-9]+;)","&#38;");
		values=values.replaceAll("<","&lt;");
		values=values.replaceAll(">","&gt;");
		values=values.replaceAll("\"","&#34;");
		values=values.replaceAll("'","&#39;");
		values=values.replaceAll("\\$","&#36;");
		return values;
	}

	 public static boolean isNull(String str) {
        if (str == null || "".equals(str) || str.trim().length() == 0)
            return true;
        else
            return false;
    }

	public enum SecureHashAlgorithm
	{
			 SHA1
			,SHA_256
	}

	public static String msgSecureHashAlgorithm(String str, SecureHashAlgorithm digest)
 	{
 		String 		  rtnValue = "";
 		MessageDigest sh 	   = null;

 		if ( str == null) return "";

 		try
 		{
 			switch ( digest )
 			{
				case SHA1:
					sh = MessageDigest.getInstance("SHA1");
					break;
				case SHA_256:
					sh = MessageDigest.getInstance("SHA-256");
					break;
				default:
					sh = MessageDigest.getInstance("SHA1");
					break;
				}
 			sh.update(str.getBytes());
 			byte byteData[] = sh.digest();
 			StringBuffer sb = new StringBuffer();
 			for(int i = 0 ; i < byteData.length ; i++)
 			{
 				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
 			}
 			rtnValue = sb.toString();
 		}
 		catch(NoSuchAlgorithmException e)
 		{
 			e.printStackTrace();
 			rtnValue = null;
 		}
 		return rtnValue;
 	}

 	public static String msgSecureHashAlgorithm(String str)
 	{
 		return msgSecureHashAlgorithm(str, SecureHashAlgorithm.SHA1);
 	}


	/////////////////////////////////////////////////////////////////////////////////////////
	//	   Array
    /////////////////////////////////////////////////////////////////////////////////////////
	public static int inArray(String[] arrStr,String str)
	 {
		if(str==null) return -1;

		for(int i=0;i<arrStr.length;i++)
		{
			if(arrStr[i].trim().equals(str.trim())) return i;
		}
		return -1;
	}
    /////////////////////////////////////////////////////////////////////////////////////////
	//		Date
	/////////////////////////////////////////////////////////////////////////////////////////

	public static String getDateToStr(Date date, String dateFormat)
	{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
	}

	public static String getDateToStr(String dateFormat)
	{
		return getDateToStr(new Date(), dateFormat);
	}

	public static String getDateToStr()
	{
		return getDateToStr(new Date(), DEFAULT_DATE_FORMAT);
	}


    public static boolean isNotNull(String str) {
        return !isNull(str);
    }
	public static int nvl(String i,int def){
		return(i==null?def:FrameworkUtils.toInt(i));
	}
	public static int nvl(Object i,int def){
		return(i==null?def:FrameworkUtils.toInt(i));
	}
	public static int nvl(int i,int def){
		return(i==0?def:i);
	}
	public static long nvl(long i,long def){
		return(i==0?def:i);
	}
	public static String nvl(String str,String def){
		str=str==null?"":str;
		return(str.equals("")?def:str.trim());
	}
	public static String nvl(Object object,String def){
		String str=object==null?"":object.toString();
		return(str.equals("")?def:str.trim());
	}
	public static String nvl(int object,String def){
		return object==0?def:FrameworkUtils.toString(object);
	}
	public static Object NVL(Object object,Object def){
		return object==null?def:object;
	}
	public static int toInt(String s){
		try{s=FrameworkUtils.nvl(s,"0");return Integer.parseInt(s);}catch(Exception e){return -1;}
	}
	public static int toInt(Long s){
		return Integer.parseInt(FrameworkUtils.toString(Math.round(s)));
	}
	public static int toInt(Double s){
		return Integer.parseInt(FrameworkUtils.toString(Math.round(s)));
	}
	public static int toInt(Object s){
		s=FrameworkUtils.nvl(s,"0");return toInt(s.toString());
	}
	public static int toInt(Object s,int def){
		s=FrameworkUtils.nvl(s,def);return toInt(s.toString());
	}
	public static String toString(Object i){
		try{return String.valueOf(i);}catch(Exception e){return "";}
	}
	public static String toString(Object i,String def){
		try{return String.valueOf(i);}catch(Exception e){return def;}
	}
	public static List<String> diffDates(String startDate, String endDate)
	{
		List<String> list = new ArrayList<String>();
		String s1=startDate.replaceAll("[^\\d]", "");
		String s2=endDate.replaceAll("[^\\d]", "");

		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		DateFormat df1 = new SimpleDateFormat("yyyyMMdd");

		//Date타입으로 변경

		Date d1 = null;
		Date d2 = null;
		try
		{
			d1 = df.parse( s1 );
			d2 = df.parse( s2 );
		}
		catch (java.text.ParseException e)
		{
			e.printStackTrace();
		}

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime( d1 );
		c2.setTime( d2 );

		while( c1.compareTo( c2 ) !=1 )
		{
			list.add(df1.format(c1.getTime()));
			//시작날짜 + 1 일
			c1.add(Calendar.DATE, 1);
		}
		return list;
	}

	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }

	    return ip;
	}

	public static String getCurrentDate(String strFormat)
	{
		SimpleDateFormat dayTime = new SimpleDateFormat(strFormat);
		return dayTime.format(new Date());
	}

	private     static int      mSequenceNumber = 0;
}
