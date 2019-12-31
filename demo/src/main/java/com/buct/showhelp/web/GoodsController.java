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

    public static class Saled {
        String id;
        String sellerId;
        String buyerId;
        String goodsId;
        String saleTime;
        String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSellerId() {
            return sellerId;
        }

        public void setSellerId(String sellerId) {
            this.sellerId = sellerId;
        }

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getSaleTime() {
            return saleTime;
        }

        public void setSaleTime(String saleTime) {
            this.saleTime = saleTime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
