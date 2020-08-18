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
            String nowtime=sqlFormat.format(nowDate);
            Date date=sqlFormat.parse(sqltime);

            long l=nowDate.getTime()-date.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            //long second=(l/1000-day*24*60*60-hour*60*60-min*60);
            if(day<1 && min<1)
                return "刚刚";
            else if(day<1 && min<60)
                return min+"分钟前";
            else if(day<1 && hour<24)
                return hour+"小时"+min+"分钟前";
            else
                return sqltime.substring(0,16);
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

}
