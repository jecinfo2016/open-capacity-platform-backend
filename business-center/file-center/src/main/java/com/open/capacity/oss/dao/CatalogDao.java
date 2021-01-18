package com.open.capacity.oss.dao;

import com.open.capacity.oss.model.Catalog;
import com.open.capacity.oss.model.FileInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * oss上传存储db
*/
@Mapper
public interface CatalogDao {

	Catalog findCatalog(String id);

	@Insert("insert into mdfile_catalog(id, catalog) "
			+ "values(#{id}, #{catalog})")
	int save(Catalog catalog);

	int update(Catalog catalog);

	@Delete("delete from mdfile_catalog where id = #{id}")
	int delete(String id);

}
