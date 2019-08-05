package cn.itcast.travel.service.impl;

import cn.itcast.travel.mapper.FavoriteMapper;
import cn.itcast.travel.mapper.RouteMapper;
import cn.itcast.travel.pojo.Favorite;
import cn.itcast.travel.pojo.PageBean;
import cn.itcast.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @package: cn.itcast.travel.service.impl
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public boolean isFavoriteByRid(int rid, int uid) {
        int num = favoriteMapper.isFavoriteByRid(rid, uid);
        return num != 0;
    }

    @Override
    public int addFavorite(int rid, int uid) {
        favoriteMapper.addFavorite(rid,uid);
        routeMapper.updateFavoriteCount(rid);
        int count = routeMapper.queryFavoriteCount(rid);
        return count;
    }

    @Override
    public PageBean queryFavoriteByPage(int uid,int curpage) {
        int pageSize = 8;
        int firstResult = (curpage - 1) * pageSize;

        int count = favoriteMapper.queryMyFavoriteCount(uid);
        List<Favorite> favoriteList = favoriteMapper.queryFavoriteByPage(uid, firstResult, pageSize);

        PageBean<Favorite> pageBean = new PageBean<>();
        pageBean.setData(favoriteList);
        pageBean.setPageSize(pageSize);
        pageBean.setCount(count);
        pageBean.setCurPage(curpage);

        return pageBean;
    }
}
