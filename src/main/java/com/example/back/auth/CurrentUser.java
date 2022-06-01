package com.example.back.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : user")
public @interface CurrentUser {
}

//SpEL 표현식에선 # 을 앞에 붙여 변수를 참조할 수 있는데, #this는 항상 현재 평가하고 있는 객체를 참조합니다.
//@AuthenticationPrincipal 에선 인증되지 않은 객체에 대해 ‘anonymousUser’ 문자열을 return 합니다.