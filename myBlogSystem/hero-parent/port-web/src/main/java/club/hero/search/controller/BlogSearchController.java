package club.hero.search.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import club.hero.search.service.BlogSearchService;

@RestController
@RequestMapping("/blogsearch")
public class BlogSearchController {
	
	@Reference
	private BlogSearchService blogSearchService;
	
	@RequestMapping("/search")
	public Map<String, Object> search(@RequestBody Map searchMap ){
		return  blogSearchService.search(searchMap);
	}	
	
	
	

}
