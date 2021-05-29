# Grupo-19-OO2-2021

--SCRIPT DB

CREATE DATABASE  IF NOT EXISTS `grupo-19-oo2-2021`;
USE `grupo-19-oo2-2021`;

-- insert perfil para administrador
INSERT INTO perfil values(1,null,"administrador",null);
-- insert perfil para auditor
INSERT INTO perfil values(2,null,"auditor",null);
INSERT INTO administrador values(1);
INSERT INTO auditor values(2);
INSERT INTO usuario 
--SE DEBE GENERAR LA CONTRASEÑA A PARTIR DEL TEST, ya que está encriptada
values(1,1,"Siciliano",null,"profe@gmail.com","Gustavo","Gustavo",4200000,"<CONTRASEÑA ENCRIPTADA>",1,1,null,1);

values(2,1,"Mansilla",null,"profe@gmail.com","Romina","Romina",4200000,"<CONTRASEÑA ENCRIPTADA>",1,2,null,2);

values(3,1,"Vranic",null,"profe@gmail.com",”Alejandra","Alejandra",4200000,"<CONTRASEÑA ENCRIPTADA>",1,1,null,1);
