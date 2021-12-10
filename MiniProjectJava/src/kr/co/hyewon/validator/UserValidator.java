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

		// 유효성 검사 중  에러가 난 Bean 이름 추출 
		// 추출된 이름으로 분기하여 사용 
		String beanName = errors.getObjectName();
		//System.out.println(beanName);

		// 비밀번호 확인 유효성 검사 
		// 에러난 부분이 회원가입 일 경우에만 비밀번호 확인 유효성 검사하도록 설정.
		if(beanName.equals("joinUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}

			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
	}
}
