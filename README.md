# deserializeVulnExample
Vulnerable spring web application

# disclaimer
vulnerable web application with bad coding practices. No real functionality or UI to work with.
App has deserialization vulnerability, so don't copy anything to your production code.

# Setup
App uses postgres DB, create database schema using these commands:
```
create database spring;

create table users (id int not null, username varchar(255) not null, password varchar(255) not null, primary key (id));

create table profiles (id int not null, user_id int not null, email varchar(255) not null, first_name varchar(255) not null, lastname varchar(255), primary key (id), foreign key (user_id) references users(id));
```
