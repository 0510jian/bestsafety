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

    public Page<Content> getContent(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return contentRepository.findAll(pageable);
    }

    public String changeDateFormat(Date beforeDate, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String afterDate = simpleDateFormat.format(beforeDate);

        return afterDate;
    }
}
