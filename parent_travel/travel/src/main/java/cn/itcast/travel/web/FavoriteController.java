package cn.itcast.travel.web;

import cn.itcast.travel.pojo.PageBean;
import cn.itcast.travel.pojo.ResultInfo;
import cn.itcast.travel.pojo.User;
import cn.itcast.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @package: cn.itcast.travel.web
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@Controller
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("isFavoriteByRid")
    @ResponseBody
    public ResultInfo isFavoriteByRid(@RequestParam("rid") Integer rid, HttpSession session) {

        User user = (User) session.getAttribute("user");
        ResultInfo resultInfo = null;
        if (user == null) {
            resultInfo = new ResultInfo(true, false, null);
        } else {
            try {
                boolean date = favoriteService.isFavoriteByRid(rid, user.getUid());
                resultInfo = new ResultInfo(true, date, null);
            } catch (Exception e) {
                e.printStackTrace();
                resultInfo = new ResultInfo(false, null, "系统正忙，稍后再试。");
            }
        }


        return resultInfo;
    }


    @RequestMapping("addFavorite")
    @ResponseBody
    public ResultInfo addFavorite(@RequestParam("rid") Integer rid, HttpSession session) {

        ResultInfo resultInfo   = null;

        User user = (User) session.getAttribute("user");
        if(user == null){
            resultInfo = new ResultInfo(true, 0, null);
        }else {
            try {
                int date = favoriteService.addFavorite(rid, user.getUid());
                resultInfo = new ResultInfo(true, date, null);
            } catch (Exception e) {
                e.printStackTrace();
                resultInfo = new ResultInfo(false, null, "服务器忙，稍后再试");
            }
        }
        return resultInfo;
    }

    @RequestMapping("queryFavoriteByPage")
    @ResponseBody
    public ResultInfo queryFavoriteByPage(HttpSession session,@RequestParam(value = "curPage",defaultValue = "1") int curPage){
        User user = (User) session.getAttribute("user");
        ResultInfo resultInfo = null;

        try {
            PageBean pageBean = favoriteService.queryFavoriteByPage(user.getUid(), curPage);
            resultInfo = new ResultInfo(true, pageBean, null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "系统繁忙");
        }


        return resultInfo;
    }
}