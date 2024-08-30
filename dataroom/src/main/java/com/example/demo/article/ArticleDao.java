package com.example.demo.article;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {
	ArrayList<Article> findByTitleLike(String title);

	ArrayList<Article> findByDataLike(String data);

	@Transactional
	@Modifying
	@Query(value = "update article set cnt=cnt+1 where num=:num", nativeQuery = true)
	void updateCnt(@Param("num") int num);
}
