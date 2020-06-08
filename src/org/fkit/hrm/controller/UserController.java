package org.fkit.hrm.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.fkit.hrm.domain.Admin;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Manager;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理用户请求控制器
 * */
@Controller
public class UserController {
	
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
		
	/**
	 * 处理登录请求
	 * @param String loginname  登录名
	 * @param String password 密码
	 * @return 跳转的视图
	 * */
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("loginname") String loginname,
			 @RequestParam("password") String password,
			 @RequestParam("identity") String identity,
			 @ModelAttribute Employee employee,
			 Integer pageIndex,
			 HttpSession session,
			 Integer job_id,Integer dept_id,
			 ModelAndView mv,
			 Model model){
		if ("admin".equals(identity)) {
			// 调用业务逻辑组件判断用户是否可以登录
			Admin admin = hrmService.login(loginname, password);
			if(admin != null){
				// 将用户保存到HttpSession当中
				System.out.println(admin);
				session.setAttribute(HrmConstants.ADMIN_SESSION, admin);
				// 客户端跳转到main页面
				mv.setViewName("redirect:/main");
			}else{
				// 设置登录失败提示信息
				mv.addObject("message", "登录名或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
			return mv;
		} else if ("manager".equals(identity)) {
			// 调用业务逻辑组件判断用户是否可以登录
			System.out.println(identity);
			Manager manager = hrmService.managerLogin(loginname, password);
			if(manager != null){
				PageModel pageModel = new PageModel();
				
				List<Employee> employees = hrmService.findMyEmployee(manager.getDept_id(),pageModel);
				
				// 将用户保存到HttpSession当中
				session.setAttribute(HrmConstants.MANAGER_SESSION, manager);
				session.setAttribute("myEmployees", employees);
				// 客户端跳转到main页面
				mv.setViewName("redirect:/mainManager");
			}else{
				// 设置登录失败提示信息
				mv.addObject("message", "登录名或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
			return mv;
		} else {
			// 调用业务逻辑组件判断用户是否可以登录
			Employee theEmployee = hrmService.employeeLogin(loginname, password);
			if(theEmployee != null){
				
				
				// 将用户保存到HttpSession当中
				session.setAttribute(HrmConstants.EMPLOYEE_SESSION, theEmployee);
				// 客户端跳转到main页面
				mv.setViewName("redirect:/mainEmployee");
			}else{
				// 设置登录失败提示信息
				mv.addObject("message", "登录名或密码错误!请重新输入");
				// 服务器内部跳转到登录页面
				mv.setViewName("forward:/loginForm");
			}
			return mv;
		}
		
	}
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/admin/selectAdmin")
	 public String selectAdmin(Integer pageIndex,
			 @ModelAttribute Admin admin,
			 Model model){
		System.out.println("admin = " + admin);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Admin> admins = hrmService.findAdmin(admin, pageModel);
		model.addAttribute("admins", admins);
		model.addAttribute("pageModel", pageModel);
		return "admin/admin";
		
	}
	
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/admin/removeAdmin")
	 public ModelAndView removeAdmin(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			hrmService.removeAdminById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/admin/selectAdmin");
		// 返回ModelAndView
		return mv;
	}
	
	
	/**
	 * 处理修改用户请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Admin admin  要修改用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/admin/updateAdmin")
	 public ModelAndView updateAdmin(
			 String flag,
			 @ModelAttribute Admin admin,
			 ModelAndView mv){
		System.out.println(admin+" "+flag);
		if(flag.equals("1")){
			// 根据id查询用户
			Admin target = hrmService.findAdminById(admin.getId());
			// 设置Model数据
			mv.addObject("admin", target);
			// 返回修改员工页面
			mv.setViewName("admin/showUpdateAdmin");
		}else{
			// 执行修改操作
			hrmService.modifyAdmin(admin);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/admin/selectAdmin");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Admin admin  要添加用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/admin/addAdmin")
	 public ModelAndView addAdmin(
			 String flag,
			 @ModelAttribute Admin admin,
			 ModelAndView mv){
		System.out.println(admin.getPassword());
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("admin/showAddAdmin");
		}else{
			// 执行添加操作
			hrmService.addAdmin(admin);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/admin/selectAdmin");
		}
		// 返回
		return mv;
	}
	
}
