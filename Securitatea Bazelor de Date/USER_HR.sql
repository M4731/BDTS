--IN USER_HR
select * from user_securitate.client;
select * from user_securitate.angajat;

--in user_hr
SELECT
    id_angajat,
    USER_SECURITATE.DECRIPTARE_TEXT(telefon_criptat) AS telefon_decriptat,
    telefon_criptat
FROM USER_SECURITATE.ANGAJAT;
