package com.buct.showhelp.utils;

import com.buct.showhelp.pojo.Admin;
import com.buct.showhelp.pojo.Users;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {

    public static Users getUserSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Users) session.getAttribute("Session_user");
    }

    public static Admin getAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Admin) session.getAttribute("AdminObj");
    }

    public static String getSessionStr(HttpServletRequest request, String name){
        HttpSession session = request.getSession();
        return (String)session.getAttribute(name);
    }

    public static String getTime(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }

    public static String getDate(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(day);
    }

    public static String getTimeName(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH-mm-ss");
        return df.format(day);
    }

    public static String getCode() {
        int n = 6;
        String string = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//保存数字0-9 和 大小写字母
        StringBuffer sb = new StringBuffer(); //声明一个StringBuffer对象sb 保存 验证码
        for (int i = 0; i < n; i++) {
            Random random = new Random();//创建一个新的随机数生成器
            int index = random.nextInt(string.length());//返回[0,string.length)范围的int值    作用：保存下标
            char ch = string.charAt(index);//charAt() : 返回指定索引处的 char 值   ==》赋值给char字符对象ch
            sb.append(ch);// append(char c) :将 char 参数的字符串表示形式追加到此序列  ==》即将每次获取的ch值作拼接
        }
        return sb.toString();//toString() : 返回此序列中数据的字符串表示形式   ==》即返回一个String类型的数据
    }

    public static String saveFile(MultipartFile file, String path){
        if(!file.isEmpty()) {
            String fileName = file.getOriginalFilename();  // 文件名
            //fileName = UUID.randomUUID() + suffixName; // 新文件名
            String newName= Utils.getCode()+"-" +Utils.getCode() + fileName.substring(fileName.lastIndexOf(".")); // 新文件名

            File file2 = new File(Global.ABSOLUTEPATH+path);
            if(!file2.isDirectory()) {
                //递归生成文件夹
                file2.mkdirs();
            }
            try {
                //构建真实的文件路径
                File newFile = new File(file2.getAbsolutePath() + File.separator + newName);
                //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "/"+Global.PACKAGE_NAME+path+File.separator+newName;
        }
        return "";
    }
}
