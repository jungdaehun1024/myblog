package com.course.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.course.blog.config.auth.PrincipalDetailService;
 
//빈등록: 스프링컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration

//스프링 시큐리티를 웹 보안 구성에 사용할 것. HTTP요청이 컨트롤러로 진입하기 전 
// 스프링 시큐리티 필터체인을 거치게한다.
@EnableWebSecurity // 시큐리티 필터 등록 ==> 이미 활성화된 스프링시큐리티가 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 메소드 수준의 보안을 활성화 

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // 해당 메서드를 스프링이 관리
	//Bcrypt해시 함수를 사용해 비밀번호를 안전하게 암호화한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); // encodePWD()를 호출하면 BCryptPasswordEncoder객체를 리턴받을 수 있다.
	}
	
	@Bean	
	@Override
	//시큐리티에서 제공하는 인증 매니저이다. 사용자의 인증및 권한 부여를 할 때 필요한 Bean
	//주로 사용자 인증정보를 검증하고 인증된 사용자에 대한 인증 객체를 반환
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	@Autowired
	private PrincipalDetailService principalDetailService;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//principalDetailService을 통해서 로그인을 할 때 패스워드 처리를 encodePWD메서드로 인코드 하고 비교를 자동으로 해줌 
      auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf 토큰 비활성화 ( 테스트시 걸어두는게 좋다)
			.authorizeRequests() // 요청이 들어올때
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // /auth 이하의 모든 경로를
				.permitAll() // 퍼밋할 것 (허용)
				.anyRequest() // 다른 모든 요청은
				.authenticated() // 인증되어야 한다
			.and()
				.formLogin()
				.loginPage("/auth/loginForm") //  로그인 페이지의 경로를 지정합니다.
				.loginProcessingUrl("/auth/loginProc") // 로그인 처리를 담당하는 URL을 지정합니다. 이 URL에 대한 요청은 Spring Security가 가로채서 사용자를 인증하고 로그인을 처리
				.defaultSuccessUrl("/"); // 로그인이 정상적으로 완료시 이동 url

	}
}
