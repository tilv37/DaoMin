package com.haramasu.daomin.controller.blog;

import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping("/home")
    public String home(Model model){
        Map<String, Object> tagNamesAndCateNames = getTagNamesAndCateNames();
        model.mergeAttributes(tagNamesAndCateNames);
        model.addAttribute("name","dingshuo");
        return "index";
    }


    @GetMapping(value = "post")
    public ResponseDTO<Page<PostVO>> getAllPost(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize){
        Page<PostVO> postVOS = postService.loadHomePageData(pageNo, pageSize);
        return ResponseDTO.success(postVOS);
    }

    @GetMapping(value = "/test")
    public String test(){
        return "index";
    }
}
