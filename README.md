# Library-Management-System

The project is implemented in Eclipse IDE Oxygen Release (4.7.0) and the backend Database Management is handled using MySQL Release (5.7).

Technical Dependency - 
System should have MYSQL and Apache installed.
a.	If MYSQL is not installed, please click here to download -- https://dev.mysql.com/doc/refman/5.6/en/osx-installation-pkg.html

b.	If Eclipse is not installed, please click here to download and then install -
http://www.eclipse.org/downloads/eclipse-packages/

c.	If Apache is not installed, please click here to download and install -- http://tomcat.apache.org/


Programming Languages Used -
a.	JAVA
b.	JSP
c.	JavaScript
d.	SQL

Instructions to execute - Assuming user has installed Eclipse, Apache and MySQL.
a.	Connect to MySQL and run bash.sql file
mysql -u "xxxx" -p "xxxx" -local -infile mysql
mysql> source library.sql

b.	Import the project in Eclipse and hit "Run on Server" choosing Apache Tomcat 7.0

c.	Locate DatabaseConnection.java file in the project and update MySQL username and password in the file.



How to load data from a csv file into tables -
LOAD DATA LOCAL INFILE 'books.csv' into table BOOK;

Note - This step is already taken care by "source library.sql" step mentioned above.

How to Run Application - 
Click on --  http://localhost:8080/Library_Management_System/home.jsp
