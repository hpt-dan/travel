package cn.itcast.travel.service.impl;

import cn.itcast.travel.mapper.RouteMapper;
import cn.itcast.travel.pojo.PageBean;
import cn.itcast.travel.pojo.Route;
import cn.itcast.travel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package: cn.itcast.travel.service.impl
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public Map<String, List<Route>> selection() {
        List<Route> popularyList = routeMapper.queryPopulary();
        List<Route> newDateList = routeMapper.queryNewDate();
        List<Route> isThemeList = routeMapper.queryIsTheme();
        Map<String, List<Route>> map = new HashMap<>();
        map.put("popularity", popularyList);
        map.put("news", newDateList);
        map.put("themes", isThemeList);
        return map;
    }


    @Override
    public PageBean queryPageBean(Integer cid, Integer curPage, String rname) {

        int pageSize = 8;
        int firstResult = (curPage - 1) * pageSize;

        //查询总记录数
        int count = routeMapper.queryRouteCount(cid, rname);
        //查询分页数据
        List<Route> routeList = routeMapper.queryRouteListPage(cid, rname, firstResult, pageSize);

        PageBean<Route> pageBean = new PageBean<>();
        pageBean.setCurPage(curPage);
        pageBean.setCount(count);
        pageBean.setPageSize(pageSize);
        pageBean.setData(routeList);

        return pageBean;
    }


    @Override
    public Route queryRouteByRid(int rid) {
        Route route = routeMapper.queryRouteByRid(rid);
        return route;

    }

    @Override
    public List<Route> queryChainRouteListPage() {
        List<Route> routeList = routeMapper.queryChainRouteListPage();
        return routeList;
    }
}
