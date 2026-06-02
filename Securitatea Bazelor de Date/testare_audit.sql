INSERT INTO ANGAJAT
VALUES (99,'Audit','Test','0711111111',30,'Electrician',5,5000,1,NULL,NULL);
COMMIT;

UPDATE ANGAJAT
SET SALARIU = 5500
WHERE ID_ANGAJAT = 99;
COMMIT;

DELETE FROM ANGAJAT
WHERE ID_ANGAJAT = 99;
COMMIT;

select * from angajat;

--auditare cu triggeri

--creare tabela audit
CREATE TABLE AUDIT_ANGAJAT (
    id_audit NUMBER GENERATED ALWAYS AS IDENTITY,
    utilizator VARCHAR2(30),
    operatie VARCHAR2(10),
    data_operatie DATE,
    id_angajat NUMBER,
    salariu_vechi NUMBER,
    salariu_nou NUMBER,
    
    CONSTRAINT pk_audit_angajat
    PRIMARY KEY (id_audit)
);


--trigger audit schimbari salariale
CREATE OR REPLACE TRIGGER trg_audit_salariu
AFTER INSERT OR UPDATE OR DELETE
ON ANGAJAT
FOR EACH ROW
BEGIN
    -- INSERT
    IF INSERTING THEN
        INSERT INTO AUDIT_ANGAJAT(
            utilizator,
            operatie,
            data_operatie,
            id_angajat,
            salariu_vechi,
            salariu_nou
        )
        VALUES(
            USER,
            'INSERT',
            SYSDATE,
            :NEW.id_angajat,
            NULL,
            :NEW.salariu
        );
    END IF;

    -- UPDATE
    IF UPDATING THEN
        INSERT INTO AUDIT_ANGAJAT(
            utilizator,
            operatie,
            data_operatie,
            id_angajat,
            salariu_vechi,
            salariu_nou
        )
        VALUES(
            USER,
            'UPDATE',
            SYSDATE,
            :NEW.id_angajat,
            :OLD.salariu,
            :NEW.salariu
        );
    END IF;

    -- DELETE
    IF DELETING THEN
        INSERT INTO AUDIT_ANGAJAT(
            utilizator,
            operatie,
            data_operatie,
            id_angajat,
            salariu_vechi,
            salariu_nou
        )
        VALUES(
            USER,
            'DELETE',
            SYSDATE,
            :OLD.id_angajat,
            :OLD.salariu,
            NULL
        );
    END IF;
END;
/


--demonstratie functionalitate trigger
INSERT INTO ANGAJAT
VALUES (99,'Audit','Test','0711111111',30,'Electrician',5,5000,1,NULL,NULL);

COMMIT;

UPDATE ANGAJAT
SET salariu = 5500
WHERE id_angajat = 99;

COMMIT;

DELETE FROM ANGAJAT
WHERE id_angajat = 99;

COMMIT;


--tabela audit 
SELECT *
FROM AUDIT_ANGAJAT
ORDER BY id_audit;


--AUDIT 3C FGA
BEGIN
DBMS_FGA.ADD_POLICY(
    object_schema   => 'USER_SECURITATE',
    object_name     => 'ANGAJAT',
    policy_name     => 'POLITICA_SALARII_MARI',
    audit_condition => 'SALARIU >= 6000',
    audit_column    => 'SALARIU',
    statement_types => 'SELECT'
);
END;
/


SELECT id_angajat,
       nume,
       salariu
FROM ANGAJAT
WHERE salariu >= 6000;

select * from ANGAJAT;