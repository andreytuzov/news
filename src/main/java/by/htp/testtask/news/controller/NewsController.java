package by.htp.testtask.news.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.htp.testtask.news.controller.model.Article;
import by.htp.testtask.news.service.ArticleService;
import by.htp.testtask.news.service.exception.ServiceException;

@Controller
public class NewsController {

	@Autowired
	private ArticleService articleService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		try {
			model.addAttribute("articles", articleService.readAll());
		} catch (ServiceException e) {
			System.err.println("Error completing readAll()" + e);
		}
		return "newslist";
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("article", new Article());
		return "createnews";
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String createProcess(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult = " + bindingResult);
			return "createnews";
		}
		
		System.out.println(article);
		
		int id = 0;
		try {
			id = articleService.create(article);
		} catch (ServiceException e) {
			System.err.println("Error create article: " + e);
		}
		
		model.addAttribute("article", article);
		return "redirect:view/" + id;
	}
	
	
	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("article", articleService.read(id));
		} catch (ServiceException e) {
			System.err.println("Error editing: " + e);
		}
		return "editnews";
	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public String editProcess(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "editnews";
		}
		System.out.println("bindingResult = " + bindingResult);
		System.out.println(article);
		
		try {
			articleService.update(article);
		} catch (ServiceException e) {
			System.err.println("Error create article: " + e);
		}
		
		model.addAttribute("article", article);
		return "redirect:view/" + article.getId();
	}
	
	
	@RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") int id, Model model) {
		try {
			model.addAttribute("article", articleService.read(id));
		} catch (ServiceException e) {
			System.err.println("Error read(): " + e);
		}
		return "newsview";
	}
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST, 
			produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id") int id) {
		try {
			articleService.delete(id);
		} catch (ServiceException e) {
			System.err.println("Error deleting: " + e);
		}
		return "Статья с id = " + id + " была успешно удалена";
	}

	@RequestMapping(path = "/deletelist", method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteList(@RequestParam("stringIDs") String stringIDs) {
		try {
			articleService.deleteList(stringIDs);
		} catch (ServiceException e) {
			System.err.println("Error deleting: " + e);
		}
		return "Статьи с id = " + stringIDs + " были успешно удалены";
	}
	
}
