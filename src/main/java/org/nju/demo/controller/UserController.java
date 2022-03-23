package org.nju.demo.controller;

import org.nju.demo.config.Constants;
import org.nju.demo.entity.AUser;
import org.nju.demo.entity.AUserInfo;
import org.nju.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/index")
    public String viewLogin(){
        return "login";
    }

    @GetMapping("/view/register")
    public String viewRegister(){
        return "register";
    }

    @RequestMapping("/view/info")
    public String viewInfo(Model model){
        AUser user = (AUser) session.getAttribute("user");
        AUserInfo userInfo = userService.getUserInfoByUserId(user.getId());
        model.addAttribute("userInfo",userInfo);
        return "user_info";
    }

    @RequestMapping("/view/password")
    public String viewPassword(){
        return "password_modify";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        AUser user = new AUser();
        user.setUsername(username);
        user.setPassword(password);
        if (userService.login(user)){
            session.setAttribute("user",userService.getUserByUsername(username));
            session.removeAttribute("loginMsg");
            return "redirect:/view/projects";
        }
        else{
            session.setAttribute("loginMsg","账号不存在或密码错误");
            return "redirect:/index";
        }
    }

    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password){
        if (userService.isExist(username)) {
            session.setAttribute("RegisterMsg","该账号已存在");
            return "redirect:/view/register";
        }
        AUser user = new AUser();
        user.setUsername(username);
        user.setPassword(password);
        userService.addUser(user);

        AUser res = userService.getUserByUsername(username);
        AUserInfo userInfo = new AUserInfo();
        userInfo.setUserId(res.getId());
        userInfo.setTelephone(Constants.UserInfo.TELEPHONE);
        userInfo.setEmail(Constants.UserInfo.EMAIL);
        userService.addUserInfo(userInfo);

        session.removeAttribute("RegisterMsg");
        return "redirect:/index";
    }

    @RequestMapping("/logout")
    public String logout(){
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @RequestMapping("/user/update")
    public String updateUser(@RequestParam("password") String password){
        AUser user = (AUser) session.getAttribute("user");
        user.setPassword(password);
        userService.updatePassword(user);
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @RequestMapping("/userInfo/update")
    public String updateUserInfo(@RequestParam("telephone") String telephone,
                              @RequestParam("email") String email){
        AUser user = (AUser) session.getAttribute("user");
        AUserInfo userInfo = userService.getUserInfoByUserId(user.getId());
        userInfo.setTelephone(telephone);
        userInfo.setEmail(email);
        userService.updateUserInfo(userInfo);
        return "redirect:/view/info";
    }

}
