# geodataservice
A RESTful webservice for geographic positions of cities in Germany. 
It is implemented with RESTlet-Framework. 
It is only a pet project for RESTful web services (https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS)
with CORS (https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS). 

Used Database Dump for geographic positions of cities in Germany:
http://www.fa-technik.adfc.de/code/opengeodb/DE.sql 
The data is provieded by OpenGeoDB Project (http://opengeodb.org/)

The database connection is handled by Hibernate ORM. More details you can find in the pom.xml and the hibernate.cfg.xml.
