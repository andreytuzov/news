package by.htp.testtask.news.dao;

import java.util.List;

import by.htp.testtask.news.dao.model.Article;

public interface ArticleDAO {
	int create(Article article);

	Article read(int id);

	List<Article> readAll();

	void update(Article article);

	void delete(int id);
	
	void deleteList(String stringIDs);
}
