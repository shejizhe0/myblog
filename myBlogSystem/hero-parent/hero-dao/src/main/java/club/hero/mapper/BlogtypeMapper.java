package club.hero.mapper;

import club.hero.pojo.Blogtype;
import club.hero.pojo.BlogtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogtypeMapper {
    int countByExample(BlogtypeExample example);

    int deleteByExample(BlogtypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blogtype record);

    int insertSelective(Blogtype record);

    List<Blogtype> selectByExample(BlogtypeExample example);

    Blogtype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blogtype record, @Param("example") BlogtypeExample example);

    int updateByExample(@Param("record") Blogtype record, @Param("example") BlogtypeExample example);

    int updateByPrimaryKeySelective(Blogtype record);

    int updateByPrimaryKey(Blogtype record);
}