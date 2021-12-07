package kr.co.hyewon.validator;

import org.springframework.validation.Errors;

import kr.co.hyewon.beans.UserBean;

public class UserValidator implements org.springframework.validation.Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// 유효성 검사할 Bean 설정
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		// target에 유효성 검사할 값들이 들어있다.
		// userBean으로 형변환 해준다.
		UserBean userBean = (UserBean)target;
		
		// 비밀번호 확인 유효성 검사 
		if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
			errors.rejectValue("user_pw", "NotEquals");
			errors.rejectValue("user_pw2", "NotEquals");
		}
		
		if(userBean.isUserIdExist() == false) {
			errors.rejectValue("user_id", "DontCheckUserIdExist");
		}
	}
	

	
}
