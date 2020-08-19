package com.diamond.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormatHandler {
    public static String AlterTimeFormat(String sqltime){
        try {
            SimpleDateFormat sqlFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sqlFormat.parse(sqltime);
            Date nowDate=new Date();
            String nowtime=sqlFormat.format(nowDate);

            long l=nowDate.getTime()-date.getTime();
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            //long second=(l/1000-day*24*60*60-hour*60*60-min*60);

            int nowYear = Integer.parseInt(nowtime.substring(0,4));
            int nowMonth = Integer.parseInt(nowtime.substring(5,7));
            int nowDay = Integer.parseInt(nowtime.substring(8,10));
            int sqlYear = Integer.parseInt(sqltime.substring(0,4));
            int sqlMonth = Integer.parseInt(sqltime.substring(5,7));
            int sqlDay = Integer.parseInt(sqltime.substring(8,10));

            if(day<1 && min<1)
                return "刚刚";
            else if(day<1 && min<60 && hour<1)
                return min+"分钟前";
            else if(day<1 && hour<24)
                return hour+"小时"+min+"分钟前";
            else if(nowYear != sqlYear)
                return sqltime.substring(0,16);
            else if(nowMonth == sqlMonth && nowDay == sqlDay+1)
                return "昨天 "+sqltime.substring(11,16);
            else if(nowMonth == sqlMonth && nowDay == sqlDay+2)
                return "前天 "+sqltime.substring(11,16);
            else
                return sqltime.substring(5,16);
        }
        catch (Exception e){
            e.printStackTrace();
            return "time error";
        }
    }

    //判断两个字符串类型时间的时间差，单位为分钟
    public static long calculateTimeDifference(String time1, String time2){
        try{
            SimpleDateFormat sqlFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sqlFormat.parse(time1);
            Date date2 = sqlFormat.parse(time2);
            long l=date2.getTime()-date1.getTime();
            return l/(60*1000);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1000000000;
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
