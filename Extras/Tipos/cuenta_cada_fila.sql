CREATE OR REPLACE TYPE cuenta_cada_fila AS OBJECT (
    id               NUMBER(19, 0),
    estado           NUMBER(1, 0),
    saldo            FLOAT,
    tipo_documento   VARCHAR2(255 CHAR),
    documento        VARCHAR2(255 CHAR),
    id_tipo_cuenta   NUMBER(19, 0)
);