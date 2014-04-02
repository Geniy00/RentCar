This test project was written in April 2012. 

This project presents car rent service.
Users can see cars, chose any car and rent it. Cars can be added and changed by administrator. Moreover, administrator can accept or reject user's orders.
<br><br>

**It demonstrate how to use:**

1. Servlet
2. JSP (JSTL)
3. Database (creating pools and connections)
4. Command pattern for web applications

<br><br>
**How to run:**

1. run sql scripts from db.sql project folder
2. copy mysqldriver from lib directory to apache/lib
3. add to Tomcat's context.xml file:
```xml
<Resource name="dsPool" auth="Container" type="javax.sql.DataSource"
maxActive="128" maxIdle="32" username="root" password="12345" driverClassName="com.mysql.jdbc.Driver"
 url="jdbc:mysql://127.0.0.1:3306/rentcar"/>
```
4.Login with credentials <br/>
admin<br/>
user01<br/>
user02<br/>
user03<br/>

password: 12345
