package club.hero.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import club.hero.pojo.Blogger;
import club.hero.service.BloggerService;


import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/blogger")
public class BloggerController {

	@Reference
	private BloggerService bloggerService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Blogger> findAll(){			
		return bloggerService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return bloggerService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param blogger
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody Blogger blogger){
		try {
			bloggerService.add(blogger);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param blogger
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Blogger blogger){
		try {
			bloggerService.update(blogger);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Blogger findOne(Integer id){
		return bloggerService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Integer [] ids){
		try {
			bloggerService.delete(ids);
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
	public PageResult search(@RequestBody Blogger blogger, int page, int rows  ){
		return bloggerService.findPage(blogger, page, rows);		
	}
	
}
