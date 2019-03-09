package whut.dao;

import java.util.List;

import whut.pojo.UserInfo;

public interface UserInfoDao {

	//获取完整用户信息--不包括seller
	List<UserInfo> getList(int status);
	
	List<UserInfo> getSellerList();

	void add(UserInfo user);

	//修改用户状态
	boolean delete(int id);

	//修改用户信息表
	boolean modify(UserInfo user);

	//通过登录表id获取全部信息
	UserInfo getDetail(int id);

	//通过用户id获取用户全部信息（两个表的信息）
	List<UserInfo> getAllInfoByUserId(int userId);

	//通过用户对象获取用户全部信息（两个表的信息）
	//无数据返回null
	//String username, String phoneNumber,String name,String identityCardNo,int  level
	List<UserInfo> searchAllInfoByUserInfo(int pageindex, int pagesize, UserInfo userInfo);

	List<UserInfo> getMemberBySellerId(int sellerid);

	UserInfo getUserInfo(String id);

	void addSeller(String id);

	void deleteSeller(String id);

}