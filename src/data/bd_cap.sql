CREATE SEQUENCE rank_id_seq;

CREATE TABLE cap.User(
	idUser  INTEGER NOT NULL default nextval('rank_id_seq'),
	login varchar(100),
	password varchar(100),
	pathBiometric varchar(100),
	Constraint idUserPK PRIMARY KEY(idUser)
);