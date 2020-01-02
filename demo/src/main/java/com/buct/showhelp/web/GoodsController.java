package com.buct.showhelp.web;

import com.buct.showhelp.mapper.GoodsMapper;
import com.buct.showhelp.pojo.Goods;
import com.buct.showhelp.pojo.Users;
import com.buct.showhelp.service.GoodsService;
import org.apache.catalina.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import sun.misc.Request;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    static Users users;

    @RequestMapping("/index")
    public String listOnSaleGoods(Model model, HttpSession session){
        List<Goods> list = goodsService.findOnSaleGoods();
        model.addAttribute("goodslist", list);
        users = (Users) session.getAttribute("Session_user");
        if(users != null) {
            model.addAttribute("user", users.getName());
        }
        else {
            model.addAttribute("user", "请登录");
        }
        return "index";
    }

    @RequestMapping("/myGoods")
    public String listMyGoods(Model model){
        List<Goods> list = goodsService.findMyOnSaleGoods(users.getId());
        model.addAttribute("goodslist", list);
        model.addAttribute("user", users.getName());
        return "myGoods";
    }

    @RequestMapping("/myGoods/addGoodsPage")
    public String addGoodsPage(){
        return "addGoods";
    }

//    title detail price purchaseUrl location number
    @RequestMapping("/myGoods/addGoods")
    public String addGoods(@RequestParam("title") String title, @RequestParam("detail") String detail,
                        @RequestParam("price") double price, @RequestParam("purchaseUrl") String purchaseUrl,
                        @RequestParam("location") String location, @RequestParam("number") int number){
        Goods goods = new Goods();
        goods.setTitle(title);
        goods.setDetail(detail);
        goods.setLocation(location);
        goods.setNumber(number);
        goods.setPurchaseUrl(purchaseUrl);
        goods.setPrice(price);
        goods.setSellerid(users.getId());
        int result = goodsService.addGoods(goods);
        if(result == 0){
            return "error";
        }
        return "redirect:../myGoods";
    }
}
