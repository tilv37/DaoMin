package com.haramasu.daomin.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/21/2020
 */
@Controller
public class DmErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            model.addAttribute("errorCode",Integer.valueOf(status.toString()));
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
