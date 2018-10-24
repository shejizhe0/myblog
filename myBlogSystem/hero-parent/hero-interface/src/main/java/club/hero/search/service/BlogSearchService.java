package club.hero.search.service;

import java.util.List;
import java.util.Map;

import club.hero.pojo.Blog;
import entity.PageResult;

public interface BlogSearchService {
	
	/**
	 * 搜索
	 * @param keywords
	 * @return
	 */
	public Map<String,Object> search(Map searchMap);

	
}
