package club.hero.service;
import java.util.List;
import club.hero.pojo.Blogtype;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface BlogtypeService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<Blogtype> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(Blogtype blogtype);
	
	
	/**
	 * 修改
	 */
	public void update(Blogtype blogtype);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public Blogtype findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Integer [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(Blogtype blogtype, int pageNum,int pageSize);
	
}
