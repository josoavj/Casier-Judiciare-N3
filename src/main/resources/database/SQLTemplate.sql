/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  haritsimba
 * Created: Dec 8, 2023
 */
-- --------------------------------------------------------------------------------- --
--			CREATION DE L'UTILISATEUR MySql                              --
-- --------------------------------------------------------------------------------- --

-- --------------------------------------------------------------------------------- --
--                      RECONSTRUCTION DE LA BASE DE DONNEE                          --
-- --------------------------------------------------------------------------------- --

DROP DATABASE IF EXISTS Bulletin;
CREATE DATABASE Bulletin;
USE Bulletin;

-- ------------------------------------------------------------------------------------
--                                 CREATION DES TABLES                              ---
-- ------------------------------------------------------------------------------------

-- ---------------------------------infoConserned---------------------------------------
CREATE TABLE infoConserned (
idConserned int(8) UNSIGNED ZEROFILL PRIMARY KEY NOT NULL AUTO_INCREMENT,
acteNaissance int(6) UNSIGNED ZEROFILL NOT NULL,
dateActenaissance date NOT  NULL,
nom varchar(255) NOT NULL,
prenoms varchar(255),
pere varchar(255) NOT NULL,
mere varchar(255) NOT NULL,
dateNaissance date NOT NULL,
lieuNaissance varchar(255) NOT NULL,
situationFamiliale varchar(255) NOT NULL,
profession varchar(255) NOT NULL,
domicile varchar(255) NOT NULL,
sexe varchar(30) NOT NULL,
nationalite varchar(255)  DEFAULT "MALAGASY");

INSERT INTO infoConserned (acteNaissance,dateActenaissance,nom,prenoms,pere,mere,dateNaissance,lieuNaissance,situationFamiliale,profession,sexe,domicile) values
(1112,CURRENT_DATE,"dazai","osamu","nanika","nakahara", CURRENT_DATE ,"shinjuku","celibataire", "portomafia","Masculin","shinjuku");

-- ---------------------------------condamnation---------------------------------------

CREATE TABLE condamnation (idCondamnation int(8) UNSIGNED ZEROFILL PRIMARY KEY NOT NULL AUTO_INCREMENT,
                             dateCondamnation date,
                             coursOuTrubinaux VARCHAR(255) DEFAULT "",
                             natureCrime VARCHAR(255) DEFAULT "",
                             naturePeine VARCHAR(255) DEFAULT "",
                             Observation text DEFAULT "",
                             idConserned int(8) UNSIGNED ZEROFILL NULL REFERENCES
                             infoConserned(idConserned));

-- -------------------------valeur par defaut (condamnation)----------------------------

INSERT INTO condamnation (dateCondamnation,
                            coursOuTrubinaux,
                            natureCrime,
                            naturePeine,
                            Observation,
                            idConserned)
                            values (CURRENT_DATE, 'kille', 'die', 'dmfq', 'fqm',1);

-- --------------------------------table Admin--------------------------------------------

CREATE TABLE Admin (
                     id int(4) UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY NOT NULL,
                     username VARCHAR(256) NOT NULL,
                     name varchar(256) NOT NULL,
                     password VARCHAR(256) NOT NULL );

-- --------------------------------Administrateur par defaut------------------------------

INSERT INTO Admin (username,name, password) VALUES ("Admin", "Admin","Admin");

-- --------------------------------------Printer-------------------------------------------
CREATE TABLE PRINTER (id int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            nameConerned VARCHAR(256) NOT NULL,
                            date date default CURRENT_DATE);
-- --------------------------------------Default printer-----------------------------------
INSERT INTO PRINTER (nameConerned) values ("null");




