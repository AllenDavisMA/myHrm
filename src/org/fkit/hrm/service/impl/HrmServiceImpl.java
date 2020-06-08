package org.fkit.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fkit.hrm.dao.DeptDao;
import org.fkit.hrm.dao.EmployeeDao;
import org.fkit.hrm.dao.JobDao;
import org.fkit.hrm.dao.NoticeDao;
import org.fkit.hrm.dao.AdminDao;
import org.fkit.hrm.dao.ManagerDao;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.Admin;
import org.fkit.hrm.domain.Manager;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 人事管理系统服务层接口实现类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private AdminDao AdminDao;
	
	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Autowired
	private NoticeDao noticeDao;
	
	
	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Admin login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return AdminDao.selectByLoginnameAndPassword(loginname, password);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Manager managerLogin(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return managerDao.selectByLoginnameAndPassword(loginname, password);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Employee employeeLogin(String name, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return employeeDao.selectByLoginnameAndPassword(name, password);
	}

	/**
	 * HrmServiceImpl接口findAdmin方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Admin> findAdmin(Admin Admin,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("Admin", Admin);
		int recordCount = AdminDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<Admin> Admins = AdminDao.selectByPage(params);
		 
		return Admins;
	}
	
	/**
	 * HrmServiceImpl接口findAdminById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Admin findAdminById(Integer id) {
		return AdminDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口removeAdminById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeAdminById(Integer id) {
		AdminDao.deleteById(id);
		
	}
	
	/**
	 * HrmServiceImpl接口addAdmin方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyAdmin(Admin Admin) {
		AdminDao.update(Admin);
		
	}
	
	/**
	 * HrmServiceImpl接口modifyAdmin方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addAdmin(Admin Admin) {
		AdminDao.save(Admin);
		
	}
	
	/*****************经理服务接口实现*************************************/
	public List<Manager> findALLManager(){
		 
		return managerDao.selectAllManager();
	}
	
	public List<Manager> findManager(Integer id){
		List<Manager> theManager = managerDao.findManager(id);
		return theManager;
	}
	
	public Manager findManagerById(Integer id){
		return managerDao.selectById(id);
	}
	
	public void removeManagerById(Integer id) {
		managerDao.deleteById(id);
	}
	
	public void addManager(Manager manager) {
		managerDao.save(manager);
		
	}
	
	public void modifyManager(Manager manager) {
		managerDao.update(manager);
	}
	/*****************部门服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		
		return deptDao.selectAllDept();
	}
	
	/**
	 * HrmServiceImpl接口findDept方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("dept", dept);
		int recordCount = deptDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Dept> depts = deptDao.selectByPage(params);
		 
		return depts;
	}
	
	/**
	 * HrmServiceImpl接口removeAdminById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
		
	}

	/**
	 * HrmServiceImpl接口addDept方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);
		
	}
	
	/**
	 * HrmServiceImpl接口findDeptById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Dept findDeptById(Integer id) {
		
		return deptDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口modifyDept方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDept(Dept dept) {
		deptDao.update(dept);
		
	}
	/*****************员工服务接口实现*************************************/
	/**
	 * HrmService接口findEmployee方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("employee", employee);
		
		int recordCount = employeeDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Employee> employees = employeeDao.selectByPage(params);
	    return employees;
	}
	
	/**
	 * HrmService接口findMyEmployee方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findMyEmployee(Integer deptId,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */

	    List<Employee> employees = employeeDao.selectByDeptId(deptId);
	    int recordCount = employees.size();
	    pageModel.setRecordCount(recordCount);
	    return employees;
	}
	/**
	 * HrmService接口removeEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
		
	}
	/**
	 * HrmService接口findEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(Integer id) {
		
		return employeeDao.selectById(id);
	}
	
	/**
	 * HrmService接口addEmployee方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
		
	}
	
	/**
	 * HrmService接口modifyEmployee方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}
	
	/*****************职位接口实现*************************************/

	/**
	 * HrmService接口findAllJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {
		
		return jobDao.selectAllJob();
	}

	/**
	 * HrmService接口findJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("job", job);
		int recordCount = jobDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Job> jobs = jobDao.selectByPage(params);
		 
		return jobs;
	}
	
	/**
	 * HrmService接口removeJobById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeJobById(Integer id) {
		jobDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addJob方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addJob(Job job) {
		jobDao.save(job);
		
	}
	
	/**
	 * HrmService接口findJobById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Job findJobById(Integer id) {
		
		return jobDao.selectById(id);
	}
	
	/**
	 * HrmService接口modifyJob方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyJob(Job job) {
		jobDao.update(job);
		
	}
	
	/*****************公告接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("notice", notice);
		int recordCount = noticeDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Notice> notices = noticeDao.selectByPage(params);
		 
		return notices;
	}

	/**
	 * HrmService接口findNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Notice findNoticeById(Integer id) {
		
		return noticeDao.selectById(id);
	}

	/**
	 * HrmService接口removeNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeNoticeById(Integer id) {
		noticeDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addNotice方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addNotice(Notice notice) {
		noticeDao.save(notice);
		
	}
	
	/**
	 * HrmService接口modifyNotice方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyNotice(Notice notice) {
		noticeDao.update(notice);
		
	}

	
}
