create database dbmention;
use dbfichefamil;

CREATE TABLE dbmention.Citoyen
(
	id_cit INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nom_fr VARCHAR(40) NOT NULL,
	prenom_fr VARCHAR(40) NOT NULL,
	nom_ar VARCHAR(40) NOT NULL,
	prenom_ar VARCHAR(40) NOT NULL,
	date_naiss DATE NULL,
	lieunaiss VARCHAR(100) NULL,
	daira_naiss VARCHAR(50) NULL,
	wilaya_naiss VARCHAR(50) NULL,
	emploi VARCHAR(80) DEFAULT '/',
	p_pere VARCHAR(40) NULL,
	np_mere VARCHAR(60) NULL,
	est_masculin BOOLEAN DEFAULT TRUE,
	dateNaiss_est_presume BOOLEAN DEFAULT FALSE,

	UNIQUE INDEX unique_index (nom_fr, prenom_fr, date_naiss, p_pere, np_mere)

);

CREATE TABLE dbmention.Mention
(
	id_ment INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	id_cit INT UNSIGNED NOT NULL,
	numact_mar INT UNSIGNED,
	date_mar DATE NOT NULL,
	date_acte_mar DATE NOT NULL,
	annee_mar INT UNSIGNED NOT NULL,
	acte_ecrit_par VARCHAR(255) DEFAULT 'ضابط الحالة المدنية',
	np_conj_ar VARCHAR(255) DEFAULT '',
	np_conj_fr VARCHAR(255) DEFAULT '',
	est_divorce BOOLEAN DEFAULT FALSE ,
	tribunal_div VARCHAR(255) DEFAULT '',
	date_div DATE,

	UNIQUE INDEX unique_index (id_ment, id_cit, numact_mar),
	FOREIGN KEY (id_cit) REFERENCES citoyen (id_cit) ON DELETE CASCADE

);