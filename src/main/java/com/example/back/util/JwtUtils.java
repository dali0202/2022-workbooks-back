package com.example.back.util;

import com.example.back.auth.auth.dto.OAuthAccessTokenResponse;
import com.example.back.config.AppProperties;
import com.example.back.exception.AuthException;
import com.example.back.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtils {

	private final AppProperties appProperties;
	private static final String REGEX_DELIMITER = "\\.";
	private static final String TOKEN_TYPE = "Bearer ";

	public String createToken(Long id, String name, String email){

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (int) appProperties.getAuth().getTokenExpirationTime());
		return Jwts.builder()
				.setSubject(String.valueOf(id))
				.claim("name", name)
				.claim("email", email)
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret().getBytes())
				.compact();
	}

	public String resolveToken(HttpServletRequest request) {
		String authorizationHeader = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
				.orElseThrow(() -> new AuthException(ErrorCode.ACCESS_TOKEN_NOT_FOUND));
		return authorizationHeader.substring(TOKEN_TYPE.length());
	}

	public void validateToken(String token) {
		if (getClaims(token).getExpiration().before(new Date())) {
			throw new AuthException(ErrorCode.ACCESS_TOKEN_EXPIRED
			);
		}
	}

	public String getSubject(String token) {
		return getClaims(token).getSubject();
	}

	private Claims getClaims(String token) {
		try {
			return Jwts
					.parserBuilder()
					.setSigningKey(appProperties.getAuth().getTokenSecret().getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (JwtException e) {
			throw new AuthException(ErrorCode.ACCESS_TOKEN_NOT_VALID);
		}
	}

	public Map<String, Object> parsePayload(OAuthAccessTokenResponse tokenResponse) {

		String idToken = tokenResponse.getId_token();
		String[] chunks = idToken.split(REGEX_DELIMITER);

		Base64.Decoder decoder = Base64.getUrlDecoder();
		String payload = new String(decoder.decode(chunks[1]));
		JSONObject jsonObject = new JSONObject(payload);
		return jsonObject.toMap();
	}
}
