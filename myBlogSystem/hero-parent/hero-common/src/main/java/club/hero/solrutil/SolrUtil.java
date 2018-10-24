package club.hero.solrutil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;

import org.springframework.stereotype.Component;

import club.hero.mapper.BlogMapper;
import club.hero.pojo.Blog;
import club.hero.pojo.BlogExample;
import club.hero.pojo.BlogExample.Criteria;

@Component
public class SolrUtil {
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired
	private SolrTemplate solrTemplate;
	
	
	  //导入商品数据
	 
	public void importItemData(){
		BlogExample example=new BlogExample();
		Criteria criteria = example.createCriteria();
		/*criteria.andStatusEqualTo("1");//已审核*/		
		List<Blog> blogList = blogMapper.selectByExample(example);
		System.out.println("===博客列表===");
		for(Blog item:blogList){
			System.out.println(item.getTitle());			
		}		
		
		solrTemplate.saveBeans(blogList);
		solrTemplate.commit();
		
		System.out.println("===结束===");			
	}	

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
		SolrUtil solrUtil=  (SolrUtil) context.getBean("solrUtil");
		solrUtil.importItemData();
	}
	
	
	
}
