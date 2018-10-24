package club.hero.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import club.hero.mapper.BlogMapper;
import club.hero.pojo.Blog;
import club.hero.pojo.BlogExample;
import club.hero.pojo.BlogExample.Criteria;
import club.hero.service.BlogService;
import entity.PageResult;

@Service(timeout=300000)
public class BlogServiceImpl implements BlogService {
	
	
	@Autowired
	private BlogMapper blogMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Blog> findAll(){
		return blogMapper.selectByExample(null);
		
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Blog> page=   (Page<Blog>) blogMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
	/**
	 * 增加
	 */
	@Override
	public void add(Blog blog) {
		blogMapper.insert(blog);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Blog blog){
		blogMapper.updateByPrimaryKey(blog);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Blog findOne(Integer id){
		return blogMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			blogMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Blog blog, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		BlogExample example=new BlogExample();
		Criteria criteria = example.createCriteria();
		
		if(blog!=null){			
						if(blog.getTitle()!=null && blog.getTitle().length()>0){
				criteria.andTitleLike("%"+blog.getTitle()+"%");
			}
			if(blog.getSummary()!=null && blog.getSummary().length()>0){
				criteria.andSummaryLike("%"+blog.getSummary()+"%");
			}
			/*if(blog.getContent()!=null && blog.getContent().length()>0){
				criteria.andContentLike("%"+blog.getContent()+"%");
			}*/
			if(blog.getKeyword()!=null && blog.getKeyword().length()>0){
				criteria.andKeywordLike("%"+blog.getKeyword()+"%");
			}
	
		}
		
		Page<Blog> page= (Page<Blog>)blogMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
		
	
	
	
	
	
	
}
