drop table if exists prodaja; drop table if exists artikl; drop table if exists tipArtikla; drop table if exists korisnik; drop table if exists tipkorisnika; drop table if exists stanje; 
 
CREATE TABLE IF NOT EXISTS tipArtikla (   id LONG NOT NULL AUTO_INCREMENT,   Naziv VARCHAR(45) NULL,   PRIMARY KEY (id)); 
 
CREATE TABLE IF NOT EXISTS Stanje (   id LONG NOT NULL,   Naziv VARCHAR(45) NOT NULL,   PRIMARY KEY (id)); 
 
CREATE TABLE IF NOT EXISTS Artikl (   id LONG NOT NULL AUTO_INCREMENT,   Naslov VARCHAR(45) NOT NULL,   Opis VARCHAR(45) NOT NULL,   Cijena DECIMAL(10,2) NOT NULL,   kvadratura INT NULL,   snaga DECIMAL(6,2) NULL,   idStanje LONG NOT NULL,   idTipArtikla LONG NOT NULL,   PRIMARY KEY (id),   FOREIGN KEY (idTipArtikla) REFERENCES tipArtikla (id),   FOREIGN KEY (idStanje) REFERENCES Stanje (id)); 
 
 
CREATE TABLE IF NOT EXISTS tipKorisnika (   id LONG NOT NULL AUTO_INCREMENT,Naziv VARCHAR(45) NOT NULL,   PRIMARY KEY (id)); 
 
 
CREATE TABLE IF NOT EXISTS Korisnik (   id LONG NOT NULL AUTO_INCREMENT,   Naziv VARCHAR(45) NULL,   Ime VARCHAR(45) NULL,   Prezime VARCHAR(45) NULL,   Web VARCHAR(45) NULL,   Telefon VARCHAR(45) NOT NULL,   Email VARCHAR(45) NOT NULL,   idTipKorisnika LONG NOT NULL,   PRIMARY KEY (id),   FOREIGN KEY (idTipKorisnika) REFERENCES tipKorisnika (id)); 
 
 
 
CREATE TABLE IF NOT EXISTS Prodaja (   idArtikl LONG NOT NULL,   idKorisnik LONG NOT NULL,   datumObjave DATE NOT NULL,   PRIMARY KEY (idArtikl, idKorisnik),   FOREIGN KEY (idArtikl) REFERENCES Artikl (id),   FOREIGN KEY (idKorisnik) REFERENCES Korisnik (id));    insert into tipArtikla(id, naziv) values (1, 'Automobil'); insert into tipArtikla(id, naziv) values (2, 'Usluga'); insert into tipArtikla(id, naziv) values (3, 'Stan'); 
 
insert into tipKorisnika(id, naziv) values (1, 'PrivatniKorisnik'); insert into tipKorisnika(id, naziv) values (2, 'PoslovniKorisnik'); 
 
insert into stanje(id, naziv) values (1, 'novo'); insert into stanje(id, naziv) values (2, 'izvrsno'); insert into stanje(id, naziv) values (3, 'rabljeno'); insert into stanje(id, naziv) values (4, 'neispravno'); 
 
insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) values ('BMW', 'BMW M5', 120000, 625, 2, 1); insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) values ('Stan', 'Mali stan', 100000, 45, 1, 3); insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) values ('Instrukcije', 'Instrukcije iz Jave', 300, 1, 2); 
 
insert into korisnik(ime, prezime, email, telefon, idtipkorisnika) values('Tin', 'Kramberger', 'tin@tvz.hr', '12345654321', 1); insert into korisnik(naziv, web, email, telefon, idtipkorisnika) values('TVZ', 'www.tvz.hr', 'info@tvz.hr', '111222', 2);