package org.fkit.hrm.dao.provider;

import static org.fkit.hrm.util.common.HrmConstants.MANAGERTABLE;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Manager;

public class ManagerDynaSqlProvider {
	
public String insertManager(Manager manager){
		
		return new SQL(){
			{
				INSERT_INTO(MANAGERTABLE);
				if(manager.getLoginname() != null && !manager.getLoginname().equals("")){
					VALUES("loginname", "#{loginname}");
				}
				if(manager.getUsername() != null && !manager.getUsername().equals("")){
					VALUES("username", "#{username}");
				}
				if(manager.getPassword() != null && !manager.getPassword().equals("")){
					VALUES("password", "#{password}");
				}
				if(manager.getStatus() != null && !manager.getStatus().equals("")){
					VALUES("status", "#{status}");
				}
				if(manager.getDept()!= null){
					VALUES("dept_id", "#{dept.id}");
				}
			}
		}.toString();
	}

	public String updateManager(Manager manager){
		
		return new SQL(){
			{
				UPDATE(MANAGERTABLE);
				if(manager.getLoginname() != null && !manager.getLoginname().equals("")){
					SET("loginname = #{loginname}");
				}
				if(manager.getUsername() != null && !manager.getUsername().equals("")){
					SET("username = #{username}");
				}
				if(manager.getPassword() != null && !manager.getPassword().equals("")){
					SET("password = #{password}");
				}
				if(manager.getStatus() != null && !manager.getStatus().equals("")){
					SET("status = #{status}");
				}
				if(manager.getDept()!= null){
					SET("dept_id = #{dept.id}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

}
