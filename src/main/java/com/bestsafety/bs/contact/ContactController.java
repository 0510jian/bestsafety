package com.bestsafety.bs.contact;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
    @GetMapping("/contact")
    public ModelAndView contact(
            HttpServletRequest request
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/contact/contact.html");
        return mv;
    }
}
