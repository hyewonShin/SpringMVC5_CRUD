package kr.co.hyewon.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hyewon.beans.UserBean;
import kr.co.hyewon.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	public boolean checkUserIdExist(String user_id) {
		
		String user_name = userDao.checkUserIdExist(user_id);
		
		if(user_name == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addUserInfo(UserBean joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}
	
	public void getLoginUserInfo(UserBean temploginUserBean) {
		
		UserBean temploginUserBean2 = userDao.getLoginUserInfo(temploginUserBean);
		
		if(temploginUserBean2 != null) {  //로그인 성공 시
			loginUserBean.setUser_idx(temploginUserBean2.getUser_idx());
			loginUserBean.setUser_name(temploginUserBean2.getUser_name());
			loginUserBean.setUserLogin(true); // 로그인 되있는 것
		}
	}
	
	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean = userDao.getModifyUserInfo(loginUserBean.getUser_idx());
		
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		
		// 현재 로그인한 사람의 index 번호
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		
		userDao.modifyUserInfo(modifyUserBean);
	}
}
