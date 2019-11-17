package xalead.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {

    public Date convert(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date d=null;
        try {
            d = format.parse(s);    //字符串转为时间
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }
}
