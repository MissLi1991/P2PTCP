package Unicast;

import java.text.SimpleDateFormat;  
import java.util.Date;  
/** 
 * ʱ�乤���� 
 * @author admin_Hzw 
 * 
 */  
public class GetDate {  
    /** 
     * ʱ���ʽ������ 
     */  
    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
    public static String getDate(){  
        return df.format(new Date());  
    }    
} 
