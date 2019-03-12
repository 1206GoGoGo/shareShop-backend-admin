package whut.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import whut.dao.ManagerInfoDao;
import whut.dao.UserLoginDao;
import whut.pojo.ManagerInfo;
import whut.pojo.UserInfo;
import whut.pojo.UserLogin;
import whut.service.ManagerInfoService;
import whut.utils.ResponseData;
@Service
public class ManagerInfoServiceImpl implements ManagerInfoService {

	@Autowired
	private ManagerInfoDao dao;
	
	@Autowired
	private UserLoginDao loginDao;

	@Override
	public ResponseData getList() {
		List<ManagerInfo> list = dao.getList();
		if(list != null) {
			return new ResponseData(200,"success",list);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData add(ManagerInfo managerInfo) {
		String username = managerInfo.getUserLogin().getUsername();
		String password = managerInfo.getUserLogin().getPassword();
		//判断用户名、密码是否符合规则
		if(username.length()<5) {
			return new ResponseData(4061,"unqualified username",null);}
		if(this.checkPassWordMethod(password)) {
			return new ResponseData(4062,"unqualified password",null);
		}
		
		//判断用户名是否冲突
		if(loginDao.getLoginInfo(username)!=null) {
			return new ResponseData(4063,"username is occupied",null);
		}
		
		//添加用户登录表数据
		UserLogin userLogin = new UserLogin();
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin.setLevel( managerInfo.getUserLogin().getLevel() );	//设置用户等级、管理员类型需要传入
		userLogin.setStatus((byte)1);	//设置用户状态
		loginDao.addUser(userLogin);
		userLogin = loginDao.getLoginInfo(username);
		
		//给user对象赋值id
		managerInfo.setUserId(userLogin.getUserId());
		managerInfo.setUserLogin(userLogin);

		//判断用户名是否重复
		dao.add(managerInfo);
		return new ResponseData(200,"success",null);

	}

	@Override
	public ResponseData getDetail(int id) {
		ManagerInfo info = dao.getDetail(id);
		if(info != null) {
			return new ResponseData(200,"success",info);
		}else {
			return new ResponseData(400,"no data",null);
		}
	}

	@Override
	public ResponseData modify(ManagerInfo managerInfo) {
		ManagerInfo managerInfoOld = dao.getDetail(managerInfo.getUserId());
		
		
		//判断当前管理员状态
		if( managerInfoOld.getUserLogin().getStatus() == 0 ) {
			return new ResponseData(4061,"管理员状态异常，禁止修改",null);
		}
		
		//只处理部分参数的修改
		managerInfoOld.setName(managerInfo.getName());
		managerInfoOld.setIdentityCardType(managerInfo.getIdentityCardType());
		managerInfoOld.setIdentityCardNo(managerInfo.getIdentityCardNo());
		managerInfoOld.setPhoneNumber(managerInfo.getPhoneNumber());
		managerInfoOld.setEmail(managerInfo.getEmail());
		managerInfoOld.setGender(managerInfo.getGender());
		managerInfoOld.setBirthday(managerInfo.getBirthday());
		
		
		dao.modify(managerInfo);
		return new ResponseData(200,"success",null);
	}

	@Override
	public ResponseData delete(int id) {
		dao.delete(id);
		return new ResponseData(200,"success",null);

	}
	
	//密码校验
    private boolean checkPassWordMethod(String str) {
        char[] ch1 = str.toCharArray();
        boolean flag = false;
        int num_count = 0, char_number = 0;
        for (int i = 0; i < ch1.length; i++) {
            if (Character.isDigit(ch1[i]) || Character.isLetter(ch1[i])) {
                if (Character.isDigit(ch1[i]))
                    num_count++;
                else
                    char_number++;
            } else
                break;
        }
        if (num_count >= 2 && char_number >= 8)
            flag = true;
        System.out.println("num_count=" + num_count + ",char_number=" + char_number);
        return flag;
    }

}
