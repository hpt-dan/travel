package cn.itcast.travel.mapper;

import cn.itcast.travel.pojo.Route;
import cn.itcast.travel.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    void insertUser(User user);

    User queryUserByUserName(@Param("username") String username);

    User queryUserByCode(@Param("code") String code);

    int update(@Param("code")String code);

    User queryUserByUserNameAndPassword(User user);




}
