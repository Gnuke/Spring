package com.example.demo.article;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao dao;

	// 추가, 수정
	public ArticleDto saveArticle(ArticleDto dto) {
		Article entity = dao.save(new Article(dto.getNum(), dto.getWriter(), dto.getWdate(), dto.getTitle(),
				dto.getContent(), dto.getData(), dto.getCnt()));
		return new ArticleDto(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getTitle(),
				entity.getContent(), entity.getData(), entity.getCnt(), null);
	}

	// 글목록
	public ArrayList<ArticleDto> getAll() {
		List<Article> l = dao.findAll();
		ArrayList<ArticleDto> list = new ArrayList<ArticleDto>();
		for (Article entity : l) {
			list.add(new ArticleDto(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getTitle(),
					entity.getContent(), entity.getData(), entity.getCnt(), null));
		}
		return list;
	}

	// 번호로 검색
	public ArticleDto getArticle(int num) {
		Article entity = dao.findById(num).orElse(null);
		if (entity != null) {
			return new ArticleDto(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getTitle(),
					entity.getContent(), entity.getData(), entity.getCnt(), null);
		}
		return null;
	}

	// 제목으로 검색
	public ArrayList<ArticleDto> getByTitle(String title) {
		ArrayList<Article> l = dao.findByTitleLike("%" + title + "%");
		ArrayList<ArticleDto> list = new ArrayList<ArticleDto>();
		for (Article entity : l) {
			list.add(new ArticleDto(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getTitle(),
					entity.getContent(), entity.getData(), entity.getCnt(), null));
		}
		return list;
	}

	// 확장자로 검색
	public ArrayList<ArticleDto> getByData(String data) {
		ArrayList<Article> l = dao.findByDataLike("%" + data);
		ArrayList<ArticleDto> list = new ArrayList<ArticleDto>();
		for (Article entity : l) {
			list.add(new ArticleDto(entity.getNum(), entity.getWriter(), entity.getWdate(), entity.getTitle(),
					entity.getContent(), entity.getData(), entity.getCnt(), null));
		}
		return list;
	}
	
	//삭제
	public void delArticle(int num) {
		dao.deleteById(num);
	}
	
	//cnt 1증가
	public void editCnt(int num) {
		dao.updateCnt(num);
	}

}
