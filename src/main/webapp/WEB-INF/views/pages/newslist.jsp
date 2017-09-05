<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<div class="page-name">
		<span class="page-name-base">News</span> 
		<span class="page-name-arrows">>></span>News List
	</div> 
	<ul> 
		<c:forEach var="article" items="${articles}">
			<li class="article" data-id="${article.id}">
				<div>
					<span class="article__date">${article.date}</span>
					<span class="article__title">${article.title}</span> 
				</div>
				<div class="article__brief">${article.brief}</div>
				<div class="article__management">
					<a href="/news/view/${article.id}"><spring:message code="form.button.view"/></a>
					<a href="/news/edit/${article.id}"><spring:message code="form.button.edit"/></a>
					<input class="article__checkbox" type="checkbox"/>
				</div>
			</li>
		</c:forEach> 
	</ul>
	
	<button id="delete-list-article" onclick="deleteListArticle()"><spring:message code="form.button.delete"/> </button>
</div>