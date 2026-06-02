--criptarea datelor

--modificare tabel angajat || agaudarea coloanelor suplimentare pentru datele criptate
ALTER TABLE ANGAJAT
ADD (
    telefon_criptat RAW(2000),
    salariu_criptat RAW(2000)
);


-- Functie pentru criptarea unui text folosind AES128
CREATE OR REPLACE FUNCTION criptare_text(
    p_text VARCHAR2        -- textul care trebuie criptat
)
RETURN RAW                -- rezultatul va fi binar (RAW)
IS
    -- cheia folosita pentru criptare
    -- AES128 necesita o cheie de 16 bytes (128 biti) =  16 caractere in string
    l_key RAW(16) :=
        UTL_RAW.CAST_TO_RAW('BDTS123456789101');

BEGIN
    -- DBMS_CRYPTO lucreaza doar cu date de tip RAW,
    -- de aceea transformam textul primit in RAW
    RETURN DBMS_CRYPTO.ENCRYPT(
        src => UTL_I18N.STRING_TO_RAW(
                    p_text,
                    'AL32UTF8'
               ),
        -- algoritmul utilizat:
        -- AES128 = algoritm de criptare
        -- CHAIN_CBC = modul CBC
        -- PAD_PKCS5 = completare automata a ultimului bloc
        typ => DBMS_CRYPTO.ENCRYPT_AES128
             + DBMS_CRYPTO.CHAIN_CBC
             + DBMS_CRYPTO.PAD_PKCS5,

        -- cheia folosita la criptare
        key => l_key
    );
END;
/


-- Functie pentru decriptarea valorilor criptate cu AES128
CREATE OR REPLACE FUNCTION decriptare_text(
    p_raw RAW
)
RETURN VARCHAR2
IS
    -- aceeasi cheie folosita la criptare
    l_key RAW(16) :=
        UTL_RAW.CAST_TO_RAW('BDTS123456789101');

    l_decrypted RAW(2000);

BEGIN
    -- decriptarea valorii
    l_decrypted :=
        DBMS_CRYPTO.DECRYPT(
            src => p_raw,
            typ => DBMS_CRYPTO.ENCRYPT_AES128
                 + DBMS_CRYPTO.CHAIN_CBC
                 + DBMS_CRYPTO.PAD_PKCS5,
            key => l_key
        );
    -- transformarea rezultatului RAW in text
    RETURN UTL_I18N.RAW_TO_CHAR(
               l_decrypted,
               'AL32UTF8'
           );
END;
/

SELECT *
FROM ANGAJAT
WHERE id_angajat = 1;

UPDATE ANGAJAT
SET telefon_criptat = criptare_text(numar_telefon),
    salariu_criptat = criptare_text(TO_CHAR(salariu))
WHERE id_angajat = 1;

COMMIT;

SELECT nume,
       decriptare_text(telefon_criptat) AS telefon_original,
       decriptare_text(salariu_criptat) AS salariu_original
FROM ANGAJAT
WHERE id_angajat = 1;

--populare coloane criptate tabel angajat
UPDATE ANGAJAT
SET telefon_criptat = criptare_text(numar_telefon),
    salariu_criptat = criptare_text(TO_CHAR(salariu));

COMMIT;
