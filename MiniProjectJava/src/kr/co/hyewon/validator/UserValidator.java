package kr.co.hyewon.validator;

import org.springframework.validation.Errors;

import kr.co.hyewon.beans.UserBean;

public class UserValidator implements org.springframework.validation.Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// 유효성 검사할 Bean
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		// target에 유효성 검사할 값들이 들어있다.
		// 형변환 해준다.
		UserBean userBean = (UserBean)target;
		
		if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
			errors.rejectValue("user_pw", "NotEquals");
			errors.rejectValue("user_pw2", "NotEquals");
		}
	}
	

	
}
