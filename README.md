# myHrm
HrmAPP
技术选型： MySql、Tomcat8.5、MyBatis + Spring + SpringMVC框架、Ajax+JSP+CSS+Jquery+JavaScript技术
硬件环境：Windows 10
开发工具：eclipse
1、View层用JSP，Controller用SpringMVC，持久层采用MyBatis技术。用Ajax实现异步刷新功能，解决了全屏刷新资源消耗大及出空白页面等待的问题，也极大地减轻了网络传输的压力，并结合JavaScript技术丰富页面动态效果。
2、完善了该项目的相关文档资料。
项目描述：
该项目采用B/S模型开发。该系统，有三种不同的身份，普通员工，部门经理，管理员，普通员工可登陆查看公司公告以及自己的薪资，个人资料等；部门经理在除去普通员工的查询权限中，可查看自己部门的与员工信息，以及可以添加、删除、修改和查询自己部门下的员工信息；管理员可负责增删改查公告，部门，部门经理以及员工等信息。
项目总结：
本项目应用了SSM架构开源解决方案，使我在SSM整合开发上也得到了很大提高，无论是MyBatis还是SpringMVC这俩种框架都比较轻量，其特有得注解式开发更是减少得工程的代码量，其核心spring框架更是以其特有的依赖注入方式大大降低了整个工程的耦合度。