package xalead.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringConverter implements Converter<Date,String> {

    public String convert(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String time = format.format(date);       //日期转字符串
        return time;
    }
}
