package com.bestsafety.bs.data;

import com.bestsafety.bs.entity.Content;

import com.bestsafety.bs.entity.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class DataService {
    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    @Autowired
    private com.bestsafety.bs.repository.ContentRepository contentRepository;
    @Autowired
    private com.bestsafety.bs.repository.FileRepository fileRepository;

    public Page<Content> readContents(int presentPage) throws ParseException {
        Pageable pageable = PageRequest.of(presentPage,10);
        Page<Content> contents = contentRepository.findAll(pageable);

        for(Content content : contents) {
            String createDate = content.getCreateDate();
            content.setCreateDate(changeDateFormat(createDate, "yyyy-MM-dd"));
        }

        return contents;
    }

    public Page<Content> readContents(int presentPage, String select, String search) throws ParseException {
        Pageable pageable = PageRequest.of(presentPage,10);

        Page<Content> contents = null;
        search = "%" + search + "%";

        if(select.equals("제목")) {
            contents = contentRepository.findByTitleLike(pageable, search);
        } else if(select.equals("내용")) {
            contents = contentRepository.findByContentLike(pageable, search);
        }

        for(Content content : contents) {
            String createDate = content.getCreateDate();
            content.setCreateDate(changeDateFormat(createDate, "yyyy-MM-dd"));
        }

        return contents;
    }

    public Content readContent(int id) {
        Content content = contentRepository.findById(id).orElse(null);
        return content;
    }

    /*
     * 날짜/시간을 지정된 format으로 변환하는 함수
     *
     * @param beforeDate(변환할 날짜/시간), format(변환하고 싶은 포맷)
     * @throws NullPointerException | ParseException beforeDate의 값이 변환 불가능한 값일 경우 예외 발생
     */
    public String changeDateFormat(String beforeDate, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date date;
        String afterDate;

        try {
            date = simpleDateFormat.parse(beforeDate);
            afterDate = simpleDateFormat.format(date);
        } catch (NullPointerException | ParseException e) {
            log.warn(e.getMessage());
            afterDate = "미기재";
        }

        return afterDate;
    }

    public void createContent(Content content) {
        int contentId;

        contentId = contentRepository.save(content).getId();
        updateFile(contentId);
    }

    /*
     * 임시 id(-1)인 file db 값들을 등록된 content의 id값으로 변경해주는 함수
     *
     * @param contentId(등록한 content id)
     */
    public void updateFile(int contentId) {
        Optional<File> files = fileRepository.findByContentId(-1);
        files.ifPresent(file -> {
            file.setContentId(contentId);
            fileRepository.save(file);
        });
    }

    public void createFile(String originalFileName, String savedFileName, String extension, String location, long size) {
        File file = new File();
        file.setOriginalName(originalFileName);
        file.setSaveName(savedFileName);
        file.setExtension(extension);
        file.setLocation(location);
        file.setFilesize(size);
        file.setContentId(-1);

        fileRepository.save(file);
    }
}