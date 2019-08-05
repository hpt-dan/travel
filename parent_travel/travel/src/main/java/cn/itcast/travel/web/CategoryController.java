package cn.itcast.travel.web;

import cn.itcast.travel.pojo.ResultInfo;
import cn.itcast.travel.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @package: cn.itcast.travel.web
 * @version: V1.0
 * @author: Administrator
 * @date: 2019/4/21 22:12
 * @description:
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("queryCategoryList")
    @ResponseBody
    public String queryCategoryList(){

        String jsonData = null;
        ResultInfo resultInfo = null;
        try {
            jsonData = categoryService.queryCategoryList();
        }catch(JsonProcessingException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器正忙。");
            try {
                jsonData = new ObjectMapper().writeValueAsString(resultInfo);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return jsonData;

    }
}
