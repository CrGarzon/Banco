CREATE OR REPLACE TYPE reportes_cada_fila AS OBJECT (
    id                NUMBER(19, 0),
    cantidad          FLOAT,
    fecha             TIMESTAMP(6),
    tipo_movimiento   VARCHAR2(255 CHAR),
    tipo_documento    VARCHAR2(255 CHAR),
    documento         VARCHAR2(255 CHAR)
);