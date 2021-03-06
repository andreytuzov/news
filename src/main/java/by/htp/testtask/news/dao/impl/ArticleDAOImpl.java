package by.htp.testtask.news.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.testtask.news.dao.ArticleDAO;
import by.htp.testtask.news.dao.model.Article;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	@Autowired
	private SessionFactory factory;

	public int create(Article article) {
		Session session = factory.getCurrentSession();
		return (Integer) session.save(article);
	}

	public Article read(int id) {
		Session session = factory.getCurrentSession();
		Article article = session.get(Article.class, id);
		return article;
	}

	public List<Article> readAll() {
		Session session = factory.getCurrentSession();
		List<Article> articles = session.createQuery("FROM Article", Article.class).list();
		return articles;
	}

	public void update(Article article) {
		Session session = factory.getCurrentSession();
		session.update(article);
	}

	public void delete(int id) {
		Session session = factory.getCurrentSession();
		Article article = session.get(Article.class, id);
		session.delete(article);
	}

	@Override
	public void deleteList(String stringIDs) {
		Session session = factory.getCurrentSession();
		session.createQuery("DELETE FROM Article a WHERE a.id IN (" + stringIDs + ")").executeUpdate();
	}

}
