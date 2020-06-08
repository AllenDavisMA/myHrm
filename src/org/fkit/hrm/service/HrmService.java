package org.fkit.hrm.service;

import java.util.List;

import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.Admin;
import org.fkit.hrm.domain.Manager;
import org.fkit.hrm.util.tag.PageModel;

/**   
 * @Description: 人事管理服务层接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public interface HrmService {


	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return Admin对象
	 * */
	Admin login(String loginname,String password);
	/**
	 * 经理登录
	 * @param  loginname
	 * @param  password
	 * @return Manager对象
	 * */
	Manager managerLogin(String loginname,String password);
	
	/**
	 * 员工登录
	 * @param  loginname
	 * @param  password
	 * @return Manager对象
	 * */
	Employee employeeLogin(String name,String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	Admin findAdminById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return Admin对象的List集合
	 * */
	List<Admin> findAdmin(Admin Admin,PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeAdminById(Integer id);
	
	/**
	 * 修改用户
	 * @param Admin 用户对象
	 * */
	void modifyAdmin(Admin Admin);
	
	/**
	 * 添加用户
	 * @param Admin 用户对象
	 * */
	void addAdmin(Admin Admin);
	
	/**
	 * 获得所有员工
	 * @param employee 查询条件
	 * @param pageModel 分页对象
	 * @return Dept对象的List集合
	 * */
	List<Employee> findEmployee(Employee employee,PageModel pageModel);
	
	/**
	 * 获得部门员工
	 * @param employee 查询条件
	 * @param pageModel 分页对象
	 * @return Dept对象的List集合
	 * */
	List<Employee> findMyEmployee(Integer deptId,PageModel pageModel);
	/**
	 * 根据id删除员工
	 * @param id
	 * */
	void removeEmployeeById(Integer id);
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return 员工对象
	 * */
	Employee findEmployeeById(Integer id);
	
	/**
	 * 添加员工
	 * @param employee 员工对象
	 * */
	void addEmployee(Employee employee);
	
	/**
	 * 修改员工
	 * @param employee 员工对象
	 * */
	void modifyEmployee(Employee employee);
	
	/**
	 * 获得所有经理
	 * @return Manager对象的List集合
	 * */
	List<Manager> findALLManager();
	
	/**
	 * 获得经理
	 * @return Manager对象的List集合
	 * */
	List<Manager> findManager(Integer id);
	
	/**
	 * 获得特指经理
	 * @return Manager对象的List集合
	 * */
	Manager findManagerById(Integer id);
	
	/**
	 * 根据id删除经理
	 * @param id
	 * */
	void removeManagerById(Integer id);
	
	/**
	 * 添加经理
	 * @param manager
	 * */
	void addManager(Manager manager);
	
	/**
	 * 修改经理
	 * @param manager
	 * */
	void modifyManager(Manager manager);
	
	
	
	/**
	 * 获得所有部门，分页查询
	 * @return Dept对象的List集合
	 * */
	List<Dept> findDept(Dept dept,PageModel pageModel);
	
	/**
	 * 获得所有部门
	 * @return Dept对象的List集合
	 * */
	List<Dept> findAllDept();
	
	/**
	 * 根据id删除部门
	 * @param id
	 * */
	public void removeDeptById(Integer id);
	
	/**
	 * 添加部门
	 * @param dept 部门对象
	 * */
	void addDept(Dept dept);
	
	/**
	 * 根据id查询部门
	 * @param id
	 * @return 部门对象
	 * */
	Dept findDeptById(Integer id);
	
	/**
	 * 修改部门
	 * @param dept 部门对象
	 * */
	void modifyDept(Dept dept);
	
	/**
	 * 获得所有职位
	 * @return Job对象的List集合
	 * */
	List<Job> findAllJob();
	
	List<Job> findJob(Job job,PageModel pageModel);
	
	/**
	 * 根据id删除职位
	 * @param id
	 * */
	public void removeJobById(Integer id);
	
	/**
	 * 添加职位
	 * @param Job 部门对象
	 * */
	void addJob(Job job);
	
	/**
	 * 根据id查询职位
	 * @param id
	 * @return 职位对象
	 * */
	Job findJobById(Integer id);
	
	/**
	 * 修改职位
	 * @param dept 部门对象
	 * */
	void modifyJob(Job job);
	
	
	/**
	 * 获得所有公告
	 * @return Notice对象的List集合
	 * */
	List<Notice> findNotice(Notice notice,PageModel pageModel);
	
	/**
	 * 根据id查询公告
	 * @param id
	 * @return 公告对象
	 * */
	Notice findNoticeById(Integer id);
	
	/**
	 * 根据id删除公告
	 * @param id
	 * */
	public void removeNoticeById(Integer id);
	
	/**
	 * 添加公告
	 * @param Notice 公告对象
	 * */
	void addNotice(Notice notice);
	
	/**
	 * 修改公告
	 * @param Notice 公告对象
	 * */
	void modifyNotice(Notice notice);
	
	
	
}
