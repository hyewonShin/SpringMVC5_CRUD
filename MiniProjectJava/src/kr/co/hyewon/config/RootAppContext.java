package kr.co.hyewon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.hyewon.beans.UserBean;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class RootAppContext {
	
	//로그인시 사용 할 userBean 따로 빈 등록해줌.
	@Bean("loginUserBean")
	@SessionScope
	public UserBean	loginUserBean() {
		return new UserBean();
	}
	
}
