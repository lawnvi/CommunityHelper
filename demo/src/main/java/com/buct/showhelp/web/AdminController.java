package com.buct.showhelp.web;

import com.buct.showhelp.pojo.*;
import com.buct.showhelp.service.AdminService;
import com.buct.showhelp.service.GoodsService;
import com.buct.showhelp.service.OrderService;
import com.buct.showhelp.service.UserService;
import com.buct.showhelp.utils.Global;
import com.buct.showhelp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    /**
     * register
     */
    @RequestMapping("/register")
    public String registerPage(){
        return "/admin/register";
    }
    @RequestMapping("/registerMethod")
    public String userRegister(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("password") String psw, @RequestParam("password2") String psw2){
        if(!psw.equals(psw2)){
            return "密码不一致，请确认密码再注册！";
        }
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setName(name);
        admin.setPassword(psw);
        admin.setPicPath(" ");
        int result = adminService.registerAdmin(admin);
        if(result == 0){
            return "注册失败！";
        } else {
            return "注册成功！";
        }
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "/admin/login";
    }

    @RequestMapping("/loginMethod")
    public String adminLogin(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password){
        Admin admin = adminService.findAdmin(email, password);
        if(admin == null){
            return "登录失败";
        }
        request.getSession().setAttribute("AdminObj", admin);
        return "redirect:/admin/logs";
    }

    /**
     * 修改信息
     * @return
     */
    @RequestMapping("/changePasswordPage")
    public String changePasswordPage(){
        return "/admin/changePassword";
    }
    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam("oldPassword") String psw,
                                 @RequestParam("newPassword1") String psw1,
                                 @RequestParam("newPassword2") String psw2,
                                 HttpServletRequest request){
//        userService.findUserById(Utils.getUserSession(request).getId())
        if(!psw1.equals(psw2)){
            //todo 弹窗
            return "确认密码不一致";
        }
        if(!psw.equals(adminService.findAdminById(Utils.getAdmin(request).getId()).getPassword())){
            return "原密码错误";
        }
        adminService.changePassword(psw1, Utils.getAdmin(request).getId());
        return "redirect:/admin/setting";
    }

    @RequestMapping("/updateAdmin")
    public String updateUserPage(Model model, HttpServletRequest request){
        model.addAttribute("user", Utils.getAdmin(request));
        return "admin/updateAdmin";
    }
    @RequestMapping("/updateAdminMethod")
    public String updateUser(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("address") String address,
                             @RequestParam("tel") String tel, @RequestParam("school") String school, @RequestParam("picturePath") String picturePath){
        Admin users = new Admin();
        users.setId(id);
        users.setName(name);
        users.setTel(tel);
        users.setPicPath(picturePath);
        users.setEmail(Utils.getUserSession(request).getEmail());
        int result = adminService.updateAdmin(users);
        if(result == 0)
            return "更新失败";
        request.getSession().setAttribute("Session_user", users);
        return "redirect:/admin/setting";
    }

    @RequestMapping("/logs")
    public String showLog(Model model){
        List<WebsiteLog> logs = adminService.getAllLog();
        model.addAttribute("logsList", logs);
        return "/admin/logs";
    }

    @RequestMapping("/users")
    public String showUser(Model model){
        List<Users> users = userService.findAllUser();
        model.addAttribute("usersList", users);
        return "/admin/users";
    }

    @RequestMapping("/goods")
    public String showGoods(Model model){
        List<Goods> list = goodsService.findAllGoods();
        model.addAttribute("goodsList", list);
        return "admin/goods";
    }

    @RequestMapping("/orders")
    public String showOrders(Model model){
        List<Orders> list = orderService.findAllOrders();
        model.addAttribute("ordersList", list);
        return "admin/orders";
    }

    @RequestMapping("/notice")
    public String showNotice(Model model){
        List<Notice> list = adminService.getAllNotice();
        model.addAttribute("noticeList", list);
        return "admin/notice";
    }

    @RequestMapping("/setting")
    public String showSetting(Model model, HttpServletRequest request){
        Admin user = adminService.findAdminById(Utils.getAdmin(request).getId());
        model.addAttribute("user", user);
        return "admin/setting";
    }
}
