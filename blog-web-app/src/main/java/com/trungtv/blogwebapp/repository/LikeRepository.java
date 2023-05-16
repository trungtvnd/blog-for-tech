package com.trungtv.blogwebapp.repository;

import com.trungtv.blogwebapp.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
