<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="form">
	<table>
		<tr class="form__field">
			<td class="form__label"><spring:message code="form.label.title"/></td>
			<td class="form__input">${article.title}</td> 
		</tr>
		<tr class="form__field">
			<td class="form__label"><spring:message code="form.label.date"/></td>
			<td class="form__input">${article.date}</td>
		</tr>
		<tr class="form__field">
			<td class="form__label"><spring:message code="form.label.brief"/></td>
			<td class="form__input">${article.brief}</td>
		</tr>
		<tr class="form__field">
			<td class="form__label"><spring:message code="form.label.content"/></td>
			<td class="form__input">${article.content}</td>
		</tr>
	</table>
</div>
<div class="view__buttons">
	<button class="form__button" onclick="editArticle(${article.id})"><spring:message code="form.button.edit"/></button>
	<button class="form__button" onclick="deleteArticle(${article.id})"><spring:message code="form.button.delete"/></button>
</div>


