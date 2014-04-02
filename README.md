This test project was written in April 2012. This project 


It demonstrate how to use:
1. Servlet
2. JSP (JSTL)
3. Database (creating pools and connections)
4. Command pattern for web applications


How to run:

1. run sql scripts from db.sql project folder
2. copy mysqldriver from lib directory to apache/lib
3. add to Tomcat's context.xml file:

<Resource name="dsPool" auth="Container" type="javax.sql.DataSource"
               maxActive="128" maxIdle="32" username="root" password="12345" driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://127.0.0.1:3306/rentcar"/>

4. Login with credentials
admin
user01
user02
user03

password 12345