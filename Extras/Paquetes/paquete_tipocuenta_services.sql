CREATE OR REPLACE PACKAGE paquete_tipocuenta_services AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */
    PROCEDURE save_tipocuenta (
        p_id        IN    VARCHAR2,
        p_nombre    IN    VARCHAR2,
        respuesta   OUT   VARCHAR2
    );
--------------------------------------------------  

    PROCEDURE update_tipocuenta (
        p_id        IN    VARCHAR2,
        p_nombre    IN    VARCHAR2,
        respuesta   OUT   VARCHAR2
    );
------------------------------------------------------------------------------------------------------------------  

    PROCEDURE delete_tipocuenta (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    );    
------------------------------------------------------------------------------------------------------------ 

    FUNCTION get_tipocuentas RETURN tipocuenta_tabla;
  
----------------------------------------------------------------------------------  

    FUNCTION get_tipocuenta_by_id (
        p_id VARCHAR2
    ) RETURN tipocuenta_tabla;
----------------------------------------------  

    FUNCTION tipocuenta_existe (
        p_id VARCHAR2
    ) RETURN NUMBER;

    return tipocuenta_tabla;
  
------------------------------------------
END paquete_tipocuenta_services;