package com.bestsafety.bs.repository;

import com.bestsafety.bs.entity.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    Page<Content> findByContentLike(Pageable pageable, String search);

    Page<Content> findByTitleLike(Pageable pageable, String search);
}
