package cn.itcast.travel.service;

import cn.itcast.travel.pojo.PageBean;

/**
 * @package: cn.itcast.travel.service
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
public interface FavoriteService {

    boolean isFavoriteByRid(int rid,int uid);

    int addFavorite(int rid,int uid);

    PageBean queryFavoriteByPage(int uid,int curpage);
}
