# Grupo-19-OO2-2021

--SCRIPT DB

CREATE DATABASE  IF NOT EXISTS `grupo-19-oo2-2021`;
USE `grupo-19-oo2-2021`;


 INSERT INTO perfil values(1,null,"ROLE_ADMIN",null);

 INSERT INTO perfil values(2,null,"ROLE_USER",null);
 
 INSERT INTO ROLE_ADMIN values(1);
 INSERT INTO ROLE_USER values(2); 


INSERT INTO usuario values(1,1,"Siciliano",null,"profe@gmail.com","Gustavo","Gustavo",4200000,"CONTRASEÑA-ENCRIPTADA",1,1,null,1);

INSERT INTO usuario values(2,1,"Mansilla",null,"profe@gmail.com","Romina","Romina",4200000,"CONTRASEÑA-ENCRIPTADA",1,2,null,2);

INSERT INTO usuario  values(3,1,"Vranic",null,"profe@gmail.com","Alejandra","Alejandra",4200000,"CONTRASEÑA-ENCRIPTADA",1,1,null,1);

