package com.buct.showhelp.web;

import com.buct.showhelp.mapper.GoodsMapper;
import com.buct.showhelp.mapper.OrdersMapper;
import com.buct.showhelp.pojo.Goods;
import com.buct.showhelp.pojo.GoodsPicPath;
import com.buct.showhelp.pojo.Orders;
import com.buct.showhelp.pojo.Users;
import com.buct.showhelp.service.GoodsService;
import com.buct.showhelp.service.OrderService;
import com.buct.showhelp.service.PictureService;
import com.buct.showhelp.service.UserService;
import com.buct.showhelp.utils.Email;
import com.buct.showhelp.utils.Global;
import com.buct.showhelp.utils.Utils;
import org.apache.catalina.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    static Users users;

//    todo 分页 判断自己的物品

    @RequestMapping("/index")
    public String listOnSaleGoods(Model model, HttpSession session){
        List<Goods> list = goodsService.findOnSaleGoods();
        model.addAttribute("goodslist", list);
        users = (Users) session.getAttribute("Session_user");
        if(users != null) {
            model.addAttribute("user", users.getName());
            model.addAttribute("url", "/myInformation/");
        }
        else {
            model.addAttribute("user", "请登录");
            model.addAttribute("url", "/user/");
        }
        return "index";
    }

    @RequestMapping("/myGoods")
    public String listMyGoods(Model model, HttpServletRequest request){
        List<Goods> list = goodsService.findGoodsByStatus(Utils.getUserSession(request).getId(), Global.GOODS_STATUS_ON_SALE);
        model.addAttribute("goodslist", list);
        model.addAttribute("user", Utils.getUserSession(request).getName());
        return "myGoods";
    }

    @RequestMapping("/myGoods/offLineGoodsPage")
    public String hidingGoods(Model model, HttpServletRequest request){
        List<Goods> list = goodsService.findGoodsByStatus(Utils.getUserSession(request).getId(), Global.GOODS_STATUS_HIDING);
        model.addAttribute("goodslist", list);
        model.addAttribute("user", Utils.getUserSession(request).getName());
        return "/goods/offLineGoods";
    }
    @RequestMapping("/myGoods/offLineGoods")
    public String hidingGoods(@RequestParam("id") int id){
        Goods goods = goodsService.findGoodsById(id);
        goods.setStatus(Global.GOODS_STATUS_HIDING);
        goodsService.updateGoodsStatus(goods);
        return "redirect:../myGoods";
    }
    @RequestMapping("/myGoods/onLineGoods")
    public String onSaleGoods(@RequestParam("id") int id){
        Goods goods = goodsService.findGoodsById(id);
        goods.setStatus(Global.GOODS_STATUS_ON_SALE);
        goodsService.updateGoodsStatus(goods);
        return "redirect:./offLineGoodsPage";
    }

    @RequestMapping("/myGoods/deleteGoods")
    public String deleteGoods(@RequestParam("id") int id, @RequestParam("page") String page){
        goodsService.delete(id);
        if(page.equals(Global.GOODS_STATUS_ON_SALE))
            return "redirect:../myGoods";
        else
            return "redirect:./offLineGoodsPage";
    }

    @RequestMapping("/myGoods/dealingGoods")
    public String dealingGoods(Model model, HttpServletRequest request){
        List<Goods> list = goodsService.findGoodsByStatus(Utils.getUserSession(request).getId(), Global.GOODS_STATUS_LOCK);
        for (Goods goods: list) {
            goods.setOrderIdBuffer(orderService.findOrdersByStatusAndGoods(goods.getId(), Global.ORDER_STATUS_WAITING).getId());
        }

        model.addAttribute("goodslist", list);
        model.addAttribute("user", Utils.getUserSession(request).getName());
        return "/goods/dealingGoods";
    }

    @RequestMapping("/myGoods/soldGoods")
    public String soldGoods(Model model, HttpServletRequest request){
        List<Goods> list = goodsService.findGoodsByStatus(Utils.getUserSession(request).getId(), Global.GOODS_STATUS_SOLD);
        model.addAttribute("goodslist", list);
        model.addAttribute("user", Utils.getUserSession(request).getName());
        return "/goods/soldGoods";
    }

    @RequestMapping("/myGoods/addGoodsPage")
    public String addGoodsPage(){
        return "addGoods";
    }

