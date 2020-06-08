package org.fkit.hrm.dao;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.fkit.hrm.dao.provider.AdminDynaSqlProvider;
import org.fkit.hrm.domain.Admin;
import static org.fkit.hrm.util.common.HrmConstants.ADMINTABLE;

/**   
 * @Description: AdminMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public interface AdminDao {

	// 根据登录名和密码查询管理员
	@Select("select * from "+ADMINTABLE+" where loginname = #{loginname} and password = #{password}")
	Admin selectByLoginnameAndPassword(
			@Param("loginname") String loginname,
			@Param("password") String password);
	
	// 根据id查询管理员
	@Select("select * from "+ADMINTABLE+" where ID = #{id}")
	Admin selectById(Integer id);
	
	// 根据id删除管理员
	@Delete(" delete from "+ADMINTABLE+" where id = #{id} ")
	void deleteById(Integer id);
		
	// 动态修改用户
	@SelectProvider(type=AdminDynaSqlProvider.class,method="updateAdmin")
	void update(Admin admin);
		
	// 动态查询
	@SelectProvider(type=AdminDynaSqlProvider.class,method="selectWhitParam")
	List<Admin> selectByPage(Map<String, Object> params);
	
	// 根据参数查询管理员总数
	@SelectProvider(type=AdminDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	// 动态插入管理员
	@SelectProvider(type=AdminDynaSqlProvider.class,method="insertAdmin")
	void save(Admin admin);
	
}
