--create tables

-- LOCATIE
CREATE TABLE LOCATIE (
    id_locatie NUMBER,
    judet VARCHAR2(20),
    localitate VARCHAR2(20),
    strada VARCHAR2(20),
    detalii VARCHAR2(50),

    CONSTRAINT pk_locatie
        PRIMARY KEY (id_locatie)
);

-- UTILAJ
CREATE TABLE UTILAJ (
    id_utilaj NUMBER,
    nume VARCHAR2(50) NOT NULL,
    an_fabricatie DATE NOT NULL,

    CONSTRAINT pk_utilaj
        PRIMARY KEY (id_utilaj)
);

-- COLABORATORI
CREATE TABLE COLABORATORI (
    id_colaborator NUMBER,
    nume VARCHAR2(100) NOT NULL,
    numar_telefon VARCHAR2(15) NOT NULL,

    CONSTRAINT pk_colaboratori
        PRIMARY KEY (id_colaborator)
);

-- CLIENT
CREATE TABLE CLIENT (
    id_client NUMBER,
    nume VARCHAR2(100) NOT NULL,
    prenume VARCHAR2(100) NOT NULL,
    numar_telefon VARCHAR2(15) NOT NULL,
    nr_lucrari_anterioare NUMBER,

    CONSTRAINT pk_client
        PRIMARY KEY (id_client),

    CONSTRAINT ck_client_lucrari
        CHECK (nr_lucrari_anterioare >= 0)
);

-- OFERTA
CREATE TABLE OFERTA (
    id_oferta NUMBER,
    pret NUMBER,

    CONSTRAINT pk_oferta
        PRIMARY KEY (id_oferta)
);

-- SEF
CREATE TABLE SEF (
    id_sef NUMBER,
    nume VARCHAR2(100) NOT NULL,
    prenume VARCHAR2(100) NOT NULL,
    numar_telefon VARCHAR2(15) NOT NULL,
    varsta NUMBER,
    experienta NUMBER,
    salariu NUMBER,

    CONSTRAINT pk_sef
        PRIMARY KEY (id_sef),

    CONSTRAINT ck_sef_varsta
        CHECK (varsta >= 18),

    CONSTRAINT ck_sef_experienta
        CHECK (experienta >= 0),

    CONSTRAINT ck_sef_salariu
        CHECK (salariu > 0)
);

-- LUCRARE
CREATE TABLE LUCRARE (
    id_lucrare NUMBER,
    nume VARCHAR2(50),
    cod_locatie NUMBER NOT NULL,
    cod_sef NUMBER NOT NULL,

    CONSTRAINT pk_lucrare
        PRIMARY KEY (id_lucrare),

    CONSTRAINT fk_lucrare_locatie
        FOREIGN KEY (cod_locatie)
        REFERENCES LOCATIE(id_locatie),

    CONSTRAINT fk_lucrare_sef
        FOREIGN KEY (cod_sef)
        REFERENCES SEF(id_sef)
);


-- FACTURA

CREATE TABLE FACTURA (
    id_factura NUMBER,
    cod_oferta_acceptata NUMBER,
    cod_client NUMBER,
    cod_lucrare NUMBER NOT NULL,
    data_facturare DATE NOT NULL,

    CONSTRAINT pk_factura
        PRIMARY KEY (id_factura),

    CONSTRAINT fk_factura_oferta
        FOREIGN KEY (cod_oferta_acceptata)
        REFERENCES OFERTA(id_oferta),

    CONSTRAINT fk_factura_client
        FOREIGN KEY (cod_client)
        REFERENCES CLIENT(id_client),

    CONSTRAINT fk_factura_lucrare
        FOREIGN KEY (cod_lucrare)
        REFERENCES LUCRARE(id_lucrare)
);

-- RECENZIE
CREATE TABLE RECENZIE (
    id_recenzie NUMBER,
    cod_client NUMBER NOT NULL,
    cod_lucrare NUMBER NOT NULL,
    parere VARCHAR2(200),

    CONSTRAINT pk_recenzie
        PRIMARY KEY (id_recenzie),

    CONSTRAINT fk_recenzie_client
        FOREIGN KEY (cod_client)
        REFERENCES CLIENT(id_client),

    CONSTRAINT fk_recenzie_lucrare
        FOREIGN KEY (cod_lucrare)
        REFERENCES LUCRARE(id_lucrare)
);

-- ECHIPA
CREATE TABLE ECHIPA (
    id_echipa NUMBER,
    cod_sef NUMBER NOT NULL,

    CONSTRAINT pk_echipa
        PRIMARY KEY (id_echipa),

    CONSTRAINT fk_echipa_sef
        FOREIGN KEY (cod_sef)
        REFERENCES SEF(id_sef)
);

-- ANGAJAT
CREATE TABLE ANGAJAT (
    id_angajat NUMBER,
    nume VARCHAR2(100) NOT NULL,
    prenume VARCHAR2(100) NOT NULL,
    numar_telefon VARCHAR2(15) NOT NULL,
    varsta NUMBER,
    specializare VARCHAR2(30),
    experienta NUMBER,
    salariu NUMBER,
    cod_echipa NUMBER,

    CONSTRAINT pk_angajat
        PRIMARY KEY (id_angajat),

    CONSTRAINT ck_angajat_varsta
        CHECK (varsta >= 18),

    CONSTRAINT ck_angajat_experienta
        CHECK (experienta >= 0),

    CONSTRAINT ck_angajat_salariu
        CHECK (salariu > 0),

    CONSTRAINT fk_angajat_echipa
        FOREIGN KEY (cod_echipa)
        REFERENCES ECHIPA(id_echipa)
);

-- PROGRAMARE_UTILAJE
CREATE TABLE PROGRAMARE_UTILAJE (
    cod_utilaj NUMBER NOT NULL,
    cod_locatie NUMBER NOT NULL,
    data_inceput DATE NOT NULL,
    data_terminare DATE NOT NULL,

    CONSTRAINT pk_programare_utilaje
        PRIMARY KEY (cod_utilaj, cod_locatie),

    CONSTRAINT fk_programare_utilaj
        FOREIGN KEY (cod_utilaj)
        REFERENCES UTILAJ(id_utilaj),

    CONSTRAINT fk_programare_locatie
        FOREIGN KEY (cod_locatie)
        REFERENCES LOCATIE(id_locatie)
);

-- SELECTIE_COLABORATORI
CREATE TABLE SELECTIE_COLABORATORI (
    id_selectie NUMBER,
    cod_colaborator NUMBER NOT NULL,
    cod_oferta NUMBER NOT NULL,
    procent_colaborator NUMBER DEFAULT 10,

    CONSTRAINT pk_selectie_colaboratori
        PRIMARY KEY (id_selectie),

    CONSTRAINT fk_selectie_colaborator
        FOREIGN KEY (cod_colaborator)
        REFERENCES COLABORATORI(id_colaborator),

    CONSTRAINT fk_selectie_oferta
        FOREIGN KEY (cod_oferta)
        REFERENCES OFERTA(id_oferta)
);