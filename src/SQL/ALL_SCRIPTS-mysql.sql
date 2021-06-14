/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Yulizar
 * Created: Jun 14, 2021
 */
drop  database glidb;

CREATE DATABASE glidb;
use glidb;

CREATE TABLE `tb_master_user_role` (
  `ID` int(11) NOT null AUTO_INCREMENT,
  `NAMA_ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `tb_master_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAMA` varchar(255) DEFAULT NULL,
  `JENIS_KELAMIN` char(1) DEFAULT NULL,
  `TANGGAL_LAHIR` date DEFAULT NULL,
  `ALAMAT` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_a0n3ejiytoflv67fxrvcxjhdj` (`ROLE_ID`),
  CONSTRAINT `FK_a0n3ejiytoflv67fxrvcxjhdj` FOREIGN KEY (`ROLE_ID`) REFERENCES `tb_master_user_role` (`ID`)
); 

insert into tb_master_user_role values (1, 'Admin');
insert into tb_master_user_role values (2, 'QA');
insert into tb_master_user_role values (3, 'Developer');
insert into tb_master_user_role values (4, 'Sales');
insert into tb_master_user_role values (5, 'GA');

insert into tb_master_user values (null,'Karakano','P',STR_TO_DATE('07-25-2012','%m-%d-%Y'),'test alamat kenangan 11', 'yul@gmail.com', 3);
insert into tb_master_user values (null,'Govart','L',STR_TO_DATE('03-03-1995','%m-%d-%Y'),'test alamat teratai raya', 'ben@gmail.com', 1);
insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 12', 'chuck@gmail.com', 5);
insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 5', 'chuck2@gmail.com', 4);
insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 5', 'chuck3@gmail.com', 2);

insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 5', 'chuck4@gmail.com', 1);
insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 5', 'chuck5@gmail.com', 4);
insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 5', 'chuck6@gmail.com', 5);

insert into tb_master_user values (null,'Verlaan','L',STR_TO_DATE('12-13-2001','%m-%d-%Y'),'test alamat melati 12', 'chuck3@gmail.com', 1);
insert into tb_master_user values (null,'Widodo','P',STR_TO_DATE('01-13-2003','%m-%d-%Y'),'test alamat teratai 9', 'marrie@gmail.com',  3);
insert into tb_master_user values (null,'Maryana','P',STR_TO_DATE('02-22-1999','%m-%d-%Y'),'test alamat melati raya', 'chen@gmail.com', 5);
