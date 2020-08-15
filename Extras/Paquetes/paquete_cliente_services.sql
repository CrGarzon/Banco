CREATE OR REPLACE PACKAGE paquete_cliente_services AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */
    PROCEDURE save_cliente (
        p_documento        IN    VARCHAR2,
        p_tipo_documento   IN    VARCHAR2,
        p_clave            IN    VARCHAR2,
        p_estado           IN    NUMBER,
        respuesta          OUT   VARCHAR2
    );
--------------------------------------------------  

    PROCEDURE update_cliente (
        p_documento        IN    VARCHAR2,
        p_tipo_documento   IN    VARCHAR2,
        p_clave            IN    VARCHAR2,
        p_estado           IN    NUMBER,
        respuesta          OUT   VARCHAR2
    );
---------------------------------------------------------------------------------------------------------

    PROCEDURE delete_cliente (
        p_tipo_documento   VARCHAR2,
        p_documento        VARCHAR2,
        respuesta          OUT VARCHAR2
    );
 ------------------------------------------------- 

    FUNCTION get_clientes RETURN cliente_tabla;
-------------------------------------------  

    FUNCTION get_cliente_by_id (
        p_tipo_documento   VARCHAR2,
        p_documento        VARCHAR2
    ) RETURN cliente_tabla;
----------------------------------------------  

    FUNCTION cliente_existe (
        p_documento        VARCHAR2,
        p_tipo_documento   VARCHAR2
    ) RETURN NUMBER;

-------------------------------------------------------------------------------

    FUNCTION get_cliente_by_estado (
        p_estado NUMBER
    ) RETURN cliente_tabla;

-------------------------------------------------------------------------

    FUNCTION cliente_estado (
        p_estado NUMBER
    ) RETURN NUMBER;

------------------------------------------------------------------------

END paquete_cliente_services;