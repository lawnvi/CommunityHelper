package com.buct.showhelp.web;

import com.buct.showhelp.pojo.Users;
import com.buct.showhelp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String Root(Model m) {
//        m.addAttribute("name", "thymeleaf");
        return "login";
    }

    /**
     * 获取name, psw，login
     */
    @RequestMapping("/login")
    public String userLogin(@RequestParam("email") String email, @RequestParam("password") String psw, HttpServletRequest request){
        Users users = userService.userLogin(email, psw);
        System.out.println(email +" "+psw);
        if(users != null){
            request.getSession().setAttribute("Session_user", users);
            request.getSession().setMaxInactiveInterval(3600);
            return "redirect:../index";
        }
        return "loginError";
    }

    @RequestMapping("/registerpage")
    public String registerPage(){
        return "register";
    }

    /**
     * 注册新用户
     */
    @ResponseBody
    @RequestMapping("register")
    public String userRegister(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("password") String psw, @RequestParam("password2") String psw2){
        if(!psw.equals(psw2)){
            return "密码不一致，请确认密码再注册！";
        }
        int result = userService.userRegister(name, psw, email);
        if(result == 0){
            return "注册失败！";
        } else {
            return result+"注册成功！";
        }
    }
}