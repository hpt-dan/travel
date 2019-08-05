package cn.itcast.travel.web;

import cn.itcast.travel.pojo.PageBean;
import cn.itcast.travel.pojo.ResultInfo;
import cn.itcast.travel.pojo.Route;
import cn.itcast.travel.pojo.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @package: cn.itcast.travel.web
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@RequestMapping("route")
@Controller
public class RouteController {


    @Autowired
    private RouteService routeService;

    @RequestMapping("selection")
    @ResponseBody
    public ResultInfo selection(){

        ResultInfo resultInfo = null;
        try {
            Map<String, List<Route>> map = routeService.selection();
            resultInfo = new ResultInfo(true, map, null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "系统繁忙。。");
        }
        return  resultInfo;
    }


    @RequestMapping("queryPageBean")
    @ResponseBody
    public ResultInfo queryPageBean(@RequestParam(value = "cid") Integer cid, @RequestParam(value = "curPage",defaultValue = "1")Integer curPage, @RequestParam(value = "rname")String rname) {
        ResultInfo resultInfo = null;

        try {
            PageBean pageBean = routeService.queryPageBean(cid, curPage, rname);
            resultInfo = new ResultInfo(true, pageBean, null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "系统繁忙。。");
        }
        return resultInfo;
    }

    @RequestMapping("queryRouteByRid")
    @ResponseBody
    public ResultInfo queryRouteByRid(@RequestParam("rid") Integer rid){

        ResultInfo resultInfo = null;

        try {
            Route route = routeService.queryRouteByRid(rid);
            resultInfo = new ResultInfo(true,route,null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"系统正忙，请稍后再试");
        }
        return resultInfo;
    }






    @RequestMapping("chinaHot")
    @ResponseBody
    public ResultInfo chinaHot(){

        ResultInfo resultInfo = null;

        try {
            List<Route> list = routeService.queryChainRouteListPage();
            resultInfo = new ResultInfo(true, list, null);
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false,null,"系统正忙，请稍后再试");
        }

        return resultInfo;
    }
}
