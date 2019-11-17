package xalead.springmvc.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private List<Channel> cs;
}
