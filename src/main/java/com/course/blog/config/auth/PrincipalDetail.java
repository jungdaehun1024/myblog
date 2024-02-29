package com.course.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.course.blog.model.User;

import lombok.Getter;

//스프링시큐리티가 로그인 요청을 가로채 로그인을 진행하고 완료하면 userDeatils타입의 오브젝트를 
//스프링시큐리티의 고유한 세션 저장소에 저장을 해준다. 그때 저장되는게  UserDetails타입의 PrincipalDetail이 될 것 
@Getter
public class PrincipalDetail implements UserDetails {

	private User user; // 객체를 품고있는 것을 콤포지션이라함 extends를하면 상속
    
	public PrincipalDetail(User user) {
		this.user= user;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴 (true: 만료안됨) false=>만료
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 잠겨있는지 여부 (true: 안잠김)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호가 만료됐는지 여부 (true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 활성화(사용가능)인지 리턴 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 가지고있는 권한 목록을 리턴한다 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				// "ROLE_"를 받을 때 규칙
				return "ROLE_" + user.getRole();
			}
		});
		return collectors;
	}
}

//자바는 파라미터로 오브젝트를 담을 순 있지만 메서드만 넘길 수는 없다.
//때문에 저렇게 해준 것 