//    title detail price purchaseUrl location number
    @RequestMapping("/myGoods/addGoods")
    public String addGoods(@RequestParam("title") String title, @RequestParam("detail") String detail,
                           @RequestParam("price") float price, @RequestParam("purchaseUrl") String purchaseUrl,
                           @RequestParam("location") String location, @RequestParam("number") int number,
                           @RequestParam(value = "file") MultipartFile file){
        Goods goods = new Goods();
        if(!file.isEmpty()){
            goods.setPicPath(Utils.saveFile(file, Global.DEFAULT_GOODS_PATH+Utils.getDate()));
        }
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

    //展示修改页面
    @RequestMapping("/myGoods/updateGoodsPage")
    public String showUpdateGoodsPage(@RequestParam("id") int id, Model model){
        model.addAttribute("updateGoods", goodsService.findGoodsById(id));
        return "updateGoodsPage";
    }

    //更新某物品
    @RequestMapping("/myGoods/updateGoods")
    public String updateGoods(HttpServletRequest request,
                              @RequestParam("title") String title, @RequestParam("detail") String detail,
                              @RequestParam("price") float price, @RequestParam("purchaseUrl") String purchaseUrl,
                              @RequestParam("location") String location, @RequestParam("number") int number,
                              @RequestParam("id") int id, @RequestParam(value = "file") MultipartFile file){
        Goods goods = goodsService.findGoodsById(id);
        if(!file.isEmpty()){
            goods.setPicPath(Utils.saveFile(file, Global.DEFAULT_GOODS_PATH+Utils.getDate()));
        }
        goods.setTitle(title);
        goods.setDetail(detail);
        goods.setLocation(location);
        goods.setNumber(number);
        goods.setPurchaseUrl(purchaseUrl);
        goods.setPrice(price);
        goods.setSellerid(Utils.getUserSession(request).getId());
        int result = goodsService.updateGoods(goods);
        if(result == 0){
            return "修改失败";
        }
        return "redirect:/index/showGoods?id="+goods.getId();
    }

    //更新状态 点赞、访问<-局部刷新 在售状态变化

    //展示一个物品
    @RequestMapping("index/showGoods")
    public String showOneGoods(Model model, @RequestParam("id") int id){
        Goods goods = goodsService.findGoodsById(id);
        model.addAttribute("oneGoods", goods);
        // todo if isMine change page to edit else want and chat
        if(users.getId() == goods.getSellerid())
            model.addAttribute("ismine", true);
        else
            model.addAttribute("ismine", false);
        return "showGoods";
    }

    /**
     * 交易相关
     */
    @RequestMapping("/buyGoods")
    public String buyGoods(@RequestParam("goodsid") int goodsid, HttpServletRequest request){
        Goods goods = goodsService.findGoodsById(goodsid);
        if(!goods.getStatus().equals(Global.GOODS_STATUS_ON_SALE)) {
            return "没有了额";
        }
        Users user = Utils.getUserSession(request);
        if(user.getId() == goods.getSellerid()){
            //todo tips
            return "不能买自己的额";
        }

        goodsService.buyGoods(user.getId(), goodsid, Utils.getTime(), Global.GOODS_STATUS_LOCK);
        Orders order = new Orders();
        order.setBuyerId(user.getId());
        order.setGoodsId(goodsid);
        order.setPrice(goods.getPrice());
        order.setSellerId(goods.getSellerid());
        order.setContent(user.getName()+"请求交易,等待卖家回复进行线下交易");
        orderService.addOrder(order);
        Users seller = userService.findUserById(order.getSellerId());
        Users buyer = userService.findUserById(order.getBuyerId());
        Email.sendMail(seller.getEmail(), Email.askForDeal(seller, buyer, goods));
        //todo email and dialog
        return "redirect:/asBuyer/request";
    }

    //buyer cancel reason
    @RequestMapping("/buyGoods/buyerCancel")
    public String buyerCancelDeal(@RequestParam("id") int orderId, @RequestParam("content") String content){
        orderService.updateOrder(orderId, Global.ORDER_STATUS_BUYER_CANCEL, "买家取消/n"+content);
        Orders orders = orderService.findOrdersById(orderId);
        Goods goods = goodsService.findGoodsById(orders.getGoodsId());
        goods.setStatus(Global.GOODS_STATUS_ON_SALE);
        goods.setBuyerid(0);
        goodsService.updateGoodsStatus(goods);
        return "redirect:/asBuyer/request";
    }

    //seller refuse
    @RequestMapping("/buyGoods/sellerCancel")
    public String sellerCancelDeal(@RequestParam("id") int orderId, @RequestParam("content") String content){
        orderService.updateOrder(orderId, Global.ORDER_STATUS_SELLER_CANCEL, "卖家取消/n"+content);
        Orders orders = orderService.findOrdersById(orderId);
        Goods goods = goodsService.findGoodsById(orders.getGoodsId());
        goods.setStatus(Global.GOODS_STATUS_ON_SALE);
        goods.setBuyerid(0);
        goodsService.updateGoodsStatus(goods);
        return "redirect:/myGoods/dealingGoods";
    }

    //success
    //seller refuse
    @RequestMapping("/buyGoods/accept")
    public String gotDeal(@RequestParam("id") int orderId, @RequestParam("content") String content){
        orderService.updateOrder(orderId, Global.ORDER_STATUS_SUCCESS, "交易成功/n"+content);
        Orders orders = orderService.findOrdersById(orderId);
        Goods goods = goodsService.findGoodsById(orders.getGoodsId());
        goods.setStatus(Global.GOODS_STATUS_SOLD);
        goodsService.updateGoodsStatus(goods);
        Users seller = userService.findUserById(orders.getSellerId());
        Users buyer = userService.findUserById(orders.getBuyerId());
        Email.sendMail(seller.getEmail(), Email.acceptDeal(seller, buyer, goods));
        return "redirect:/myGoods/dealingGoods";
    }
    /**
     * 我买到的
     */
    @RequestMapping("/asBuyer/order")
    public String showMyOrder(Model model, HttpServletRequest request){
        List<Goods> list = goodsService.findWantByStatus(Utils.getUserSession(request).getId(), Global.GOODS_STATUS_SOLD);
        for (Goods goods: list) {
            System.out.println(goods.getId()+" "+Global.ORDER_STATUS_SUCCESS);
            goods.setOrderIdBuffer(orderService.findOrdersByStatusAndGoods(goods.getId(), Global.ORDER_STATUS_SUCCESS).getId());
        }
        model.addAttribute("goodsList", list);
        return "asBuyer/showMyOrder";
    }

    @RequestMapping("/asBuyer/request")
    public String showMyRequest(Model model, HttpServletRequest request){
        List<Goods> list = goodsService.findWantByStatus(Utils.getUserSession(request).getId(), Global.GOODS_STATUS_LOCK);
        for (Goods goods: list) {
            System.out.println(goods.getId()+" ");
            goods.setOrderIdBuffer(orderService.findOrdersByStatusAndGoods(goods.getId(), Global.ORDER_STATUS_WAITING).getId());
        }
        model.addAttribute("goodsList", list);
        return "asBuyer/showMyRequest";
    }

}
