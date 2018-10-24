package club.hero.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import club.hero.mapper.BlogtypeMapper;
import club.hero.pojo.Blogtype;
import club.hero.pojo.BlogtypeExample;
import club.hero.pojo.BlogtypeExample.Criteria;
import club.hero.service.BlogtypeService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service(timeout=300000)
public class BlogtypeServiceImpl implements BlogtypeService {

	@Autowired
	private BlogtypeMapper blogtypeMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Blogtype> findAll() {
		return blogtypeMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Blogtype> page=   (Page<Blogtype>) blogtypeMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Blogtype blogtype) {
		blogtypeMapper.insert(blogtype);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Blogtype blogtype){
		blogtypeMapper.updateByPrimaryKey(blogtype);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Blogtype findOne(Integer id){
		return blogtypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			blogtypeMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Blogtype blogtype, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		BlogtypeExample example=new BlogtypeExample();
		Criteria criteria = example.createCriteria();
		
		if(blogtype!=null){			
						if(blogtype.getTypename()!=null && blogtype.getTypename().length()>0){
				criteria.andTypenameLike("%"+blogtype.getTypename()+"%");
			}
	
		}
		
		Page<Blogtype> page= (Page<Blogtype>)blogtypeMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
