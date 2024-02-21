package com.bestsafety.bs.data;

import com.bestsafety.bs.dto.Content;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/data")
    public ModelAndView data(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "select", defaultValue = "") String select,
            @RequestParam(name = "search", defaultValue = "") String search
    ) throws ParseException {
        ModelAndView mv = new ModelAndView("/data/data.html");

        Page<Content> contents;
        if(!search.equals("")) {
            contents = dataService.getContents(page-1, select, search);
        } else {
            contents = dataService.getContents(page-1);
        }

        mv.addObject("contents", contents);
        mv.addObject("count", page);

        return mv;
    }
}
