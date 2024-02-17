package com.bestsafety.bs.data;

import com.bestsafety.bs.dto.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    @Autowired
    private com.bestsafety.bs.repository.ContentRepository contentRepository;

    public Page<Content> getContent(int page) {
        Pageable pageable = PageRequest.of(page,999999);
        return contentRepository.findAll(pageable);
    }
}
