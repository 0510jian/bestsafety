package com.bestsafety.bs.repository;

import com.bestsafety.bs.dto.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
}
