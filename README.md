# **RDS Replica Connector for Reader/Writer Separation**

## **Overview**
This repository provides a simple solution for application teams to seamlessly connect to **RDS reader** and **writer replicas** without making significant changes to the source code.

### **Features**
- **Reader replica connections** are automatically handled by using `get`, `find`, or `list` prefixed functions.
- **Writer replica connections** are managed through `insert`, `update`, or `delete` prefixed functions.
- Minimal source code changes required.
- Easy-to-implement solution for managing separate read and write database operations.

## **Local Setup**
1. Clone the repository:
    ```bash
    git clone https://github.com/JohnDeere-Tech/isg-alexandria-sample-java-rds-replica.git
    cd isg-alexandria-sample-java-rds-replica
    ```

2. Mention the reader and write replica strings in [application.properties](src/main/resources/application.properties) file
   ```bash
   spring.datasource.write.jdbc-url=jdbc:mysql://localhost:3306/master

   spring.datasource.read.jdbc-url=jdbc:mysql://localhost:3306/slave
   ```
   
3. Encrypt the DB passwords using 2 way encrption key and mention that as environment variable , 
   ``` bash
   jasypt.encryptor.password=*******
   ```
4. Use the prefixed functions in your application:
    - For read operations (queries), use methods prefixed with `get`, `find`, or `list`.
    - For write operations (inserts/updates/deletes), use methods prefixed with `insert`, `update`, or `delete`.
    - For changing the prefix/suffix for reader/writer functions,visit [DataSourceAspect.java](src/main/java/com/action/reader/readerwriterreplica/datasource/DataSourceAspect.java)
    -  
### **Example:**

   ```java
   // Read operation example
   User user = userRepository.findById(123);  // Automatically routed to reader replica
   
   // Write operation example
   userRepository.insertNewUser(newUser);    // Automatically routed to writer replica
   ```
5. To build the project run on windows with java correto 21,
      
```bash 
mvnw.cmd clean package
```
