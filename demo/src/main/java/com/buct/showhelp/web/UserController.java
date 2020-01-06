package com.buct.showhelp.web;

import com.buct.showhelp.pojo.Users;
import com.buct.showhelp.service.UserService;
import com.buct.showhelp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            users.setPassword("");
            request.getSession().setAttribute("Session_user", users);
//            request.getSession().setMaxInactiveInterval(0);
            return "redirect:../index";
        }
        return "loginError";
    }

    //logout
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/";
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

    /**
     * 我的信息
     */
    @RequestMapping("/myInformation")
    public String showMyInformation(Model model, HttpServletRequest request){
        model.addAttribute("user", Utils.getUserSession(request));
        return "information/myInformation";
    }

    /**
     * 修改信息
     */
    @RequestMapping("/updateUserPage")
    public String updateUserPage(Model model, HttpServletRequest request){
        model.addAttribute("user", Utils.getUserSession(request));
        return "information/updateUser";
    }
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request, @RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("address") String address,
                             @RequestParam("tel") String tel, @RequestParam("school") String school, @RequestParam("picturePath") String picturePath){
        Users users = new Users();
        users.setId(id);
        users.setAddress(address);
        users.setName(name);
        users.setTel(tel);
        users.setSchool(school);
        users.setPicturePath(picturePath);
        users.setEmail(Utils.getUserSession(request).getEmail());
        int result = userService.updateUser(users);
        if(result == 0)
            return "更新失败";
        request.getSession().setAttribute("Session_user", users);
        return "redirect:./myInformation";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePasswordPage")
    public String changePasswordPage(){
        return "information/changePassword";
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
        if(!psw.equals(userService.findUserById(Utils.getUserSession(request).getId()).getPassword())){
            return "原密码错误";
        }
        userService.changePassword(Utils.getUserSession(request).getId(), psw1);
        return "redirect:./myInformation";
    }

}