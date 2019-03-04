package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

	public static String createJwt() throws IllegalArgumentException, UnsupportedEncodingException {
		Algorithm al = Algorithm.HMAC256("secretkey");
		String token =JWT.create().withIssuer("yangli").withSubject("SSO").withClaim("userid", "123456").withExpiresAt(new Date(System.currentTimeMillis()+360000)).sign(al);
		return token;
	}
	
	
	public static boolean verifyJwt(String token) {
		try {
			Algorithm al = Algorithm.HMAC256("secretkey");
			JWTVerifier verifier = JWT.require(al).build();
			DecodedJWT jwt = verifier.verify(token);
			return true;
		} catch (IllegalArgumentException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (JWTVerificationException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("验证失败");
		}
		return false;
		
	}
}
