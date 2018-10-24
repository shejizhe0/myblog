package club.hero.search.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import club.hero.pojo.Blog;
import club.hero.pojo.BlogExample;
import club.hero.search.service.BlogSearchService;
import entity.PageResult;

@Service(timeout=300000)
public class BlogSearchServiceImpl implements BlogSearchService {

	@Autowired
	private SolrTemplate solrTemplate;

	@Override
	public Map<String, Object> search(Map searchMap) {
		
		//关键字空格处理 
		String keywords = (String) searchMap.get("keywords");
		searchMap.put("keywords", keywords.replace(" ", ""));
		
		Map<String,Object> map=new HashMap<>();	
		//1.查询列表		
		map.putAll(searchList(searchMap));
		
	
		return map;
	}
	
	
	
	/**
	 * 根据关键字搜索列表
	 * @param keywords
	 * @return
	 */
	private Map searchList(Map searchMap){
		Map map=new HashMap();
		HighlightQuery query=new SimpleHighlightQuery();
		HighlightOptions highlightOptions=new HighlightOptions().addField("blog_title");//设置高亮的域
		highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀 
		highlightOptions.setSimplePostfix("</em>");//高亮后缀
		query.setHighlightOptions(highlightOptions);//设置高亮选项
		//1,按照关键字查询
		Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		//2,分页查询
		Integer pageNo= (Integer) searchMap.get("pageNo");//提取页码
		if(pageNo==null){
			pageNo=1;//默认第一页
		}
		Integer pageSize=(Integer) searchMap.get("pageSize");//每页记录数 
		if(pageSize==null){
			pageSize=20;//默认20
		}
		query.setOffset((pageNo-1)*pageSize);//从第几条记录查询
		query.setRows(pageSize);		
		
		
		
		HighlightPage<Blog> page = solrTemplate.queryForHighlightPage(query, Blog.class);
		for(HighlightEntry<Blog> h: page.getHighlighted()){//循环高亮入口集合
			Blog blog = h.getEntity();//获取原实体类			
			if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
				blog.setTitle(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
			}			
		}		
		map.put("rows",page.getContent());
		map.put("totalPages", page.getTotalPages());//返回总页数
		map.put("total", page.getTotalElements());//返回总记录数
		return map;
	}
	

	
	
	
	
	
}
