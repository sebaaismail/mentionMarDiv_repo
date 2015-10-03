create database dbmention;
use dbfichefamil;

CREATE TABLE dbmention.Citoyen
(
	id_cit INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	num_actnaiss INT UNSIGNED NOT NULL,
	annee_actnaiss SMALLINT UNSIGNED NOT NULL,
	code_lieunaiss INT UNSIGNED NOT NULL,
	nom_fr VARCHAR(40) NOT NULL,
	prenom_fr VARCHAR(40) NOT NULL,
	nom_ar VARCHAR(40) NOT NULL,
	prenom_ar VARCHAR(40) NOT NULL,
	date_naiss DATE NULL,
	p_pere VARCHAR(40) NULL,
	np_mere VARCHAR(60) NULL,
	est_masculin BOOLEAN DEFAULT TRUE,
	id_deces INT UNSIGNED DEFAULT 0,
	date_est_presume BOOLEAN DEFAULT FALSE,
	sit_famil CHAR(1) DEFAULT 'c',
	UNIQUE INDEX unique_index (num_actnaiss, annee_actnaiss, code_lieunaiss)

);

CREATE TABLE dbmention.Mariage
(
	id_mar INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	numact_mar INT UNSIGNED,
	date_mar DATE NOT NULL,
	lieu_mar INT UNSIGNED NOT NULL,
	id_epoux INT UNSIGNED NOT NULL,
	id_epouse INT UNSIGNED NOT NULL,
	observ VARCHAR(255) DEFAULT '',

	UNIQUE INDEX unique_index (numact_mar, date_mar, lieu_mar),
	FOREIGN KEY (id_epoux) REFERENCES citoyen (id_cit) ON DELETE CASCADE,
	FOREIGN KEY (id_epouse) REFERENCES citoyen (id_cit) ON DELETE CASCADE

);