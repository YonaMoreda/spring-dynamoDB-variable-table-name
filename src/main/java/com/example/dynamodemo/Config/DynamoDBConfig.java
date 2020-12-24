package com.example.dynamodemo.Config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.dynamodemo.Repository")
public class DynamoDBConfig {

    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String awsSecretKey;

    @Value("${amazon.region}")
    private String awsRegion;

    @Value("${amazon.dynamodb.endpoint}")
    private String awsDynamoDBEndPoint;

    @Value("${props.env}")
    private String environment;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        amazonDynamoDB.setEndpoint(awsDynamoDBEndPoint);
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    /**
     * Adding prefix for table name happens here
     * @return
     */
    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverrider) {
        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.withTypeConverterFactory(DynamoDBTypeConverterFactory.standard());
        builder.withTableNameResolver(DynamoDBMapperConfig.DefaultTableNameResolver.INSTANCE);
        builder.withTableNameOverride(tableNameOverrider());
        return builder.build();
    }


    @Bean
    public DynamoDBMapperConfig.TableNameOverride tableNameOverrider() {
        String prefix = environment + "-";
        return DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(prefix);
    }
}
