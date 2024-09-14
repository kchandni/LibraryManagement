package com.example.LibrarySystem.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	private static final String secret = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
	public String extractUserName(String token)
	{
		return getClaim(token, Claims::getSubject);
		
	}

	private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
        		.setSigningKey(getSignInKey())
        		.build()
        		.parseClaimsJws(token)
        		.getBody();
        		
    }
	
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

	private Key getSignInKey() {
		// TODO Auto-generated method stub
		byte[] byteKey=Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(byteKey);
	}
	
	 public String generateToken(Map<String,Object> claims,UserDetails userDetails) {
	       return Jwts
	    		   .builder()
	    		   .setClaims(claims)
	    		   .setSubject(userDetails.getUsername())
	    		   .setIssuedAt(new Date(System.currentTimeMillis()))
	    		   .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
	    		   .signWith(getSignInKey(),SignatureAlgorithm.HS256)
	    		   .compact();
	    }
	 public String generateToken(UserDetails userDetails)
	 {
		 return generateToken(new HashMap<>(), userDetails);
	 }
	 //validate token
	 public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUserName(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	 //token is expired or not
	 private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDate(token);
	        return expiration.before(new Date());
	    }
	 //return expiration date
	 public Date getExpirationDate(String token) {
	        return getClaim(token, Claims::getExpiration);
	    }


}
