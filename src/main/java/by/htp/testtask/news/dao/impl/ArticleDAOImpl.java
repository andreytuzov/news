package by.htp.testtask.news.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.testtask.news.dao.ArticleDAO;
import by.htp.testtask.news.dao.model.Article;

@Component
public class ArticleDAOImpl implements ArticleDAO {
 	
	private static final SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Article.class).buildSessionFactory();
	
	public int create(Article article) {
		Session session = factory.openSession();
		Integer id = null;
		try {
			session.beginTransaction();
			id = (Integer)session.save(article);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return id;
	}

	public Article read(int id) {
		Session session = factory.openSession();
		Article article = null;
		try {
			article = session.get(Article.class, id); 
		} finally {
			session.close();
		}
		return article;
	}

	public List<Article> readAll() {
		Session session = factory.openSession();
		List<Article> articles = null;
		try {
			articles = session.createQuery("FROM Article", Article.class).list();
		} finally {
			session.close();
		}
		return articles;
	}

	public void update(Article article) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.update(article);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	public void delete(int id) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			Article article = session.get(Article.class, id);
			session.delete(article);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteList(String stringIDs) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			session.createQuery("DELETE FROM Article a WHERE a.id IN (" + stringIDs + ")").executeUpdate();
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

}
