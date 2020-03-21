package com.haramasu.daomin.controller.blog;

import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.PostVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "1",required = false) int pageNo,
                       @RequestParam(defaultValue = "5",required = false) int pageSize,
                       Model model){
        Map<String, Object> tagNamesAndCateNames = getTagNamesAndCateNames();
        Page<PostVO> postVOS = postService.loadHomePageData(pageNo, pageSize);
        model.mergeAttributes(tagNamesAndCateNames);
        model.addAttribute("name","dingshuo");
        model.addAttribute("posts",postVOS.getContent());
        return "index";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @GetMapping("/post/{titleEn}")
    public String viewPost(@PathVariable(name = "titleEn")String titleEn,
                           Model model){
        PostVO postVO = postService.findByTitleEn(titleEn);
        model.addAttribute("post",postVO);
        return "post";
    }


    public ResponseDTO<Page<PostVO>> getAllPost(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize){
        Page<PostVO> postVOS = postService.loadHomePageData(pageNo, pageSize);
        return ResponseDTO.success(postVOS);
    }

    @GetMapping(value = "/test")
    public String test(){
        return "index";
    }
}
