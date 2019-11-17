package xalead.springmvc.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Channel {
    private int cid;
    private String cname;
    private String description;

    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    private Date date;
}
