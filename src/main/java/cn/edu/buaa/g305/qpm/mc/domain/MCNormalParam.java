package cn.edu.buaa.g305.qpm.mc.domain;

public class MCNormalParam {
	
		//MC常量
	    //变量名符号，例如 x或y
		private String name;
		private String value;
		//变量符号解释 例如，x代表什么
		private String nameRemark;
		public String getNameRemark() {
			return nameRemark;
		}
		public void setNameRemark(String nameRemark) {
			this.nameRemark = nameRemark;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

}
