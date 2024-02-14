package com.bestsafety.bs.data;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataController {
    @GetMapping("/data")
    public ModelAndView data(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/data/data.html");
        return mv;
    }
}
