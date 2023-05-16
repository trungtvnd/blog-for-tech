package com.trungtv.blogwebapp.repository;

import com.trungtv.blogwebapp.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HashTagRepository extends JpaRepository<HashTag, Long> {
}
