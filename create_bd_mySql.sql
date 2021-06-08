
CREATE TABLE CATEGORIES (
    no_categorie   INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    libelle        VARCHAR(30) NOT NULL
);  

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(50) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
);


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
);

CREATE TABLE RETRAITS (
	no_article       INTEGER PRIMARY KEY NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
);


ALTER TABLE RETRAITS
    ADD CONSTRAINT retrait_article_fk FOREIGN KEY ( no_article ) REFERENCES  ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
    ON UPDATE no action ;

CREATE TABLE ENCHERES(	
	no_enchere  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
	date_enchere datetime NOT NULL,
	montant_enchere INTEGER NOT NULL,
	no_article INTEGER NOT NULL,
	no_utilisateur INTEGER NOT NULL
 );
 
ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_no_article_fk FOREIGN KEY ( no_article ) REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action ;	

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action ;