This is a test task for getting an internship.

Postgresql must be installed on your computer. There must be no database named 'shop'.
Connection with the postgres server is set for default postgres user:
	login = "postgres"
	password = "postgres".
The server has to be hosted on localhost and on port 5432 (default postgres configuration).

Steps to follow in order to get your local postgresql server ready:
  1. Download the 'db.sql' dump file of the 'shop' database.
  2. Execute this command to create the 'shop' database and fill it with data:
	  sudo -u postgres psql -f db.sql



