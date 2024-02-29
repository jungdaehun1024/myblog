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
//시큐리티가 모든 요청을 가로챈다 --> http요청시 컨트롤러로가서 메서드가 실행되는데 메서드가 실행되기 전에 해당 객체가 동작해서 필터링을 해야함 
@EnableWebSecurity // 시큐리티 필터 등록 ==> 이미 활성화된 스프링시큐리티가 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다.

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean // 해당 메서드를 스프링이 관리
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); // encodePWD()를 호출하면 BCryptPasswordEncoder객체를 리턴받을 수 있다.
	}
	
	@Bean	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}


	@Autowired
	private PrincipalDetailService principalDetailService;
	
	//시큐리티가 대신 로그인 해줄때 password를 가로채기한다.
	//해당 password가 어떤 값으로 해쉬가 되어 회원가입이 되었는지 알야야 같은 해쉬로 암호화해서 DB에 있는 해쉬와 비교 가능 
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
				.loginPage("/auth/loginForm") // 인증이 필요한 페이지에 접근시 로그인창이 자동으로 뜸
				.loginProcessingUrl("/auth/loginProc") // 스프링시큐리티가 해당 주소로 오는 로그인을 가로채서 대신 로그인한다. UserDetails타입의 User오브젝트를 만들어야함
				.defaultSuccessUrl("/"); // 로그인이 정상적으로 완료시 이동 url

	}
}
