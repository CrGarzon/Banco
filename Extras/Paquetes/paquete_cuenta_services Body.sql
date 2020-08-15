CREATE OR REPLACE PACKAGE BODY paquete_cuenta_services AS

    PROCEDURE save_cuenta (
        p_id               IN    NUMBER,
        p_estado           IN    NUMBER,
        p_saldo            IN    FLOAT,
        p_tipo_documento   IN    VARCHAR2,
        p_documento        IN    VARCHAR2,
        p_id_tipo_cuenta   IN    NUMBER,
        respuesta          OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := cuenta_existe(p_id);
        IF existe = 0 THEN
            INSERT INTO cuenta (
                id,
                estado,
                saldo,
                tipo_documento,
                documento,
                id_tipo_cuenta
            ) VALUES (
                p_id,
                p_estado,
                p_saldo,
                p_tipo_documento,
                p_documento,
                p_id_tipo_cuenta
            );

            COMMIT;
            respuesta := 'LA CUENTA SE GUARDO';
        ELSE
            respuesta := 'LA CUENTA YA EXISTE';
        END IF;

    END save_cuenta;
----------------------------------------------------------------------------------------------------------  

    PROCEDURE delete_cuenta (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := cuenta_existe(p_id);
        IF existe = 0 THEN
            DELETE FROM cuenta c
            WHERE
                c.id = p_id;

            COMMIT;
            respuesta := 'LA CUENTA SE BORRO';
        ELSE
            respuesta := 'LA CUENTA NO EXISTE';
        END IF;

    END delete_cuenta;
-------------------------------------------------------------------------------------------------

    PROCEDURE update_cuenta (
        p_id               IN    NUMBER,
        p_estado           IN    NUMBER,
        p_saldo            IN    FLOAT,
        p_tipo_documento   IN    VARCHAR2,
        p_documento        IN    VARCHAR2,
        p_id_tipo_cuenta   IN    NUMBER,
        respuesta          OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := cuenta_existe(p_id);
        IF existe = 0 THEN
            respuesta := 'LA CUENTA NO EXISTE';
        ELSE
            UPDATE cuenta c
            SET
                id = p_id,
                estado = p_estado,
                saldo = p_saldo,
                tipo_documento = p_tipo_documento,
                documento = p_documento,
                id_tipo_cuenta = p_id_tipo_cuenta
            WHERE
                c.id = p_id;

            COMMIT;
            respuesta := 'lA CUENTA SE ACTUALIZO';
        END IF;

    END update_cuenta;
---------------------------------------------------------------------------------------------------------

    FUNCTION get_cuentas RETURN cuenta_tabla AS
        cuentas cuenta_tabla;
    BEGIN
        SELECT
            cuenta_cada_fila(id, estado, saldo, tipo_documento, documento,
                             id_tipo_cuenta)
        BULK COLLECT
        INTO cuentas
        FROM
            cuenta;

        RETURN cuentas;
    END get_cuentas;
-------------------------------------------------------------------------------------------------------

    FUNCTION get_cuenta_by_id (
        p_id NUMBER
    ) RETURN cuenta_tabla AS
        cuentas   cuenta_tabla;
        existe    NUMBER;
    BEGIN
        existe := cuenta_existe(p_id);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                cuenta_cada_fila(id, estado, saldo, tipo_documento, documento,
                                 id_tipo_cuenta)
            BULK COLLECT
            INTO cuentas
            FROM
                cuenta c
            WHERE
                c.id = p_id;

            RETURN cuentas;
        END IF;

    END get_cuenta_by_id;
----------------------------------------------------------------------------------------------------------

    FUNCTION cuenta_existe (
        p_id NUMBER
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            cuenta c
        WHERE
            c.id = p_id;

        RETURN retorno;
    END cuenta_existe;
---------------------------------------------------------------------------------------------------------

    FUNCTION saldo_existe (
        p_saldo NUMBER
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            cuenta c
        WHERE
            c.saldo = p_saldo;

        RETURN retorno;
    END saldo_existe;
-------------------------------------------------------------------------------------------------------

    FUNCTION get_cuenta_by_saldo (
        p_saldo NUMBER
    ) RETURN cuenta_tabla AS
        cuentas   cuenta_tabla;
        existe    NUMBER;
    BEGIN
        existe := saldo_existe(p_saldo);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                cuenta_cada_fila(id, estado, saldo, tipo_documento, documento,
                                 id_tipo_cuenta)
            BULK COLLECT
            INTO cuentas
            FROM
                cuenta c
            WHERE
                c.saldo = p_saldo;

            RETURN cuentas;
        END IF;

    END get_cuenta_by_saldo;
---------------------------------------------------------------------------------------------------------

    FUNCTION estado_existe (
        p_estado NUMBER
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            cuenta c
        WHERE
            c.estado = p_estado;

    END estado_existe;
-------------------------------------------------------------------------------------------------------

    FUNCTION get_cuenta_by_estado (
        p_estado NUMBER
    ) RETURN cuenta_tabla AS
        cuentas   cuenta_tabla;
        existe    NUMBER;
    BEGIN
        existe := estado_existe(p_estado);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                cuenta_cada_fila(id, estado, saldo, tipo_documento, documento,
                                 id_tipo_cuenta)
            BULK COLLECT
            INTO cuentas
            FROM
                cuenta c
            WHERE
                c.estado = p_estado;

            RETURN cuentas;
        END IF;

    END get_cuenta_by_estado;
------------------------------------------------------------------------------------------------------------

END paquete_cuenta_services;