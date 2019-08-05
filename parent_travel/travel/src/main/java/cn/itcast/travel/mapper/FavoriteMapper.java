package cn.itcast.travel.mapper;


import cn.itcast.travel.pojo.Favorite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper {

    int isFavoriteByRid(@Param("rid") Integer rid, @Param("uid") Integer uid);

    void addFavorite(@Param("rid") Integer rid, @Param("uid") Integer uid);

    int queryMyFavoriteCount(@Param("uid") int uid);

    List<Favorite> queryFavoriteByPage(@Param("uid") int uid,
                                       @Param("firstResult") int firstResult,
                                       @Param("pageSize") int pageSize);

}
