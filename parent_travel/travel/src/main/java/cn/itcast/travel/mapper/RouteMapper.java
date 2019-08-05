package cn.itcast.travel.mapper;

import cn.itcast.travel.pojo.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMapper {

    List<Route> queryPopulary();

    List<Route> queryNewDate();

    List<Route> queryIsTheme();

    int queryRouteCount(@Param("cid") Integer cid, @Param("rname") String rname);


    List<Route> queryRouteListPage(@Param("cid") Integer cid,
                                   @Param("rname") String rname,
                                   @Param("firstResult") Integer firstResult,
                                   @Param("pageSize") Integer pageSize);


    Route queryRouteByRid(@Param("rid") Integer rid);

    void updateFavoriteCount(@Param("rid") int rid);

    int queryFavoriteCount(@Param("rid") int rid);


    List<Route> queryChainRouteListPage();




}
