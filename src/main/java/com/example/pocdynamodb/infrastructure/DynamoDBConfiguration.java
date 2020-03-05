package com.example.pocdynamodb.infrastructure;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration@EnableDynamoDBRepositories
    (basePackages = "com.example.pocdynamodb")
public class DynamoDBConfiguration {
  
  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDBEndpoint;
  
  @Value("${amazon.aws.accesskey}")
  private String amazonAWSAccessKey;
  
  @Value("${amazon.aws.secretkey}")
  private String amazonAWSSecretKey;
  
  @Value("${amazon.aws.region}")
  private String region;
  
  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    return AmazonDynamoDBClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, Regions.SA_EAST_1.getName()))
        .build();
  }
  
  @Bean
  public AWSCredentials amazonAWSCredentials() {
    return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
  }
}
