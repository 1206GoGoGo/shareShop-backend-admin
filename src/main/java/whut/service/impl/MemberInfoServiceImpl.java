package whut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.UserInfoDao;
import whut.dao.UserLoginDao;
import whut.pojo.UserInfo;
import whut.pojo.UserLogin;
import whut.service.MemberInfoService;
import whut.utils.JsonUtils;
import whut.utils.ResponseData;
@Service
public class MemberInfoServiceImpl implements MemberInfoService {

	@Autowired
	private UserInfoDao dao;

	@Autowired
	private UserLoginDao loginDao;
	
	@Override
	public ResponseData getList(int status) {
		List<UserInfo> list = dao.getList(status);
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}

	}

	@Override
	public ResponseData add(UserInfo user){
		dao.add(user);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData delete(int id) {
		boolean result = dao.delete(id);
		if(result) {
			return new ResponseData(200,"success",null);
		}else {
			return new ResponseData(500,"error",null);
		}
	}

	@Override
	public ResponseData search(int pagesize, int pageindex, String username, String phoneNumber,String name,String identityCardNo, int level) {
		
		int userId = 0;
		List<UserInfo> list = null;
		if(username!=null || username!="") {
			userId = loginDao.searchByUsername(username);
		}
		if(userId!=0) {
			//获取列表
			list = dao.getAllInfoByUserId(userId);
			return new ResponseData(200,"success",list);
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo.setPhoneNumber(phoneNumber);
		userInfo.setName(name);
		userInfo.setIdentityCardNo(identityCardNo);
		
		UserLogin userLogin = new UserLogin();
		userLogin.setLevel(level);
		userInfo.setUserLogin(userLogin);
		
		
		list = dao.searchAllInfoByUserInfo(pageindex,pagesize,userInfo);
		if(list!=null) {
			return new ResponseData(200,"success",list);
		}
		
		return  new ResponseData(400,"no data",null);
	}

	@Override
	public ResponseData modify(UserInfo user) {
		boolean result = dao.modify(user);
		if(result) {
			return new ResponseData(200,"success",null);
		}else {
			return new ResponseData(500,"error",null);
		}
	}

	@Override
	public ResponseData getDetail(int id) {
		UserInfo info = dao.getDetail(id);
		if(info != null) {
			return new ResponseData(200,"success",info);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData getMemberListBySeller(String username) {
		int sellerid = loginDao.searchByUsername(username);
		if(sellerid == 0) {
			return new ResponseData(4061,"no data",null);
		}

		List<UserInfo> list = null;
		list = dao.getMemberBySellerId(sellerid);
		if(list == null) {
			return new ResponseData(4062,"no data",null);
		}
		
		return new ResponseData(200,"success",list);
	}

}