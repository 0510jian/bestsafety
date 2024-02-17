package com.bestsafety.bs.data;

import com.bestsafety.bs.dto.Content;
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
    @Autowired
    private com.bestsafety.bs.repository.ContentRepository contentRepository;

    public Page<Content> getContent(int page) throws ParseException {
        Pageable pageable = PageRequest.of(page,10);
        Page<Content> contents = contentRepository.findAll(pageable);

        for(Content content : contents) {
            String createDate = content.getCreateDate();
            content.setCreateDate(changeDateFormat(createDate, "yyyy-MM-dd"));
        }

        return contents;
    }

    public String changeDateFormat(String beforeDate, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(beforeDate);

        String afterDate = simpleDateFormat.format(date);

        return afterDate;
    }
}
