package com.buct.showhelp.web;

import com.buct.showhelp.mapper.GoodsMapper;
import com.buct.showhelp.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("/index")
    public String listOnSaleGoods(Model model){
        List<Goods> list = goodsMapper.findOnSaleGoods();
        model.addAttribute("goodslist", list);
        return "index";
    }
}
