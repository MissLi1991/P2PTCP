package Unicast;

import java.text.SimpleDateFormat;  
import java.util.Date;  
/** 
 * 时间工具类 
 * @author admin_Hzw 
 * 
 */  
public class GetDate {  
    /** 
     * 时间格式到毫秒 
     */  
    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
    public static String getDate(){  
        return df.format(new Date());  
    }    
} 
