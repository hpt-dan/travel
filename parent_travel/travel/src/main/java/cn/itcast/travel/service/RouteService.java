package cn.itcast.travel.service;

import cn.itcast.travel.pojo.PageBean;
import cn.itcast.travel.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RouteService {

    Map<String,List<Route>> selection();

    PageBean queryPageBean(Integer cid, Integer curPage, String rname);

    Route queryRouteByRid(int rid);

    List<Route> queryChainRouteListPage();

}
