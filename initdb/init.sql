# create databases
CREATE DATABASE IF NOT EXISTS `bingo_user`;
CREATE DATABASE IF NOT EXISTS `bingo_invoice`;

# create root user and grant rights
CREATE USER 'root'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;



-- CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';

-- GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' WITH GRANT OPTION;

-- CREATE USER 'username'@'%' IDENTIFIED BY 'password';

-- GRANT ALL PRIVILEGES ON *.* TO 'username'@'%' WITH GRANT OPTION;

-- FLUSH PRIVILEGES;