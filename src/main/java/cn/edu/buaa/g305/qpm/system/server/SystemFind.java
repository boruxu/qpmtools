package cn.edu.buaa.g305.qpm.system.server;

public interface SystemFind {
	
	//确定产品所属的项目，有返回原名称(原名不为空)，数据库无此项目(或原名为空)，则返回默认名“无归属项目”
	String findProductAffiliation(String name);
	//确定项目所属的组织，有返回原名称(原名不为空)，数据库无此组织(或原名为空)，则返回默认名“无归属组织”
	String findProjectAffiliation(String name);

}
