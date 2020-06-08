package org.fkit.hrm.dao;

import static org.fkit.hrm.util.common.HrmConstants.MANAGERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.EmployeeDynaSqlProvider;
import org.fkit.hrm.dao.provider.ManagerDynaSqlProvider;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Manager;

public interface ManagerDao {
	
	// 根据登录名和密码查询员工
		@Select("select * from "+MANAGERTABLE+" where loginname = #{loginname} and password = #{password}")
		Manager selectByLoginnameAndPassword(
				@Param("loginname") String loginname,
				@Param("password") String password);
		
		
		// 查询所有经理
		@Select("SELECT * FROM manager_inf")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="loginname",property="loginname"),
			@Result(column="password",property="password"),
			@Result(column="status",property="status"),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="DEPT_ID",property="dept",
				one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",
			fetchType=FetchType.EAGER))
		})
		List<Manager> selectAllManager();
		
		@Select("SELECT * FROM "+MANAGERTABLE+" where dept_id = #{id}")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="loginname",property="loginname"),
			@Result(column="password",property="password"),
			@Result(column="status",property="status"),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="DEPT_ID",property="dept",
				one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",
			fetchType=FetchType.EAGER))
		})
		List<Manager> findManager(Integer id);
		
		@Select("SELECT * FROM "+MANAGERTABLE+" where ID = #{id}")
		@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="loginname",property="loginname"),
			@Result(column="password",property="password"),
			@Result(column="status",property="status"),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="DEPT_ID",property="dept",
				one=@One(select="org.fkit.hrm.dao.DeptDao.selectById",
			fetchType=FetchType.EAGER))
		})
		Manager selectById(Integer id);
		
		@Delete(" delete from "+MANAGERTABLE+" where id = #{id} ")
		void deleteById(Integer id);
		
		// 动态插入经理
		@InsertProvider(type=ManagerDynaSqlProvider.class,method="insertManager")
		void save(Manager manager);
		
		@SelectProvider(type=ManagerDynaSqlProvider.class,method="updateManager")
		void update(Manager manager);
			
		
		
		
			
}
