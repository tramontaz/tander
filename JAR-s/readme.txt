Start the terminal and enter: 

1) For mysql_launcher.jar:

java -jar mysql_launcher.jar "login" "password" "database URL" "n" and press ENTER
  login - your login into database 
  password - your password into database
  databaseURL - Database connection URL-address (example: jdbc:mysql://localhost/tander?autoReconnect=true)
  n - Number of rows in the database

Запустите терминал в папке с jar-файлом. Ведите в терминале: 
java -jar mysql_launcher.jar "имя пользователя базы данных" "пароль базы данных" "URL-адрес подключения к базе данных" "число строк в базе данных".

2) For sqlite_launcher.jar:

java -jar sqlite_launcher.jar "database name" "n"
  database name - name of your database (example: "test_bd.sqlite")
  n - Number of rows in the database

Запустите терминал в папке с jar-файлом. Ведите в терминале: 
java -jar sqlite_launcher.jar "имя базы данных" "число строк в базе данных"
