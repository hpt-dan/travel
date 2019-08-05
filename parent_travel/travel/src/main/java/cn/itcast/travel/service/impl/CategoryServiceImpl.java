package cn.itcast.travel.service.impl;

import cn.itcast.travel.mapper.CategoryMapper;
import cn.itcast.travel.pojo.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.utils.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @package: cn.itcast.travel.service.impl
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public String queryCategoryList() throws JsonProcessingException {

        String jsonData = (String) redisTemplate.opsForValue().get("category");

        if(StringUtils.isBlank(jsonData)){
            List<Category> categoryList = categoryMapper.queryCategoryList();
            jsonData = new ObjectMapper().writeValueAsString(categoryList);
            redisTemplate.opsForValue().set("category", jsonData);
        }

        return jsonData;
    }
}
