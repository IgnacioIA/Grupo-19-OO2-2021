# Grupo-19-OO2-2021

-- SCRIPT DB:

CREATE DATABASE IF NOT EXISTS grupo19oo22021; 
USE grupo19oo22021;

INSERT INTO perfil values(1,null,"ROLE_ADMIN",null);
INSERT INTO perfil values(2,null,"ROLE_USER",null);

INSERT INTO persona values(1,"Siciliano",4200000,"Gustavo","Gustavo"); 
INSERT INTO persona values(2,"Mansilla",4200000,"Romina","Romina"); 
INSERT INTO persona values(3,"Vranic",4200000,"Alejandra","Alejandra");
 
INSERT INTO usuario values(1,1,"Siciliano",null,"profe@gmail.com","Gustavo","gustavo",4200000,"1234",1,1,null,1,1); 
INSERT INTO usuario values(2,1,"Mansilla",null,"profe@gmail.com","Romina","romina",4200000,"1234",1,2,null,2,2); 
INSERT INTO usuario values(3,1,"Vranic",null,"profe@gmail.com","Alejandra","alejandra",4200000,"1234",1,1,null,1,3);


INSERT INTO lugar values(1,1854,"CABA"); 
INSERT INTO lugar values(2,1765,"Mar del plata"); 
INSERT INTO lugar values(3,1856,"Bariloche");
INSERT INTO lugar values(4,1854,"Lanús"); 
INSERT INTO lugar values(5,1765,"Temperley"); 
INSERT INTO lugar values(6,1856,"Constitución");
