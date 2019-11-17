package xalead.service;

import org.springframework.stereotype.Service;
import xalead.dao.GoodsMapper;
import xalead.entity.Goods;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    public List<Goods> goodsList() {
        return goodsMapper.goodsList();
    }
}
