Simple project testing out file upload to AWS S3: https://s3.console.aws.amazon.com/s3/buckets/

Command to test upload endpoint: 
```curl -v http://localhost:8080/store/upload -X POST -F "file=@test.txt" -H "Content-Type: multipart/form-data"```

Ensure you have AWS CLI installed and credentials configured (~/.aws/credentials). DefaultAWSCredentialsProviderChain 
will use default credentials specified in that file.
 
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

