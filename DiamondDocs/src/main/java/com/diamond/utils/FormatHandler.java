package com.diamond.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormatHandler {
    public static String AlterTimeFormat(String sqltime){
        try {
            SimpleDateFormat sqlFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date nowDate=new Date();
            Date date=sqlFormat.parse(sqltime);

            long l=nowDate.getTime()-date.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            //long second=(l/1000-day*24*60*60-hour*60*60-min*60);
            if(day>3)
                return sqltime.substring(0,16);
            else if(day>0)
                return day+"天前";
            else if(hour>0)
                return hour+"小时前";
            else if(min>2)
                return min+"分钟前";
            else
                return "刚刚";
        }
        catch (Exception e){
            e.printStackTrace();
            return "time error";
        }
    }

    public static String AlterNumFormat(int num){
        try {
            StringBuilder s=new StringBuilder();
            if(num<1000)
                return Integer.toString(num);
            else if(num<10000){
                int a=num/1000;
                int b=num/100%10;
                s.append(a);
                s.append(".");
                s.append(b);
                s.append("k");
            }
            else{
                int a=num/10000;
                int b=num/1000%10;
                s.append(a);
                s.append(".");
                s.append(b);
                s.append("w");
            }
            return s.toString();
        }
        catch (Exception e){
            System.out.println("数字转换异常");
            e.printStackTrace();
            return "null";
        }
    }

    /*
    将前端转化成数组的字符串转成字符串数组
    前端传递形式形如 "1=ABCDE&2=EDSWE&3=WWWSW"
    */
    public static List<String> getListString(String strs) {
        List<String> list = new ArrayList<>();
        String[] tuples = strs.split("&");
        for(String tuple : tuples){
            String str = tuple.split("=")[1];
            list.add(str);
        }
        return list;
    }

    /*
    * 笨方法转化
    * 转化要求：10字节内容+两字节省略号
    */
    public static String getPreviewTitle(String string) {
        int length = 0;
        StringBuilder title = new StringBuilder();
        for(int i = 0 ; i < string.length() ; i++){
            char c = string.charAt(i);
            if(c>=19968 && c<=40869 && length <= 8) {
                length = length + 2;
            }
            else if(!(c>=19968 && c<=40869) && length <= 9){
                length ++;
            }
            else
                break;
            if(length < 10){
                title.append(c);
            }
            else if(length == 10){
                title.append(c);
                break;
            }
        }
        if(!string.equals(title))
            title.append("…");
        return title.toString();
    }
}
