CREATE OR REPLACE TYPE administrador_cada_fila AS OBJECT (
    id               NUMBER(19, 0),
    documento        VARCHAR2(255 CHAR),
    tipo_documento   VARCHAR2(255 CHAR),
    clave            VARCHAR2(255 CHAR),
    estado           NUMBER(1, 0)
);