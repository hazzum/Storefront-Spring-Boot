package com.hazzum.storefront.security.jwt;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.rest.exceptionHandler.NotAuthorizedException;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {

  @Value("${storefront.app.jwtSecret}")
  private String jwtSecret;

  @Value("${storefront.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(User theUser) {
    String token = Jwts.builder()
        .claim("id", Integer.toString(theUser.getId()))
        .claim("name", theUser.getUserName())
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    return token;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody().get("name", String.class);
  }

  public int getIdFromJwtToken(String token) {
    String theId =  Jwts.parser()
      .setSigningKey(jwtSecret)
      .parseClaimsJws(token)
      .getBody().get("id", String.class);
    return Integer.valueOf(theId);
  }

  public String parseJwt(String headerAuth) {
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      throw new NotAuthorizedException("Invalid JWT signature: {}" + e.getMessage());
    } catch (MalformedJwtException e) {
      throw new NotAuthorizedException("Invalid JWT token: {}" + e.getMessage());
    } catch (ExpiredJwtException e) {
      throw new NotAuthorizedException("JWT token is expired: {}" + e.getMessage());
    } catch (UnsupportedJwtException e) {
      throw new NotAuthorizedException("JWT token is unsupported: {}" + e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new NotAuthorizedException("JWT claims string is empty: {}" + e.getMessage());
    }
  }
}
