package xalead.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xalead.entity.Goods;
import xalead.service.GoodsService;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/goods")
@Controller
public class GoodsController {
    @Resource
    private GoodsService goodsService;

//    @GetMapping("/list")
//    public ResponseEntity<List<Goods>> goodsList(){
//        Logger logger = LoggerFactory.getLogger(getClass());
//        logger.trace("追踪服务!");
//        return ResponseEntity.ok(goodsService.goodsList());
//    }

    @GetMapping("/list")
    public String goodsList(Model model){
        List<Goods> goods = goodsService.goodsList();
       model.addAttribute("goods",goods);
        return "list";
    }
}
