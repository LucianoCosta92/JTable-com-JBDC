CREATE TABLE IF NOT EXISTS 'Biblioteca' (
				'ID' bigint(20) NOT NULL AUTO_INCREMENT,
				'EDITORA' varchar(50) NOT NULL,
				'TITULO' varchar(50) NOT NULL,
				'ISBN' varchar(50) NOT NULL,
				PRIMARY KEY('ID')
);