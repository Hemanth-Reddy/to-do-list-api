package com.seneca.todolist.config;

import com.seneca.todolist.entity.JwtBlockListEntity;
import com.seneca.todolist.repository.IJwtBlockListRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class consists of a scheduler for running after specified intervals.
 *
 * @author hemanth.nagireddy
 *
 */
@Component
public class ExpiredTokenRemoveScheduler {

  
  /**
   * LOGGER instance created using the loggerfactory.getLogger method.
   */
  private static final Logger LOG = LoggerFactory
      .getLogger(ExpiredTokenRemoveScheduler.class);
  
  /**
   * This interface connects with the database via java persistence api and used
   * for interacting with database. Used for block list entity.
   */
  @Autowired
  public IJwtBlockListRepository iJwtBlockListRepository;
  
  /**
   * A variable for token validity.
   */
  @Value("${jwt.validity}")
  public Long jwtTokenValidity;
  
  /**
   * Scheduler for removing the expired tokens from database.
   */
  @Scheduled(fixedDelay = 1800000)
  public void removeExpiredTokens() {
    
    LOG.info("removing the expired tokens..");
    
    List<JwtBlockListEntity> oldTokens = iJwtBlockListRepository.findAll();
    
    for (JwtBlockListEntity jwt : oldTokens) {
      
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(jwt.getCreatedAt());
      calendar.add(Calendar.SECOND, jwtTokenValidity.intValue());

      if (calendar.getTime().before(new Date())) {
        iJwtBlockListRepository.delete(jwt);
      }
    }
    LOG.info("Expired tokens removed..");
  }
}
