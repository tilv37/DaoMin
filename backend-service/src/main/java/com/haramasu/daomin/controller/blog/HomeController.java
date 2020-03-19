package com.haramasu.daomin.controller.blog;

import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.PostVO;
import com.haramasu.daomin.service.PostService;
import io.swagger.models.Model;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Controller
public class HomeController {


    private final PostService postService;

    public HomeController(PostService service) {
        this.postService = service;
    }

    @GetMapping("/home")
    public String home(Model model){
        return "home";
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
