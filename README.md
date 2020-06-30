Postgresql must be installed on your computer. There must be no database named 'shop'.<br/>
Connection with the postgres server is set for default postgres user:<br/>
  login = "postgres"<br/>
  password = "postgres".<br/>
The server has to be hosted on localhost and on port 5432 (default postgres configuration).<br/>

DATABASE SETUP<br/>
Steps to follow in order to get your local postgresql server ready:<br/>
  1. Download the 'db.sql' dump file of the 'shop' database.<br/>
  2. Execute this command to create the 'shop' database and fill it with data:<br/>
	  sudo -u postgres psql -f db.sql<br/>

BUILDING PROJECT<br/>
Build and execution instructions:<br/>
  1. Download the Test-task repository.<br/>
  2. Use Intellij Idea to open the DatabaseProject project.<br/>
  3. In Intellij Idea, click 'Add configuration' button.<br/>
  4. Click the '+' button and pick 'Application'.<br/>
  5. In the 'Main class' field insert this 'ru.nsu.fit.grigor.database_project.Main'.<br/>
  6. Click Ok.<br/>

TESTING PROJECT<br/>
For testing purposes the 'test_input' directory was created. There are 'input.json' files in it. To test this application, you can further add configurations to this project. For example, you can change your 'Working directory' to '~/DatabaseProject/test_input/search/' and then change 'Programm arguments' to 'search input1.json output1.json'.
