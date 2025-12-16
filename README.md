Library Book Record System - README

The Library Book Record System is a Java-based web application built using Servlets, JSP, and JDBC. It allows librarians and administrators to manage books, librarian accounts, and general library operations through an easy-to-use interface.

Features

User Authentication

Secure login system

Session-based access control

Prevents unauthorized page access

Librarian Account Management

View all librarian accounts

Update librarian details (first name, last name, email)

Delete librarian accounts (with protection against deleting your own account)

Displays only librarians (not admins)

Book Management

Add new books

Update book information

Delete books

View complete list of books

User Interface Enhancements

Background images with blur effects

Glass-style translucent dialog frames

Warm brown and gold theme that matches a classic library aesthetic

Responsive layout that works across different screen sizes

About Us Page

Background blur effect

Team member display

Library statistics

Technologies Used

Backend: Java Servlets (JDK 8 or later), JDBC
Frontend: JSP, HTML, CSS, JavaScript
Database: MySQL or MariaDB
Application Server: GlassFish 4.1.1 (Tomcat compatible)
Build: IDE-based or Maven (optional)

Project Structure (Overview)

src/
com.example.library.servlets/
AdminUsers.java
AdminUpdateUser.java
AdminDeleteUser.java
AdminCreateUser.java
BookListServlet.java
BookUpdateServlet.java
BookDeleteServlet.java
LoginServlet.java
LogoutServlet.java
com.example.library.filters/
AuthFilter.java
EncodingFilter.java
com.example.library.models/
Book.java
Librarian.java
com.example.library.utils/
DBUtil.java

web/
adminUsers.jsp
adminUserEdit.jsp
adminHome.jsp
bookList.jsp
addBook.jsp
aboutus.jsp
login.jsp
assets/
styles.css
library.jpg
images2.jpg
WEB-INF/
web.xml
jspf/header.jspf
jspf/footer.jspf

Database Setup

Create the database:

CREATE DATABASE library_db;

Create the tables (adjust as needed based on DBUtil setup):

CREATE TABLE librarians (
firstname VARCHAR(50),
lastname VARCHAR(50),
email VARCHAR(100),
username VARCHAR(50) PRIMARY KEY,
password_hash VARCHAR(255),
role VARCHAR(20)
);

CREATE TABLE books (
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(100),
author VARCHAR(80),
category VARCHAR(50),
amount INT
);

Update DBUtil.java with your database connection:

URL: jdbc:mysql://localhost:3306/library_db
User: root
Password: your-password

Running the Application

Open the project in NetBeans, IntelliJ, or Eclipse.

Ensure GlassFish or Tomcat is running.

Deploy the project (Run Project).

Access the system in your browser:

http://localhost:8080/LibraryBookRecordSystem/

Security Notes

Passwords are stored as hashes.

Session prevents unauthorized access.

Users cannot delete their own account.

Only librarians appear in the management interface.

Future Improvements

Borrow/return book system

Email notifications

Search and filtering

Reporting tools

Additional user roles

Authors

Developers: Sisay Yigezu, Hikma Mohammed
