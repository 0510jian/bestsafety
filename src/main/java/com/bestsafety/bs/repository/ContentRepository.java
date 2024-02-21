package com.bestsafety.bs.repository;

import com.bestsafety.bs.dto.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    Page<Content> findByContentLike(Pageable pageable, String search);

    Page<Content> findByTitleLike(Pageable pageable, String search);
}
