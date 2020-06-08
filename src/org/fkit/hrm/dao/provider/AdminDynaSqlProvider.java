package org.fkit.hrm.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Admin;
import static org.fkit.hrm.util.common.HrmConstants.ADMINTABLE;;

/**   
 * @Description: 用户动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午11:19:23 
 * @version V1.0   
 */
public class AdminDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(ADMINTABLE);
				if(params.get("admin") != null){
					Admin admin = (Admin)params.get("admin");
					if(admin.getAdminname() != null && !admin.getAdminname().equals("")){
						WHERE("  adminname LIKE CONCAT ('%',#{admin.adminname},'%') ");
					}
					if(admin.getStatus() != null && !admin.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{admin.status},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(ADMINTABLE);
				if(params.get("admin") != null){
					Admin admin = (Admin)params.get("admin");
					if(admin.getAdminname() != null && !admin.getAdminname().equals("")){
						WHERE(" adminname LIKE CONCAT ('%',#{admin.adminname},'%') ");
					}
					if(admin.getStatus() != null && !admin.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{admin.status},'%') ");
					}
				}
			}
		}.toString();
	}	
	
	// 动态插入
	public String insertAdmin(Admin admin){
		
		return new SQL(){
			{
				INSERT_INTO(ADMINTABLE);
				if(admin.getAdminname() != null && !admin.getAdminname().equals("")){
					VALUES("adminname", "#{adminname}");
				}
				if(admin.getStatus() != null && !admin.getStatus().equals("")){
					VALUES("status", "#{status}");
				}
				if(admin.getLoginname() != null && !admin.getLoginname().equals("")){
					VALUES("loginname", "#{loginname}");
				}
				if(admin.getPassword() != null && !admin.getPassword().equals("")){
					VALUES("password", "#{password}");
				}
			}
		}.toString();
	}
	// 动态更新
		public String updateAdmin(Admin admin){
			
			return new SQL(){
				{
					UPDATE(ADMINTABLE);
					if(admin.getAdminname() != null){
						SET(" adminname = #{adminname} ");
					}
					if(admin.getLoginname() != null){
						SET(" loginname = #{loginname} ");
					}
					if(admin.getPassword()!= null){
						SET(" password = #{password} ");
					}
					if(admin.getStatus()!= null){
						SET(" status = #{status} ");
					}
					if(admin.getCreateDate()!= null){
						SET(" create_date = #{createDate} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
