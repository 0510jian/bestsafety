package com.bestsafety.bs.data;

import com.bestsafety.bs.dto.Content;

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

@Service
public class DataService {
    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    @Autowired
    private com.bestsafety.bs.repository.ContentRepository contentRepository;

    public Page<Content> getContents(int page) throws ParseException {
        Pageable pageable = PageRequest.of(page,10);
        Page<Content> contents = contentRepository.findAll(pageable);

        for(Content content : contents) {
            String createDate = content.getCreateDate();
            content.setCreateDate(changeDateFormat(createDate, "yyyy-MM-dd"));
        }

        return contents;
    }

    public Page<Content> getContents(int page, String select, String search) throws ParseException {
        Pageable pageable = PageRequest.of(page,10);

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
        contentRepository.save(content);
    }
}
