CREATE OR REPLACE TYPE cliente_cada_fila AS OBJECT (
    documento        VARCHAR2(255 CHAR),
    tipo_documento   VARCHAR2(255 CHAR),
    clave            VARCHAR2(255 CHAR),
    estado           NUMBER(1, 0)
);