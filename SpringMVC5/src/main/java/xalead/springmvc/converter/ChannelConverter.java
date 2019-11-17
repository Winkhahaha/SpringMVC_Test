package xalead.springmvc.converter;

import org.springframework.core.convert.converter.Converter;
import xalead.springmvc.entity.Channel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
将字符串转为channel对象
???:???:???:???
 */
public class ChannelConverter implements Converter<String, Channel> {
    public Channel convert(String s) {

        String[] ss = s.split(":");
        Channel channel = new Channel();
        channel.setCid(Integer.parseInt(ss[0]));
        channel.setCname(ss[1]);
        channel.setDescription(ss[2]);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            channel.setDate(format.parse(ss[3]));   //字符串转换为日期
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return channel;
    }
}
