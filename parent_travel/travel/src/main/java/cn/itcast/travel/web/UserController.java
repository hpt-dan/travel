package cn.itcast.travel.web;

import cn.itcast.travel.exceptions.PasswordErrorException;
import cn.itcast.travel.exceptions.UserExistsException;
import cn.itcast.travel.pojo.ResultInfo;
import cn.itcast.travel.pojo.User;
import cn.itcast.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;


/**
 * @package: cn.itcast.travel.web
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @param check
     * @param session
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public ResultInfo register(User user, @RequestParam("check")String check, HttpSession session){
        ResultInfo resultInfo = null;

        String checkCode = (String) session.getAttribute("check");
        try {
            if(!checkCode.equalsIgnoreCase(check)){
                resultInfo = new ResultInfo(false, null, "验证码错误");
            }else {
                userService.register(user);
                resultInfo = new ResultInfo(true, null, null);
            }
        } catch (UserExistsException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"系统繁忙，请稍后再用");
        }
        return resultInfo;
    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @RequestMapping("active")
    public String active(@RequestParam("code")String code){

        try {
            boolean active = userService.active(code);
            if (active){
                return "redirect:/login.html";
            }else {
                return "redirect:/error/500.html";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error/500.html";
        }
    }


    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(User user, HttpSession session,@RequestParam("check")String check) {
        ResultInfo resultInfo = null;

        String checkCode = (String) session.getAttribute("check");
        try {
            if(!checkCode.equalsIgnoreCase(check)){
                resultInfo = new ResultInfo(false, null, "验证码错误");
            }else {
                User loginUser = userService.login(user);
                session.setAttribute("user", loginUser);
                resultInfo = new ResultInfo(true, null, null);
            }
        } catch (PasswordErrorException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "系统繁忙");
        }
        return resultInfo;
    }


    @RequestMapping("queryInfoByLoginUser")
    @ResponseBody
    public ResultInfo queryInfoByLoginUser(HttpSession session){
        ResultInfo resultInfo = null;
        if(session.getAttribute("user") == null){
            resultInfo = new ResultInfo(false, null, null);
        }else {
            User user = (User) session.getAttribute("user");
            resultInfo = new ResultInfo(true, user.getName(), null);
        }
        return resultInfo;
    }


    @RequestMapping("outLogin")
    public String outLogin(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
