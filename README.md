# DATABASE_TERMPROJECT

Users can search for properties, browse comprehensive listings, save their favorite properties, and examine housing market trends with HomeScope, an online tool for housing market analysis.

Property data is stored in a relational database by the system, and SQL queries comprising joins and aggregates are used to deliver insights.


1. – Backend (Repositories)
2.  – Controllers & Services
3. Reneka – Frontend (Mustache UI)
4. Anudeepthi – Documentation, Database Creation

Technologies Used: JDBC, Maven, Java, MySQL, SpringBoot, Mustache Templates

Database Info: 
Database Name: homescope_db
Username: mysql-server-x370
Password: mysqlpass

Test Users:


Application Features:
- Registering and logging in
- Use filters to find properties (city, price range, bedrooms)
- A glimpse of the property's details and pricing history
- Save and delete favorited properties
- Market analytics dashboard (average price, price per square foot)

How to Start Application
1. Start MySQL server in Docker
2. Run ddl.sql to create database 
3. Run data.sql to populate the database with the synthetic data created
4. Open the project in an IDE - (we used VS code)
5. Run the Spring Boot application
