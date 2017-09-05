package by.htp.testtask.news.service;

import java.util.List;

import by.htp.testtask.news.controller.model.Article;
import by.htp.testtask.news.service.exception.ServiceException;


public interface ArticleService {
	int create(Article article) throws ServiceException;

	Article read(int id) throws ServiceException;

	List<Article> readAll() throws ServiceException;

	void update(Article article) throws ServiceException;

	void delete(int id) throws ServiceException;
	
	void deleteList(String stringIDs) throws ServiceException;
}
