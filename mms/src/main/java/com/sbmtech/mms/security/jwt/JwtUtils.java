package com.sbmtech.mms.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.RoleEnum;
import com.sbmtech.mms.models.User;



@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${mms.app.jwtSecret}")
  private String jwtSecret;

  @Value("${mms.app.jwtExpirationMs}")
  private int jwtExpirationMs;
  
  @Autowired
  UserRepository userRepository;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    Optional<User> userOp=userRepository.findByEmail(userPrincipal.getUsername());
    User user=null;
    Integer subscriberId=null;
    if(userOp.isPresent()) {
     	user=userOp.get();
    	if(user!=null ) {
    		Set <Role> roles=user.getRoles();
//    		if(roles!=null && roles..contains(RoleEnum.ROLE_MGT_ADMIN)) {
//    			
//    		}
    		subscriberId=user.getSubscriber().getSubscriberId();
    	}
    }
    
    return Jwts.builder()
        .setSubject((userPrincipal.getUsername()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(key(), SignatureAlgorithm.HS256)
        .claim("userId", userPrincipal.getUserId())
        .claim("subscriberId",(subscriberId!=null)?subscriberId:0)
        .compact();
  }
  
  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
