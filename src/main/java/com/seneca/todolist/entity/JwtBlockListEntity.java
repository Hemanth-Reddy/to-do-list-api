package com.seneca.todolist.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

/**
 * This is the java persistence entity mapping to a table in database.
 *
 * @author hemanth.nagireddy
 *
 */
@Entity
@Table(name = "jwt_block_list")
public class JwtBlockListEntity {

  /**
   * Id associated with the task.
   */
  @Id
  @Column(name = "token_specific_id")
  private String tokenSpecificId;

  /**
   * Id associated with the user.
   */
  @Column(name = "email")
  private String email;

  /**
   * Created time of the task.
   */
  @CreationTimestamp
  @Column(name = "created_at")
  private Date createdAt;

  /**
   * Default constructor.
   */
  public JwtBlockListEntity() {
  }

  /**
   * Constructor for taskEntity.
   *
   * @param tokenSpecificId   Indicates the id of token.
   * @param createdAt Indicates the created time of the token in this table.
   * @param email     Indicates the id of the user.
   */
  public JwtBlockListEntity(final String tokenSpecificId, final Date createdAt,
      final String email) {
    super();
    this.tokenSpecificId = tokenSpecificId;
    this.createdAt = createdAt;
    this.email = email;
  }

  /**
   * Getter for tokenId.
   * 
   * @return tokenId returns the taskId.
   */
  public String getTokenSpecificId() {
    return tokenSpecificId;
  }

  /**
   * Setter for tokenId.
   * 
   * @param tokenSpecificId Id of the jwt.
   */
  public void setTokenSpecificId(final String tokenSpecificId) {
    this.tokenSpecificId = tokenSpecificId;
  }

  /**
   * Getter for the email.
   * 
   * @return email email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setter for email.
   * 
   * @param email email of the user.
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Getter for createdAt.
   * 
   * @return createdAt returns the created time.
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Setter for created time.
   * 
   * @param createdAt created time.
   */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }

}
