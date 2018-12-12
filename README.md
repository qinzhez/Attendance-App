# Attendance Platform - AttendU

## cse682

course repository for cse682 in Syracuse University 2018 Fall


## Contribution
- Qinzhe	Zhang	(qinzhez@gmail.com)
- Minrui	Zhang	(mzhang63@syr.edu)
- Ruxin	Wang	(rwang64@syr.edu)
- Mingyuan	Xie	(mxie10@syr.edu)

## Installation and Run

### Back End Server


1. Database

	The Database used in this project is MySQL. To link the database, you need to install MySql local server. Then, put your db address and user information to [mybatis-config.xml](./dev/src/main/resources/mybatis/mybatis-confg.xml). Otherwise, the backend server will not run properly.

	The database used special designed structure and relations, you can see each schema description in [DB_Document.pdf](./dev/DB_Document.pdf).

2. Server

	The Sever code are developed in Java with gradle project. Import gradle project in Eclipse or other IDEs. Refresh the project or build the project for downloading pre-required packages.

3. Run

	The server has many microservices. Ideally, microservices are standalone. However, there are some production processes may require some of them. 
	3. You should run the Eurake discover service first by running `DevApplication.java`
	3. Then, you can run any other microservices out of order. Run `*Server.java`


### Front End Server


1. NodeJS
	
	NodeJS is required for front end sever

2. Pre-Required Packages and Run
	
	In terminal, please run `npm start` under the `./dev/webapp/` directory. NPM will help download packages. After process, you can see the message to show the server is up, and its address. Type the address in any web browser.


**Details about web usage and installation, please see [./dev/readme.pdf](./dev/readme.pdf)**

## Course Software Requirements Specification

You can find in [./dev/cse682SRS.pdf](./dev/cse682SRS.pdf)
