# Amazon DynamoDB POC

This project is just for learning and test proposals.
Do not use it in production.

### Dependencies
- Java 11
- Maven
- Docker
- AWS SDK for Java 2.0
- [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-linux.html)

### Running

##### Download the DynamoDB container image (Only need for the very first time)

```
docker pull amazon/dynamodb-local (only first time)
```

##### Start the local DynamoDB container

```
docker run -d -p 8000:8000 amazon/dynamodb-local
```

##### Run the application from command line with
```
mvn spring-boot:run
```

### Examples
##### Create an alias

```shell script
curl --location --request POST 'http://localhost:8080/alias' \
--header 'Content-Type: application/json' \
--data-raw '{
	"nickName": "dynamodb",
	"cpf": 999999999999,
	"email": "poc@dynamodb.com"
}'
```


##### Retrieve all alias

```shell script
curl --location --request GET 'http://localhost:8080/alias' \
--header 'Content-Type: application/json'
```

##### Retrieve one alias

```shell script
curl --location --request GET 'http://localhost:8080/alias/<ALIAS_ID>' \
--header 'Content-Type: application/json'
```

##### Update an alias

```shell script
curl --location --request PUT 'http://localhost:8080/alias' \
--header 'Content-Type: application/json' \
--data-raw '{
	"id": "<ALIAS_ID>",
	"nickName": "changed-dynamodb",
	"cpf": 11111111111,
	"email": "poc@dynamodb.com"
}'
```
##### Delete an alias

```shell script
curl --location --request DELETE 'http://localhost:8080/alias/<ALIAS_ID>'
```


### Helpful links
- [DynamoDB Available Commands](https://docs.aws.amazon.com/cli/latest/reference/dynamodb/index.html)
- [From SQL to NoSQL](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/SQLtoNoSQL.html)