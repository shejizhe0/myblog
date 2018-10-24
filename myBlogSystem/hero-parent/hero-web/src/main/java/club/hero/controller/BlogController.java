package club.hero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;

import club.hero.pojo.Blog;
import club.hero.service.BlogService;
import entity.PageResult;
import entity.Result;

@RestController
@Component
@RequestMapping("/blog")
public class BlogController {
	
	@Reference
	private BlogService blogService;

	
	
	@RequestMapping("/findAll")
	public List<Blog> findAll(){
		return blogService.findAll();
		
	}
	

	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return blogService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param blog
	 * @param id 
	 * @return
	 */

	
	@RequestMapping("/add")
	public Result add(@RequestBody Blog blog, Integer id ){
		try {
			blogService.add(blog);
			
			
			return new Result(true, "增加成功3");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("经过此处4----");
			return new Result(false, "增加失败4");
		}
	}
	
	


	

	
	

	

	
	/**
	 * 修改
	 * @param blog
	 * @param id 
	 * @param id 
	 * @param id 
	 * @param ids 
	 * @param id 
	 * @param bloglist 
	 * @param bloglist 
	 * @return
	 */
	
	
	@RequestMapping("/update")
	public Result update(@RequestBody Blog blog,   Integer id ){
		try {
			blogService.update(blog);
			
		
				
			return new Result(true, "修改成功5");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("经过此处8----");
			return new Result(false, "修改失败6");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Blog findOne(Integer id){
		return blogService.findOne(id);	
		}
	
	/**
	 * 获取博客实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findBlog")
	public Blog findBlog(Integer id){
		return blogService.findOne(id);	
		
	}
	
	
	

	
	
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			blogService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Blog blog, int page, int rows  ){
		return blogService.findPage(blog, page, rows);		
	}
	
}
