CREATE OR REPLACE PACKAGE paquete_reportes_services AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */
    PROCEDURE save_reportes (
        p_id                IN    NUMBER,
        p_cantidad          IN    FLOAT,
        p_fecha             IN    TIMESTAMP,
        p_tipo_movimiento   IN    VARCHAR2,
        p_tipo_documento    IN    VARCHAR2,
        p_documento         IN    VARCHAR2,
        respuesta           OUT   VARCHAR2
    );
--------------------------------------------------  

    PROCEDURE update_reportes (
        p_id                IN    NUMBER,
        p_cantidad          IN    FLOAT,
        p_fecha             IN    TIMESTAMP,
        p_tipo_movimiento   IN    VARCHAR2,
        p_tipo_documento    IN    VARCHAR2,
        p_documento         IN    VARCHAR2,
        respuesta           OUT   VARCHAR2
    );
  
-------------------------------------------------------------------------------------

    PROCEDURE delete_reporte (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    );
 ------------------------------------------------- 

    FUNCTION get_reportes RETURN reportes_tabla;
-------------------------------------------  

    FUNCTION get_reporte_by_id (
        p_id NUMBER
    ) RETURN reportes_tabla;
----------------------------------------------  

    FUNCTION reporte_existe (
        p_id NUMBER
    ) RETURN NUMBER;

--------------------------------------------------------------------------------

END paquete_reportes_services;