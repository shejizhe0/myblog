package club.hero.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import club.hero.mapper.LinkMapper;
import club.hero.pojo.Link;
import club.hero.pojo.LinkExample;
import club.hero.pojo.LinkExample.Criteria;
import club.hero.service.LinkService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service(timeout=300000)
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkMapper linkMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<Link> findAll() {
		return linkMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<Link> page=   (Page<Link>) linkMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Link link) {
		linkMapper.insert(link);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Link link){
		linkMapper.updateByPrimaryKey(link);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Link findOne(Integer id){
		return linkMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			linkMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(Link link, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		LinkExample example=new LinkExample();
		Criteria criteria = example.createCriteria();
		
		if(link!=null){			
						if(link.getLinkname()!=null && link.getLinkname().length()>0){
				criteria.andLinknameLike("%"+link.getLinkname()+"%");
			}
			if(link.getLinkurl()!=null && link.getLinkurl().length()>0){
				criteria.andLinkurlLike("%"+link.getLinkurl()+"%");
			}
	
		}
		
		Page<Link> page= (Page<Link>)linkMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
