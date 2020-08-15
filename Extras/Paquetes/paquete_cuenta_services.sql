CREATE OR REPLACE PACKAGE paquete_cuenta_services AS
    PROCEDURE save_cuenta (
        p_id               IN    NUMBER,
        p_estado           IN    NUMBER,
        p_saldo            IN    FLOAT,
        p_tipo_documento   IN    VARCHAR2,
        p_documento        IN    VARCHAR2,
        p_id_tipo_cuenta   IN    NUMBER,
        respuesta          OUT   VARCHAR2
    );
--------------------------------------------------  

    PROCEDURE update_cuenta (
        p_id               IN    NUMBER,
        p_estado           IN    NUMBER,
        p_saldo            IN    FLOAT,
        p_tipo_documento   IN    VARCHAR2,
        p_documento        IN    VARCHAR2,
        p_id_tipo_cuenta   IN    NUMBER,
        respuesta          OUT   VARCHAR2
    );
-------------------------------------------------------------------------

    PROCEDURE delete_cuenta (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    );

------------------------------------------------- 

    FUNCTION get_cuentas RETURN cuenta_tabla;
-------------------------------------------  

    FUNCTION get_cuenta_by_id (
        p_id NUMBER
    ) RETURN cuenta_tabla;
---------------------------------------------- 

    FUNCTION get_cuenta_by_saldo (
        p_saldo NUMBER
    ) RETURN cuenta_tabla;

-----------------------------------------------------------

    FUNCTION cuenta_existe (
        p_id NUMBER
    ) RETURN NUMBER;
-----------------------------------------------------------

    FUNCTION saldo_existe (
        p_saldo NUMBER
    ) RETURN NUMBER;
----------------------------------------------------------------

    FUNCTION estado_existe (
        p_estado NUMBER
    ) RETURN NUMBER;

--------------------------------------------------

    FUNCTION get_cuenta_by_estado (
        p_estado NUMBER
    ) RETURN cuenta_tabla;
-------------------------------------------------------------------------------------------

END paquete_cuenta_services;