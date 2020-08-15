create or replace PACKAGE PAQUETE_ADMINISTRADOR_SERVICES AS 

    PROCEDURE SAVE_ADMINISTRADOR(
P_ID	    IN  NUMBER,
P_CLAVE	    IN  VARCHAR2,
P_DOCUMENTO	IN  VARCHAR2,
P_NOMBRE	IN  VARCHAR2,
P_TIPO_DOCUMENTO	IN  VARCHAR2,
RESPUESTA   OUT VARCHAR2
);
-------------------------------------------------------------------------------------------
    PROCEDURE UPDATE_ADMINISTRADOR(
P_ID	    IN  NUMBER,
P_CLAVE	    IN  VARCHAR2,
P_DOCUMENTO	IN  VARCHAR2,
P_NOMBRE	IN  VARCHAR2,
P_TIPO_DOCUMENTO	IN  VARCHAR2,
RESPUESTA   OUT VARCHAR2
);
-----------------------------------------------------------------------------------------------
FUNCTION GET_ADMINISTRADOR
RETURN ADMINISTRADOR_TABLA;
-----------------------------------------------------------------------------------------------
FUNCTION GET_ADMINISTRADOR_BY_ID(
P_ID	  NUMBER
)
RETURN ADMINISTRADOR_TABLA;
-----------------------------------------------------------------------------------------------------
FUNCTION ADMINISTRADOR_EXISTENTE(
P_ID	  NUMBER
)
RETURN NUMBER;
-------------------------------------------------------------------------------------------------------
PROCEDURE DELETE_ADMINISTRADOR(
P_ID	    IN  NUMBER,
RESPUESTA   OUT VARCHAR2
);
------------------------------------------------------------------------------------------------
END PAQUETE_ADMINISTRADOR_SERVICES;