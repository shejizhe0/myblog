package club.hero.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import club.hero.mapper.BloggerMapper;
import club.hero.pojo.Blogger;
import club.hero.pojo.BloggerExample;
import club.hero.pojo.BloggerExample.Criteria;
import club.hero.service.BloggerService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service(timeout=300000)
public class BloggerServiceImpl implements BloggerService {

	@Autowired
	private BloggerMapper bloggerMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Blogger> findAll() {
		return bloggerMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Blogger> page=   (Page<Blogger>) bloggerMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Blogger blogger) {
		bloggerMapper.insert(blogger);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Blogger blogger){
		bloggerMapper.updateByPrimaryKey(blogger);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Blogger findOne(Integer id){
		return bloggerMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			bloggerMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Blogger blogger, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		BloggerExample example=new BloggerExample();
		Criteria criteria = example.createCriteria();
		
		if(blogger!=null){			
						if(blogger.getUsername()!=null && blogger.getUsername().length()>0){
				criteria.andUsernameLike("%"+blogger.getUsername()+"%");
			}
			if(blogger.getPassword()!=null && blogger.getPassword().length()>0){
				criteria.andPasswordLike("%"+blogger.getPassword()+"%");
			}
			/*if(blogger.getProfile()!=null && blogger.getProfile().length()>0){
				criteria.andProfileLike("%"+blogger.getProfile()+"%");
			}*/
			if(blogger.getNickname()!=null && blogger.getNickname().length()>0){
				criteria.andNicknameLike("%"+blogger.getNickname()+"%");
			}
			if(blogger.getSign()!=null && blogger.getSign().length()>0){
				criteria.andSignLike("%"+blogger.getSign()+"%");
			}
			if(blogger.getImagename()!=null && blogger.getImagename().length()>0){
				criteria.andImagenameLike("%"+blogger.getImagename()+"%");
			}
			if(blogger.getSs()!=null && blogger.getSs().length()>0){
				criteria.andSsLike("%"+blogger.getSs()+"%");
			}
	
		}
		
		Page<Blogger> page= (Page<Blogger>)bloggerMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
