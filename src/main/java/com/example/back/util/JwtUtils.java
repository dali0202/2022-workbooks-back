package com.example.back.util;

import com.example.back.auth.UserPrincipal;
import com.example.back.config.AppProperties;
import com.example.back.user.domain.User;
import com.example.back.user.domain.UserRepository;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtUtils {

	private final AppProperties appProperties;
	private final UserRepository userRepository;

	public String createToken(Authentication authentication){

		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (int) appProperties.getAuth().getTokenExpirationTime());

		return Jwts.builder()
			.setSubject(userPrincipal.getEmail())
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret().getBytes())
			.compact();
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			return !getClaims(token).getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public Authentication getAuthentication(String token) {
		Claims claims = getClaims(token);
		Optional<User> user = userRepository.findByEmail(claims.getSubject());
		UserPrincipal userPrincipal = UserPrincipal.create(user.get());
		return new OAuth2AuthenticationToken(userPrincipal, userPrincipal.getAuthorities(),
			userPrincipal.getAuthProvider());
	}

	private Claims getClaims(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(appProperties.getAuth().getTokenSecret().getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public User getUserByToken(HttpServletRequest request) {
		String token = resolveToken(request);
		Claims claims = getClaims(token);
		return userRepository.findByEmail(claims.getSubject()).get();
	}
}
