package com.bestsafety.bs;

import com.bestsafety.bs.data.DataService;
import com.bestsafety.bs.dto.Content;
import com.bestsafety.bs.repository.ContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class DataTest {
    DataService dataService = new DataService();

    @Autowired
    ContentRepository contentRepository;
    @Test
    void insertDummyContentTest() {
        System.out.println("# createDummyContentTest 시작");

        int num = (int)(Math.random()*30+20);

        Content content;
        for(int i=0; i<num; i++) {
            content = new Content();
            content.setId(i+1);
            content.setTitle("제목입니다" + (i+1));
            content.setCreateDate(new Timestamp(System.currentTimeMillis()));
            contentRepository.save(content);
        }

        contentRepository.findAll();
    }
}
