NOTES:
- Developed on Java 8. I could have developed it on Java 11, but thought it better to play it safe with Java 8.
- Please update the application.properties files for the data source URL, username, and password of your MySQL database
- Your db user must have authorization to create and update tables
- Property spring.jpa.hibernate.ddl-auto is set 'update', which should take care of DDL and creation of tables
- As this is packaged as a Spring Boot app with embedded Tomcat, to run, just invoke the following on the command line:
java -jar technicalexam-0.0.1-SNAPSHOT
