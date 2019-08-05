package cn.itcast.travel.mapper;

import cn.itcast.travel.pojo.Category;
import cn.itcast.travel.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    List<Category> queryCategoryList();
}
