package cn.itcast.travel.service.impl;

import cn.itcast.travel.exceptions.PasswordErrorException;
import cn.itcast.travel.exceptions.UserExistsException;
import cn.itcast.travel.mapper.UserMapper;
import cn.itcast.travel.pojo.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.utils.MailUtil;
import cn.itcast.travel.utils.Md5Util;
import cn.itcast.travel.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @package: cn.itcast.travel.service.impl
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:用户业务模块
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) throws Exception {
        //1:判断用户名是否存在
        User queryUser = userMapper.queryUserByUserName(user.getUsername());
        //2：往数据库插入数据
        if(queryUser != null){
            throw new UserExistsException("用户已经存在。");
        }

        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        userMapper.insertUser(user);

        MailUtil.sendMail(user.getEmail(),"<h2>注册成功！</h2><a href='http://localhost:8080/user/active?requestMethod=active&code="+user.getCode()+"'>请点击此处激活账号</a>");
    }

    @Override
    public boolean active(String code) {
        User user = userMapper.queryUserByCode(code);
        if(user == null){
            return false;
        }else {
            int update = userMapper.update(code);
            if(update == 1){
                return true;
            }else {
                return false;
            }
        }
    }


    @Override
    public User login(User user) throws Exception {
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        User queryUser = userMapper.queryUserByUserNameAndPassword(user);
        if(queryUser == null){
            throw new PasswordErrorException("用户名或者密码错误");
        }else
        {
            if("N".equalsIgnoreCase(queryUser.getStatus())){
                throw new PasswordErrorException("用户没激活，请激活");
            }
        }
        return queryUser;
    }
}
