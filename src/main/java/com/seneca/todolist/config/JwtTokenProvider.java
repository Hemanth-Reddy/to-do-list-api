package com.seneca.todolist.config;

import com.seneca.todolist.entity.UserEntity;
import com.seneca.todolist.model.UserDto;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The jwt token provider class implements all the required functionalities
 * related to the token.
 * <ul>
 * <li>To generate token</li>
 * <li>To validate token</li>
 * <li>To extract validity of the token</li>
 * <li>etc.</li>
 * </ul>
 *
 * @author hemanth.nagireddy
 *
 */
@Component
public class JwtTokenProvider {

  /**
   * A variable for token validity.
   */
  @Value("${jwt.validity}")
  public Long jwtTokenValidity;

  /**
   * A variable for secret key.
   */
  @Value("${jwt.secret}")
  private String secret;

  /**
   * A method to resolve the token i.e remove the Bearer from the token.
   *
   * @param token This is the authentication token.
   * @return token This is the resolved token without bearer word in it.
   */
  private String resolveToken(final String token) {
    if (token != null && token.startsWith("Bearer ")) {
      return token.substring(7);
    }
    return token;
  }

  /**
   * To retrieve username from jwt token.
   *
   * @param token This is the authentication token.
   * @return username This is the username from the token.
   */
  public String getUsernameFromToken(final String token) {
    return getClaimFromToken(resolveToken(token), Claims::getSubject);
  }

  /**
   * To retrieve the expiration date from jwt token.
   *
   * @param token This is the authentication token.
   * @return Expiration time This is the date object indicating the expiration time.
   */
  public Date getExpirationDateFromToken(final String token) {
    return getClaimFromToken(resolveToken(token), Claims::getExpiration);
  }

  /**
   * To get individual claims from the token.
   *
   * @param <T>            This is the generic which may accept date or string
   *                       based on the claim requested.
   * @param token          This is the authentication token.
   * @param claimsResolver This is the function to fetch claim from all the
   *                       claims subjected to a condition.
   * @return associated value of the claim requested.
   */
  public <T> T getClaimFromToken(final String token,
      final Function<Claims, T> claimsResolver) {

    final Claims claims = getAllClaimsFromToken(resolveToken(token));
    return claimsResolver.apply(claims);
  }

  /**
   * This method is for retrieving any information from token.
   *
   * @param token The token is the authentication token
   * @return claims The claims are the encrypted information inside the token.
   */
  private Claims getAllClaimsFromToken(final String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(resolveToken(token)).getBody();
  }

  /**
   * This method checks the token is expired or not.
   *
   * @param token This is the authentication token.
   * @return true if token is expired or else false
   */
  private Boolean isTokenExpired(final String token) {
    final Date expiration = getExpirationDateFromToken(resolveToken(token));
    return expiration.before(new Date());
  }

  /**
   * This method is to generate the token for the user.
   *
   * @param user This contains the details of the user obtained from database.
   * @return This method returns the token generated.
   */
  public String generateToken(final UserEntity user) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
              .setClaims(claims)
              .setSubject(user.getEmail())
              .setId(UUID.randomUUID().toString())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(
                  new Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
              .signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  /**
   * This method is used to validate whether the token passed is valid or not.
   *
   * @param token This is the authentication token.
   * @param user  This userDTO consists of the details of the user.
   * @return true if token is valid or else false.
   */
  public Boolean validateToken(final String token, final UserDto user) {
    
    final String resolvedToken = resolveToken(token);
    final String username = getUsernameFromToken(resolvedToken);
    return username.equals(user.getEmail()) && !isTokenExpired(resolvedToken);
  }
  
  /**
   * To retrieve the token specific id from jwt token.
   *
   * @param token This is the authentication token.
   * @return tokenSpecificId This is the token specific id.
   */
  public String getTokenSpecificIdFromToken(final String token) {
    return getClaimFromToken(resolveToken(token), Claims::getId);
  }
  
  /**
   * To retrieve the token issued date from jwt token.
   *
   * @param token This is the authentication token.
   * @return tokenSpecificId This is the token issued date.
   */
  public Date getTokenIssuedDateFromToken(final String token) {
    return getClaimFromToken(resolveToken(token), Claims::getIssuedAt);
  }

}
