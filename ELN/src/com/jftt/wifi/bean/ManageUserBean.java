/**
 * All rights Reserved, Copyright (C) FUJITSU LIMITED 2014
 * FileName:Ad.java
 * Version: 1.0
 * Modify record:
 * NO. |		Date		|		Name		|		Content
 * 1   |  2014/01/07           |  JFTT)wangjian     |  original version
 */
package com.jftt.wifi.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * ManageUserBean 用户表
 */
@Document(collection = "ManageUser")
public class ManageUserBean implements Serializable {
	
	/**  
	 * define a field serialVersionUID which type is long
	 */
	private static final long serialVersionUID = -8375491184353067679L;
	/**
	 * 
	 */
	@Id
	private String id; 		         //主键ID
	private Integer order;//排序
	private String userName;           //用户账号
	private String password;         //密码
	private String name;             //姓名
	private Integer sex;//性别1:男；2：女；
	private String mobile; //手机号码
	private String email; //邮箱
	private Integer deptId;       //部门
	private String deptName;//部门名称
	private Integer postId;         //职位
	private String postName;//岗位名称
	private Integer companyId; //公司id
	private String companyName;//公司名称
	private Integer subCompanyId; //子公司id
	private String subCompanyName;//子公司名称
	private String createTime;//创建时间
	private Integer status;//状态1：正常；2：冻结；
	private String birthDay;//生日
	private String qqNo;//qq号码
	private String wechartNo;//微信号码
	private String idCard;//身份证号码
	private String workNo; //工号
	private String workYear;//工龄
	private String address;//地址
	private String highEducation;//最高学历
	private String graduateCollege;//毕业院校
	private String major;//专业
	private String joinCompanyTime;//进入公司时间
	private String joinWorkTime;//参加工作时间
	private String headPhoto;//头像地址
	private String field1;//扩展字段1
	private String field2;//扩展字段2
	private String field3;//扩展字段3
	private String field4;//扩展字段4
	private String field5;//扩展字段5
	private String field6;//扩展字段6
	private String field7;//扩展字段7
	private String field8;//扩展字段8
	private String field9;//扩展字段9
	private String field10;//扩展字段10
	private String field11;//扩展字段1
	private String field12;//扩展字段2
	private String field13;//扩展字段3
	private String field14;//扩展字段4
	private String field15;//扩展字段5
	private String field16;//扩展字段6
	private String field17;//扩展字段7
	private String field18;//扩展字段8
	private String field19;//扩展字段9
	private String field20;//扩展字段10
	private String field21;//扩展字段1
	private String field22;//扩展字段2
	private String field23;//扩展字段3
	private String field24;//扩展字段4
	private String field25;//扩展字段5
	private String field26;//扩展字段6
	private String field27;//扩展字段7
	private String field28;//扩展字段8
	private String field29;//扩展字段9
	private String field30;//扩展字段10
	private String field31;//扩展字段1
	private String field32;//扩展字段2
	private String field33;//扩展字段3
	private String field34;//扩展字段4
	private String field35;//扩展字段5
	private String field36;//扩展字段6
	private String field37;//扩展字段7
	private String field38;//扩展字段8
	private String field39;//扩展字段9
	private String field40;//扩展字段10
	private String field41;//扩展字段1
	private String field42;//扩展字段2
	private String field43;//扩展字段3
	private String field44;//扩展字段4
	private String field45;//扩展字段5
	private String field46;//扩展字段6
	private String field47;//扩展字段7
	private String field48;//扩展字段8
	private String field49;//扩展字段9
	private String field50;//扩展字段10
	private String field51;//扩展字段1
	private String field52;//扩展字段2
	private String field53;//扩展字段3
	private String field54;//扩展字段4
	private String field55;//扩展字段5
	private String field56;//扩展字段6
	private String field57;//扩展字段7
	private String field58;//扩展字段8
	private String field59;//扩展字段9
	private String field60;//扩展字段10
	private String field61;//扩展字段1
	private String field62;//扩展字段2
	private String field63;//扩展字段3
	private String field64;//扩展字段4
	private String field65;//扩展字段5
	private String field66;//扩展字段6
	private String field67;//扩展字段7
	private String field68;//扩展字段8
	private String field69;//扩展字段9
	private String field70;//扩展字段10
	private String field71;//扩展字段1
	private String field72;//扩展字段2
	private String field73;//扩展字段3
	private String field74;//扩展字段4
	private String field75;//扩展字段5
	private String field76;//扩展字段6
	private String field77;//扩展字段7
	private String field78;//扩展字段8
	private String field79;//扩展字段9
	private String field80;//扩展字段10
	private String field81;//扩展字段1
	private String field82;//扩展字段2
	private String field83;//扩展字段3
	private String field84;//扩展字段4
	private String field85;//扩展字段5
	private String field86;//扩展字段6
	private String field87;//扩展字段7
	private String field88;//扩展字段8
	private String field89;//扩展字段9
	private String field90;//扩展字段10
	private String field91;//扩展字段1
	private String field92;//扩展字段2
	private String field93;//扩展字段3
	private String field94;//扩展字段4
	private String field95;//扩展字段5
	private String field96;//扩展字段6
	private String field97;//扩展字段7
	private String field98;//扩展字段8
	private String field99;//扩展字段9
	private String field100;//扩展字段10
	private List<ManageRoleBean> roleList;
	private Integer isManager;//是否管理员 1：是；2：否
	private String isEdit;//0-可以修改 1-不可以修改
	private String photo;//照片
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getIsManager() {
		return isManager;
	}
	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSubCompanyName() {
		return subCompanyName;
	}
	public void setSubCompanyName(String subCompanyName) {
		this.subCompanyName = subCompanyName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getSubCompanyId() {
		return subCompanyId;
	}
	public void setSubCompanyId(Integer subCompanyId) {
		this.subCompanyId = subCompanyId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getQqNo() {
		return qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	public String getWechartNo() {
		return wechartNo;
	}
	public void setWechartNo(String wechartNo) {
		this.wechartNo = wechartNo;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHighEducation() {
		return highEducation;
	}
	public void setHighEducation(String highEducation) {
		this.highEducation = highEducation;
	}
	public String getGraduateCollege() {
		return graduateCollege;
	}
	public void setGraduateCollege(String graduateCollege) {
		this.graduateCollege = graduateCollege;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getJoinCompanyTime() {
		return joinCompanyTime;
	}
	public void setJoinCompanyTime(String joinCompanyTime) {
		this.joinCompanyTime = joinCompanyTime;
	}
	public String getJoinWorkTime() {
		return joinWorkTime;
	}
	public void setJoinWorkTime(String joinWorkTime) {
		this.joinWorkTime = joinWorkTime;
	}
	public String getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	public String getField6() {
		return field6;
	}
	public void setField6(String field6) {
		this.field6 = field6;
	}
	public String getField7() {
		return field7;
	}
	public void setField7(String field7) {
		this.field7 = field7;
	}
	public String getField8() {
		return field8;
	}
	public void setField8(String field8) {
		this.field8 = field8;
	}
	public String getField9() {
		return field9;
	}
	public void setField9(String field9) {
		this.field9 = field9;
	}
	public String getField10() {
		return field10;
	}
	public void setField10(String field10) {
		this.field10 = field10;
	}
	public String getField11() {
		return field11;
	}
	public void setField11(String field11) {
		this.field11 = field11;
	}
	public String getField12() {
		return field12;
	}
	public void setField12(String field12) {
		this.field12 = field12;
	}
	public String getField13() {
		return field13;
	}
	public void setField13(String field13) {
		this.field13 = field13;
	}
	public String getField14() {
		return field14;
	}
	public void setField14(String field14) {
		this.field14 = field14;
	}
	public String getField15() {
		return field15;
	}
	public void setField15(String field15) {
		this.field15 = field15;
	}
	public String getField16() {
		return field16;
	}
	public void setField16(String field16) {
		this.field16 = field16;
	}
	public String getField17() {
		return field17;
	}
	public void setField17(String field17) {
		this.field17 = field17;
	}
	public String getField18() {
		return field18;
	}
	public void setField18(String field18) {
		this.field18 = field18;
	}
	public String getField19() {
		return field19;
	}
	public void setField19(String field19) {
		this.field19 = field19;
	}
	public String getField20() {
		return field20;
	}
	public void setField20(String field20) {
		this.field20 = field20;
	}
	public String getField21() {
		return field21;
	}
	public void setField21(String field21) {
		this.field21 = field21;
	}
	public String getField22() {
		return field22;
	}
	public void setField22(String field22) {
		this.field22 = field22;
	}
	public String getField23() {
		return field23;
	}
	public void setField23(String field23) {
		this.field23 = field23;
	}
	public String getField24() {
		return field24;
	}
	public void setField24(String field24) {
		this.field24 = field24;
	}
	public String getField25() {
		return field25;
	}
	public void setField25(String field25) {
		this.field25 = field25;
	}
	public String getField26() {
		return field26;
	}
	public void setField26(String field26) {
		this.field26 = field26;
	}
	public String getField27() {
		return field27;
	}
	public void setField27(String field27) {
		this.field27 = field27;
	}
	public String getField28() {
		return field28;
	}
	public void setField28(String field28) {
		this.field28 = field28;
	}
	public String getField29() {
		return field29;
	}
	public void setField29(String field29) {
		this.field29 = field29;
	}
	public String getField30() {
		return field30;
	}
	public void setField30(String field30) {
		this.field30 = field30;
	}
	public String getField31() {
		return field31;
	}
	public void setField31(String field31) {
		this.field31 = field31;
	}
	public String getField32() {
		return field32;
	}
	public void setField32(String field32) {
		this.field32 = field32;
	}
	public String getField33() {
		return field33;
	}
	public void setField33(String field33) {
		this.field33 = field33;
	}
	public String getField34() {
		return field34;
	}
	public void setField34(String field34) {
		this.field34 = field34;
	}
	public String getField35() {
		return field35;
	}
	public void setField35(String field35) {
		this.field35 = field35;
	}
	public String getField36() {
		return field36;
	}
	public void setField36(String field36) {
		this.field36 = field36;
	}
	public String getField37() {
		return field37;
	}
	public void setField37(String field37) {
		this.field37 = field37;
	}
	public String getField38() {
		return field38;
	}
	public void setField38(String field38) {
		this.field38 = field38;
	}
	public String getField39() {
		return field39;
	}
	public void setField39(String field39) {
		this.field39 = field39;
	}
	public String getField40() {
		return field40;
	}
	public void setField40(String field40) {
		this.field40 = field40;
	}
	public String getField41() {
		return field41;
	}
	public void setField41(String field41) {
		this.field41 = field41;
	}
	public String getField42() {
		return field42;
	}
	public void setField42(String field42) {
		this.field42 = field42;
	}
	public String getField43() {
		return field43;
	}
	public void setField43(String field43) {
		this.field43 = field43;
	}
	public String getField44() {
		return field44;
	}
	public void setField44(String field44) {
		this.field44 = field44;
	}
	public String getField45() {
		return field45;
	}
	public void setField45(String field45) {
		this.field45 = field45;
	}
	public String getField46() {
		return field46;
	}
	public void setField46(String field46) {
		this.field46 = field46;
	}
	public String getField47() {
		return field47;
	}
	public void setField47(String field47) {
		this.field47 = field47;
	}
	public String getField48() {
		return field48;
	}
	public void setField48(String field48) {
		this.field48 = field48;
	}
	public String getField49() {
		return field49;
	}
	public void setField49(String field49) {
		this.field49 = field49;
	}
	public String getField50() {
		return field50;
	}
	public void setField50(String field50) {
		this.field50 = field50;
	}
	public String getField51() {
		return field51;
	}
	public void setField51(String field51) {
		this.field51 = field51;
	}
	public String getField52() {
		return field52;
	}
	public void setField52(String field52) {
		this.field52 = field52;
	}
	public String getField53() {
		return field53;
	}
	public void setField53(String field53) {
		this.field53 = field53;
	}
	public String getField54() {
		return field54;
	}
	public void setField54(String field54) {
		this.field54 = field54;
	}
	public String getField55() {
		return field55;
	}
	public void setField55(String field55) {
		this.field55 = field55;
	}
	public String getField56() {
		return field56;
	}
	public void setField56(String field56) {
		this.field56 = field56;
	}
	public String getField57() {
		return field57;
	}
	public void setField57(String field57) {
		this.field57 = field57;
	}
	public String getField58() {
		return field58;
	}
	public void setField58(String field58) {
		this.field58 = field58;
	}
	public String getField59() {
		return field59;
	}
	public void setField59(String field59) {
		this.field59 = field59;
	}
	public String getField60() {
		return field60;
	}
	public void setField60(String field60) {
		this.field60 = field60;
	}
	public String getField61() {
		return field61;
	}
	public void setField61(String field61) {
		this.field61 = field61;
	}
	public String getField62() {
		return field62;
	}
	public void setField62(String field62) {
		this.field62 = field62;
	}
	public String getField63() {
		return field63;
	}
	public void setField63(String field63) {
		this.field63 = field63;
	}
	public String getField64() {
		return field64;
	}
	public void setField64(String field64) {
		this.field64 = field64;
	}
	public String getField65() {
		return field65;
	}
	public void setField65(String field65) {
		this.field65 = field65;
	}
	public String getField66() {
		return field66;
	}
	public void setField66(String field66) {
		this.field66 = field66;
	}
	public String getField67() {
		return field67;
	}
	public void setField67(String field67) {
		this.field67 = field67;
	}
	public String getField68() {
		return field68;
	}
	public void setField68(String field68) {
		this.field68 = field68;
	}
	public String getField69() {
		return field69;
	}
	public void setField69(String field69) {
		this.field69 = field69;
	}
	public String getField70() {
		return field70;
	}
	public void setField70(String field70) {
		this.field70 = field70;
	}
	public String getField71() {
		return field71;
	}
	public void setField71(String field71) {
		this.field71 = field71;
	}
	public String getField72() {
		return field72;
	}
	public void setField72(String field72) {
		this.field72 = field72;
	}
	public String getField73() {
		return field73;
	}
	public void setField73(String field73) {
		this.field73 = field73;
	}
	public String getField74() {
		return field74;
	}
	public void setField74(String field74) {
		this.field74 = field74;
	}
	public String getField75() {
		return field75;
	}
	public void setField75(String field75) {
		this.field75 = field75;
	}
	public String getField76() {
		return field76;
	}
	public void setField76(String field76) {
		this.field76 = field76;
	}
	public String getField77() {
		return field77;
	}
	public void setField77(String field77) {
		this.field77 = field77;
	}
	public String getField78() {
		return field78;
	}
	public void setField78(String field78) {
		this.field78 = field78;
	}
	public String getField79() {
		return field79;
	}
	public void setField79(String field79) {
		this.field79 = field79;
	}
	public String getField80() {
		return field80;
	}
	public void setField80(String field80) {
		this.field80 = field80;
	}
	public String getField81() {
		return field81;
	}
	public void setField81(String field81) {
		this.field81 = field81;
	}
	public String getField82() {
		return field82;
	}
	public void setField82(String field82) {
		this.field82 = field82;
	}
	public String getField83() {
		return field83;
	}
	public void setField83(String field83) {
		this.field83 = field83;
	}
	public String getField84() {
		return field84;
	}
	public void setField84(String field84) {
		this.field84 = field84;
	}
	public String getField85() {
		return field85;
	}
	public void setField85(String field85) {
		this.field85 = field85;
	}
	public String getField86() {
		return field86;
	}
	public void setField86(String field86) {
		this.field86 = field86;
	}
	public String getField87() {
		return field87;
	}
	public void setField87(String field87) {
		this.field87 = field87;
	}
	public String getField88() {
		return field88;
	}
	public void setField88(String field88) {
		this.field88 = field88;
	}
	public String getField89() {
		return field89;
	}
	public void setField89(String field89) {
		this.field89 = field89;
	}
	public String getField90() {
		return field90;
	}
	public void setField90(String field90) {
		this.field90 = field90;
	}
	public String getField91() {
		return field91;
	}
	public void setField91(String field91) {
		this.field91 = field91;
	}
	public String getField92() {
		return field92;
	}
	public void setField92(String field92) {
		this.field92 = field92;
	}
	public String getField93() {
		return field93;
	}
	public void setField93(String field93) {
		this.field93 = field93;
	}
	public String getField94() {
		return field94;
	}
	public void setField94(String field94) {
		this.field94 = field94;
	}
	public String getField95() {
		return field95;
	}
	public void setField95(String field95) {
		this.field95 = field95;
	}
	public String getField96() {
		return field96;
	}
	public void setField96(String field96) {
		this.field96 = field96;
	}
	public String getField97() {
		return field97;
	}
	public void setField97(String field97) {
		this.field97 = field97;
	}
	public String getField98() {
		return field98;
	}
	public void setField98(String field98) {
		this.field98 = field98;
	}
	public String getField99() {
		return field99;
	}
	public void setField99(String field99) {
		this.field99 = field99;
	}
	public String getField100() {
		return field100;
	}
	public void setField100(String field100) {
		this.field100 = field100;
	}
	public List<ManageRoleBean> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<ManageRoleBean> roleList) {
		this.roleList = roleList;
	}
	
	
	
	
	
}
