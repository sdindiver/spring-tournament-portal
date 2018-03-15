package com.bo.tournament.authentication.jwt;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.BiFunction;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.NativeWebRequest;

import com.bo.tournament.model.Account;
import com.bo.tournament.service.JWTAuthenticationSuccessHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
//Two LoopHoles IN JWT Authentication

//I have to use double cookie submit strategy to prevent CSRF attack.
// I am not able to log out the users from server side before expiry time or how would i force user to logged in again.
//Thats why i will need to use secret key not as hard code but as hash of user password.
// So we have to maintain a new mysql table for userid with secret key. If i have to logged out user. I can simpally remove his
//his secret key.
//Or if i have to allow a single user having multiple session with different devices. I have to user unique crypto random string for each connection. 
//This process is called JTI.
public class JWTTokenManager {
	private static Key SECRET_KEY = null;

	static {
		if (SECRET_KEY == null) {
			SECRET_KEY = MacProvider.generateKey();
		}
	}

	public static String generateToken(Account user) {
		return Jwts.builder().setSubject(user.getUserName())
				.setExpiration(
						Date.from(LocalDateTime.now().plusMinutes(15l).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}

	public static JWTToken parseToken(String token) {
		try{
			Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			if(jwsClaims != null){
				Claims claims =jwsClaims.getBody();
				String UserName = claims.getSubject();
				LocalDateTime expirationTime = claims.getExpiration().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				JWTToken webToken = JWTToken.of(UserName, expirationTime);
				return webToken;
			}
			
		}catch(SignatureException e){
			return null;
		}
		return null;
	}
	
	public static void clearCookieJwt(NativeWebRequest webRequest){
		getUserByJwtToken(webRequest, (x,y)-> {
			 Cookie cookie  = new Cookie(JWTAuthenticationSuccessHandler.AUTHORIZATION_HEADER_KEY,"");
			 cookie.setMaxAge(0);
			 y.addCookie(cookie);
			return true;
		});
	}
	public static <R> R getUserByJwtToken(NativeWebRequest request,BiFunction<JWTToken, HttpServletResponse, R> callBack){
		HttpServletRequest req = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse res = request.getNativeResponse(HttpServletResponse.class);
		return getUserByJwtToken(req,res,callBack);
	}
	
	public static <REQ extends HttpServletRequest,RES extends HttpServletResponse,R> R getUserByJwtToken(REQ webRequest, RES webResponse, BiFunction<JWTToken, HttpServletResponse, R> callBack){
		if(webRequest.getHeader("Cookie") !=null){
			String[] rawCookieParams = webRequest.getHeader("Cookie").split(";");
			for(String rawCookieNameAndValue :rawCookieParams)
			{
				if(rawCookieNameAndValue.contains(JWTAuthenticationSuccessHandler.AUTHORIZATION_HEADER_KEY)){
					 String[] rawCookieNameAndValuePair = rawCookieNameAndValue.split("=");
					 JWTToken parsedToken = JWTTokenManager.parseToken(rawCookieNameAndValuePair[1]);
					 if(parsedToken == null){
						 Cookie cookie  = new Cookie(JWTAuthenticationSuccessHandler.AUTHORIZATION_HEADER_KEY,"");
						 cookie.setMaxAge(0);
						 webResponse.addCookie(cookie);
						 return null;
					 }
					 if(callBack!=null){
						 return callBack.apply(parsedToken,webResponse);
					 }
					 
					 
				}
			 
			}
		}
		return null;
	}


}
