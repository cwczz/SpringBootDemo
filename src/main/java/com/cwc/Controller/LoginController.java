package com.cwc.Controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
   /* @GetMapping("/login")
    public String Login(){
        return "/login";
    }*/
   @PostMapping("/userlogin")
    public String login(String username, String password, Model model, HttpSession session){
       if(!StringUtil.isEmpty(username)&&password.equals("123456")){
           session.setAttribute("username",username);
           return "redirect:main";
       }else {
           model.addAttribute("msg","用户名或密码错误");
           return "login";
       }
   }
}
