/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  haritsimba
 * Created: Dec 8, 2023
 */

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
nom varchar(255) NOT NULL,
prenoms varchar(255),
pere varchar(255) NOT NULL,
mere varchar(255) NOT NULL,
dateNaissance date NOT NULL,
lieuNaissance varchar(255) NOT NULL,
situationFamiliale varchar(255) NOT NULL,
profession varchar(255) NOT NULL,
domicile varchar(255) NOT NULL,
nationalite varchar(255)  DEFAULT "MALAGASY");

INSERT INTO infoConserned (nom,prenoms,pere,mere,dateNaissance,lieuNaissance,situationFamiliale,profession,domicile) values
("dazai","osamu","nanika","nakahara", CURRENT_DATE ,"shinjuku","celibataire", "portomafia","shinjuku");

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
                            values (null, '', '', '', '',1);



