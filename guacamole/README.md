# Guacamole

This docker compose file will create a setup to run Guacamole.  

The tutorial used to create this can be found [here](https://guacamole.apache.org/doc/gug/guacamole-docker.html)  

The web UI can be found here http://localhost:8080/guacamole/  

## Setting up the database 
Once the containers have been created you will need to populate the postgres databases.  

The following command will generate a sql file that will create these databases and place it in a shared directory:  
```
docker exec  guacamole_web_1 /opt/guacamole/bin/initdb.sh --postgres > /shared/initdb.sql
``` 
This can then be run in the postgres container using:  
```
docker exec guacamole_db_1 psql -d guacamole_db -U guacamole_usr -a -f /shared/initdb.sql
```
Alternitivly [psql](https://www.postgresql.org/docs/9.2/static/app-psql.html) commands can be made directly in the terminal using:  
```
docker exec -it guacamole_db_1 psql -h localhost -U guacamole_usr  guacamole_db
``` 

This script will create a user with the following details:
user: guacadmin  
password: guacadmin  