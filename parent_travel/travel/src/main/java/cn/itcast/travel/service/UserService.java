package cn.itcast.travel.service;

import cn.itcast.travel.exceptions.PasswordErrorException;
import cn.itcast.travel.pojo.PageBean;
import cn.itcast.travel.pojo.User;

/**
 * @package: cn.itcast.travel.service
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
public interface UserService {
    void register(User user) throws Exception;

    boolean active(String code);

    User login(User user) throws Exception;


}
