package com.bestsafety.bs.data;

import com.bestsafety.bs.dto.Content;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping("/data/write")
    public ModelAndView write(
            HttpServletRequest request
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/data/write.html");

        return mv;
    }

    @PostMapping("/data/write")
    public String create(
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("editordata") String content
    ) throws Exception {
        Content createContent = new Content();
        createContent.setTitle(title);
        createContent.setContent(content);

        dataService.createContent(createContent);

        return "redirect:/";
    }
}