package xalead.dao;

import org.apache.ibatis.annotations.Select;
import xalead.entity.Goods;

import java.util.List;

public interface GoodsMapper {

    @Select("select * from goods")
    public List<Goods> goodsList();
}
