package com.bestsafety.bs.data;

import com.bestsafety.bs.entity.Content;
import com.bestsafety.bs.repository.ContentRepository;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.UUID;

@Controller
public class DataController {

    @Autowired
    DataService dataService;
    @Autowired
    ContentRepository contentRepository;

    @GetMapping("/data")
    public ModelAndView data(
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search_select", defaultValue = "") String select,
            @RequestParam(name = "search_input", defaultValue = "") String search
    ) throws ParseException {
        ModelAndView mv = new ModelAndView("/data/data.html");

        Page<Content> contents;
        if(!search.equals("")) {
            contents = dataService.readContents(page-1, select, search);
        } else {
            contents = dataService.readContents(page-1);
        }

        mv.addObject("contents", contents);
        mv.addObject("count", page);

        return mv;
    }

    @GetMapping("/data/{id}")
    public ModelAndView readData(
            HttpServletRequest request,
            @PathVariable("id") int id
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/data/detail.html");

        Content content = dataService.readContent(id);
        Content prevContent = dataService.readContent(id-1);
        Content nextContent = dataService.readContent(id+1);

        mv.addObject("content", content);
        mv.addObject("prev", prevContent);
        mv.addObject("next", nextContent);

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


        return "redirect:/data";
    }

    @PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(
            @RequestParam("file")MultipartFile multipartFile
    ) {
        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\"; // 이미지가 저장될 폴더
        String originalFileName = multipartFile.getOriginalFilename(); // 기존 파일 이름
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자
        String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 이름

        File targetFile = new File(fileRoot + savedFileName); // 저장될 파일

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            jsonObject.addProperty("url", "/summernoteImage/" + savedFileName);
            jsonObject.addProperty("responseCode", "success");

            dataService.createFile(originalFileName, savedFileName, extension, fileRoot+savedFileName, multipartFile.getSize());
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }
}

