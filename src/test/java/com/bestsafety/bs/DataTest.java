package com.bestsafety.bs;

import com.bestsafety.bs.data.DataService;
import com.bestsafety.bs.dto.Content;
import com.bestsafety.bs.repository.ContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

@SpringBootTest
public class DataTest {

    @Autowired
    DataService dataService;
    @Autowired
    ContentRepository contentRepository;
    @Test
    void insertDummyContentTest() {
        int num = (int)(Math.random()*30+20);

        Content content;
        for(int i=0; i<num; i++) {
            content = new Content();
            content.setId(i+1);
            content.setTitle("제목입니다" + (i+1));
            content.setCreateDate(String.valueOf(new Timestamp(System.currentTimeMillis())));
            contentRepository.save(content);
        }

        contentRepository.findAll();
    }

    @Test
    void changeDateFormatTest() throws ParseException {
        Date beforeDate = new Date(System.currentTimeMillis());
        String afterDate = dataService.changeDateFormat(beforeDate, "yyyy-MM-dd");
        System.out.println("changeDateFormatTest의 결과 : " + afterDate + " (orig. " + beforeDate + ")");
    }
}
