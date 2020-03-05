package com.example.pocdynamodb.alias;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class AliasService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AliasService.class);
  
  @Autowired
  private AmazonDynamoDB amazonDynamoDB;
  
  @Autowired
  private AliasRepository aliasRepository;
  
  @PostConstruct
  public void createTables() {
    LOGGER.info("Creating Alias related tables.");
    
    DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    
    CreateTableRequest tableRequest = dynamoDBMapper
        .generateCreateTableRequest(Alias.class);
    
    tableRequest.setProvisionedThroughput(
        new ProvisionedThroughput(1L, 1L));
    
    TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    
    LOGGER.info("Alias related tables are created.");
  }
  
  public Alias createAlias(Alias alias) {
    LOGGER.info("Attempting to create new alias: {}", new Gson().toJson(alias));
    Alias save = aliasRepository.save(alias);
    LOGGER.info("New alias was successfully created: {}", new Gson().toJson(alias));
    
    return save;
  }
  
  public Alias getAliasById(String aliasId) {
    LOGGER.info("Attempting to find alias with id {}", aliasId);
    Alias alias = aliasRepository.findById(aliasId).orElseThrow();
    LOGGER.info("Alias with id {} was founded.", aliasId);
    
    return alias;
  }
  
  public List<Alias> getAllAlias() {
    LOGGER.info("Attempting to retrieve all alias");
    List<Alias> result = StreamSupport.stream(aliasRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    LOGGER.info("Successfully retrieved alias.");
    
    return result;
  }
  
  public Alias updateAlias(String aliasId, Alias alias) {
    LOGGER.info("Attempting to update new alias: {}", new Gson().toJson(alias));
    
    if (aliasRepository.existsById(aliasId)) {
      Alias updatedUser = aliasRepository.save(alias);
      LOGGER.info("The alias with id {} was successfully updated: {}", aliasId, new Gson().toJson(updatedUser));
      return updatedUser;
    }
    
    LOGGER.info("The alias with id {} couldn't be updated.", aliasId);
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
  
  public void deleteAlias(String aliasId) {
    LOGGER.info("Attempting to delete alias with id {}", aliasId);
    aliasRepository.deleteById(aliasId);
    LOGGER.info("The alias with id {} was deleted:", aliasId);
  }
}
