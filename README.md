# PetStore
A sample pet store project using Spring Boot and AngularJS. Users can add/view pets by interacting with a front-end component written using AngularJS, and through a RESTful service and to a database.

This project is written to work with MS SQL Server 2012 and OracleDB. For other databases, additional dependencies may have to be installed.

## Set Up
1. Under ~/.m2/settings.xml, set the following property variables for your local database. Eg.:

        <petstore.test.db.url>
		    jdbc:jtds:sqlserver://localhost:1433/RBC_Archive;instance=SQLEXPRESSTEST
	    </petstore.test.db.url>
	    <db.username>db_username</db.username>
	    <db.password>db_password</db.password>

2. Run 'npm install' in project root.

3. Run 'mvn spring-boot:run' to start the server-side component.

4. Run 'gulp serve' to start the front-end component.