CREATE OR REPLACE PACKAGE BODY paquete_cliente_services AS

    PROCEDURE save_cliente (
        p_documento        IN    VARCHAR2,
        p_tipo_documento   IN    VARCHAR2,
        p_clave            IN    VARCHAR2,
        p_estado           IN    NUMBER,
        respuesta          OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := cliente_existe(p_documento, p_tipo_documento);
        IF existe = 0 THEN
            INSERT INTO cliente (
                documento,
                tipo_documento,
                clave,
                estado
            ) VALUES (
                p_documento,
                p_tipo_documento,
                p_clave,
                p_estado
            );

            COMMIT;
            respuesta := 'El cliente se guardo satisfactoriamente';
        ELSE
            respuesta := 'El cliente ya existe';
        END IF;

    END save_cliente;
  
  /**
*-------------------------------------------------------------------------------------------------------------------------------------------
*/

    PROCEDURE update_cliente (
        p_documento        IN    VARCHAR2,
        p_tipo_documento   IN    VARCHAR2,
        p_clave            IN    VARCHAR2,
        p_estado           IN    NUMBER,
        respuesta          OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := cliente_existe(p_documento, p_tipo_documento);
        IF existe = 0 THEN
            respuesta := 'El cliente no existe no se puede actualizar';
        ELSE
            UPDATE cliente c
            SET
                documento = p_documento,
                tipo_documento = p_tipo_documento,
                clave = p_clave,
                estado = p_estado
            WHERE
                c.documento = p_documento
                AND c.tipo_documento = p_tipo_documento;

            COMMIT;
            respuesta := 'El cliente se actualizo satisfactoriamente.';
        END IF;

    END update_cliente;
---------------------------------------------------------------------------------------------------------------------------------

    PROCEDURE delete_cliente (
        p_tipo_documento   VARCHAR2,
        p_documento        VARCHAR2,
        respuesta          OUT VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := cliente_existe(p_documento, p_tipo_documento);
        IF existe = 0 THEN
            DELETE FROM cuenta c
            WHERE
                c.documento = p_documento
                AND c.tipo_documento = p_tipo_documento;

            COMMIT;
            respuesta := 'EL CLIENTE SE BORRO';
        ELSE
            respuesta := 'EL CLIENTE NO EXISTE';
        END IF;

    END delete_cliente;
  
  /**
*-------------------------------------------------------------------------------------------------------------------------------------------
*/

    FUNCTION get_clientes RETURN cliente_tabla AS
        clientes cliente_tabla;
    BEGIN
        SELECT
            cliente_cada_fila(documento, tipo_documento, clave, estado)
        BULK COLLECT
        INTO clientes
        FROM
            cliente;

        RETURN clientes;
    END get_clientes;
    
  /**
*-------------------------------------------------------------------------------------------------------------------------------------------
*/

    FUNCTION get_cliente_by_id (
        p_tipo_documento   VARCHAR2,
        p_documento        VARCHAR2
    ) RETURN cliente_tabla AS
        clientes   cliente_tabla;
        existe     NUMBER;
    BEGIN
        existe := cliente_existe(p_documento, p_tipo_documento);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                cliente_cada_fila(documento, tipo_documento, clave, estado)
            BULK COLLECT
            INTO clientes
            FROM
                cliente c
            WHERE
                c.documento = p_documento
                AND c.tipo_documento = p_tipo_documento;

            RETURN clientes;
        END IF;

    END get_cliente_by_id;
  /**
*-------------------------------------------------------------------------------------------------------------------------------------------
*/

    FUNCTION cliente_existe (
        p_documento        VARCHAR2,
        p_tipo_documento   VARCHAR2
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            cliente c
        WHERE
            c.tipo_documento = p_tipo_documento
            AND c.documento = p_documento;

        RETURN retorno;
    END cliente_existe;
  --------------------------------------------------------------------------------------------------------

    FUNCTION cliente_estado (
        p_estado NUMBER
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            cliente c
        WHERE
            c.estado = p_estado;

        RETURN retorno;
    END cliente_estado;
--------------------------------------------------------------------------------------------------------------

    FUNCTION get_cliente_by_estado (
        p_estado NUMBER
    ) RETURN cliente_tabla AS
        clientes   cliente_tabla;
        existe     NUMBER;
    BEGIN
        existe := cliente_estado(p_estado);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                cliente_cada_fila(documento, tipo_documento, clave, estado)
            BULK COLLECT
            INTO clientes
            FROM
                cliente c
            WHERE
                c.estado = p_estado;

            RETURN clientes;
        END IF;

    END get_cliente_by_estado;
  
  
-------------------------------------------------------------------------------------------------------------------  

END paquete_cliente_services;