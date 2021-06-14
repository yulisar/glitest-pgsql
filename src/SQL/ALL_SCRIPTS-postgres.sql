/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Yulizar
 * Created: Jun 14, 2021
 */
CREATE TABLE public.tb_master_user_role (
	id int4 NOT NULL,
	nama_role varchar(255) NULL,
	CONSTRAINT tb_master_user_role_pkey PRIMARY KEY (id)
);

CREATE TABLE public.tb_master_user (
	id int4 NOT NULL,
	nama varchar(255) NULL,
	jenis_kelamin bpchar(1) NULL,
	tanggal_lahir date NULL,
	alamat varchar(255) NULL,
	email varchar(255) NULL,
	role_id int4 NULL,
	CONSTRAINT tb_master_user_pkey PRIMARY KEY (id),
	CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES tb_master_user_role(id)
);

CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

insert into tb_master_user_role values (1, 'Admin');
insert into tb_master_user_role values (2, 'QA');
insert into tb_master_user_role values (3, 'Developer');
insert into tb_master_user_role values (4, 'Sales');
insert into tb_master_user_role values (5, 'GA');

insert into tb_master_user values (nextval('hibernate_sequence'),'Karakano','P',TO_DATE('07-JUL-2012','dd-Mon-yyyy'),'test alamat kenangan 11', 'yul@gmail.com', 3);

insert into tb_master_user values (nextval('hibernate_sequence'),'Govart','L',TO_DATE('07-Jul-2012','dd-Mon-yyyy'),'test alamat teratai raya', 'ben@gmail.com', 1);
insert into tb_master_user values (nextval('hibernate_sequence'),'Anang','L',TO_DATE('11-Mar-1978','dd-Mon-yyyy'),'test alamat melati 12', 'chuck@gmail.com', 5);
insert into tb_master_user values (nextval('hibernate_sequence'),'Schaltz','L',TO_DATE('22-Dec-2012','dd-Mon-yyyy'),'test alamat melati 5', 'chuck2@gmail.com', 4);
insert into tb_master_user values (nextval('hibernate_sequence'),'Verlaan','L',TO_DATE('05-Apr-2010','dd-Mon-yyyy'),'test alamat melati 5', 'chuck3@gmail.com', 2);

insert into tb_master_user values (nextval('hibernate_sequence'),'Schaltz','L',TO_DATE('1-May-1999','dd-Mon-yyyy'),'test alamat melati 5', 'chuck4@gmail.com', 1);
insert into tb_master_user values (nextval('hibernate_sequence'),'Smith','L',TO_DATE('25-Feb-1997','dd-Mon-yyyy'),'test alamat melati 5', 'chuck5@gmail.com', 4);
insert into tb_master_user values (nextval('hibernate_sequence'),'Widodo','L',TO_DATE('15-Nov-1980','dd-Mon-yyyy'),'test alamat melati 5', 'chuck6@gmail.com', 5);
