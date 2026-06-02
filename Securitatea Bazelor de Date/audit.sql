--SQL AUDIT

-- Auditare standard asupra tabelei ANGAJAT

--  pentru SELECT
AUDIT SELECT ON USER_SECURITATE.ANGAJAT BY ACCESS;
--  pentru INSERT
AUDIT INSERT ON USER_SECURITATE.ANGAJAT BY ACCESS;
--  pentru UPDATE
AUDIT UPDATE ON USER_SECURITATE.ANGAJAT BY ACCESS;
--  pentru DELETE
AUDIT DELETE ON USER_SECURITATE.ANGAJAT BY ACCESS;

-- SELECT TABELA AUDIT DEFAULT
SELECT * 
FROM dba_audit_trail
WHERE obj_name = 'ANGAJAT'
ORDER BY timestamp DESC;


--Autitare cu triggeri

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


--verificare audit FGA
SELECT db_user, object_name, policy_name,sql_text, timestamp
FROM dba_fga_audit_trail
ORDER BY timestamp DESC;