package org.fkit.hrm.controller;

import java.util.List;

import org.fkit.hrm.domain.Admin;
import org.fkit.hrm.domain.Dept;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Manager;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {
	
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 查询所有manager
	 * */
	@RequestMapping(value="/manager/selectManager")
	 public String selectDept(Model model,Integer pageIndex,
			 @ModelAttribute Manager manager){
		
		List<Dept> depts = hrmService.findAllDept();
		model.addAttribute("depts", depts);
		List<Manager> managers = hrmService.findALLManager();
		System.out.println("经理人数："+managers.size());
		model.addAttribute("managers", managers);
		
		return "manager/manager";
		
	}
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/manager/findManager")
	 public String selectUser(Integer dept_id,
			 Model model){
		System.out.println("部门ID:"+dept_id);
		if (dept_id != 0) {
			PageModel pageModel = new PageModel();
			// 查询部门信息 ，用于模糊查询
			List<Dept> depts = hrmService.findAllDept();
			model.addAttribute("depts", depts);
			
			/** 查询用户信息     */
			List<Manager> managers = hrmService.findManager(dept_id);
			model.addAttribute("managers", managers);
			return "manager/manager";
		}
		else {
			List<Manager> managers = hrmService.findALLManager();
			model.addAttribute("managers", managers);
			return "manager/manager";
		}
		
	}
	
	/**
	 * 处理删除员工请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/manager/removeManager")
	 public ModelAndView removeManager(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			hrmService.removeManagerById(Integer.parseInt(id));
		}

		mv.setViewName("redirect:/manager/selectManager");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/manager/addManager")
	 public ModelAndView addManager(
			 String flag,
			 Integer dept_id,
			 @ModelAttribute Manager manager,
			 Model model,
			 ModelAndView mv){
		System.out.println(manager.getLoginname());
		System.out.println(manager.getPassword());
		System.out.println(manager.getDept_id());
		if(flag.equals("1")){
			List<Dept> depts = hrmService.findAllDept();
			model.addAttribute("depts", depts);
			// 设置跳转到添加页面
			mv.setViewName("manager/showAddManager");
		}else{
			this.genericAssociation(dept_id, manager);
			// 执行添加操作
			hrmService.addManager(manager);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/manager/selectManager");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理修改经理请求
	 * @param String flag 标记，1表示跳转到修改页面，2表示执行修改操作
	 * @param String dept_id 部门编号
	 * @param Manager manager  要修改员工的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/manager/updateManager")
	 public ModelAndView updateManager(
			 String flag,
			 Integer dept_id,
			 @ModelAttribute Manager manager,
			 ModelAndView mv){
		System.out.println("经理ID:"+manager.getId());
		if(flag.equals("1")){
			// 根据id查询员工
			Manager target = hrmService.findManagerById(manager.getId());
			// 需要查询部门信息 
			List<Dept> depts = hrmService.findAllDept();
			// 设置Model数据
			mv.addObject("depts", depts);
			mv.addObject("manager", target);
			// 返回修改员工页面
			mv.setViewName("manager/showUpdateManager");
		}else{
			// 创建并封装关联对象
			this.genericAssociation(dept_id, manager);
			System.out.println("updateEmployee -->> " + manager);
			// 执行修改操作
			hrmService.modifyManager(manager);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/manager/selectManager");
		}
		// 返回
		return mv;
	}
	
	
	public void genericAssociation(Integer dept_id,Manager manager){
		if(dept_id != null){
			Dept dept = new Dept();
			dept.setId(dept_id);
			manager.setDept(dept);
		}
	}
	

}
