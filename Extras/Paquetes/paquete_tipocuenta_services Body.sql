CREATE OR REPLACE PACKAGE BODY paquete_tipocuenta_services AS

    PROCEDURE save_tipocuenta (
        p_id        IN    VARCHAR2,
        p_nombre    IN    VARCHAR2,
        respuesta   OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := tipocuenta_existe(p_id);
        IF existe = 0 THEN
            INSERT INTO tipocuenta (
                id,
                nombre
            ) VALUES (
                p_id,
                p_nombre
            );

            COMMIT;
            respuesta := 'EL TIPOCUENTA SE A GUARDADO';
        ELSE
            respuesta := 'YA EXISTE';
        END IF;

    END save_tipocuenta;
---------------------------------------------------------------------------------------------------------------------------------------------------

    PROCEDURE update_tipocuenta (
        p_id        IN    VARCHAR2,
        p_nombre    IN    VARCHAR2,
        respuesta   OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := tipocuenta_existe(p_id);
        IF existe = 0 THEN
            respuesta := 'EL TIPO CUENTA NO EXISTE NO SE PUEDE ACTUALIZAR';
        ELSE
            UPDATE tipocuenta t
            SET
                id = p_id,
                nombre = p_nombre
            WHERE
                t.id = p_id;

            COMMIT;
            respuesta := 'SE ACTUALIZO EL TIPO DE CUENTA.';
        END IF;

    END update_tipocuenta;
---------------------------------------------------------------------------------------------------------------------------------

    PROCEDURE delete_tipocuenta (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := tipocuenta_existe(p_id);
        IF existe = 0 THEN
            DELETE FROM tipocuenta c
            WHERE
                c.id = p_id;

            COMMIT;
            respuesta := 'EL TIPO CUENTA SE BORRO';
        ELSE
            respuesta := 'EL TIPO CUENTA NO EXISTE';
        END IF;

    END delete_tipocuenta;
------------------------------------------------------------------------------------------------------------------------------------

    FUNCTION get_tipocuentas RETURN tipocuenta_tabla AS
        tipocuentas tipocuenta_tabla;
    BEGIN
        SELECT
            tipocuenta_cada_fila(id, nombre)
        BULK COLLECT
        INTO tipocuentas
        FROM
            tipocuenta;

        RETURN tipocuentas;
    END get_tipocuentas;
-------------------------------------------------------------------------------------------------------------------------------

    FUNCTION get_tipocuenta_by_id (
        p_id VARCHAR2
    ) RETURN tipocuenta_tabla AS
        tipocuentas   tipocuenta_tabla;
        existe        NUMBER;
    BEGIN
        existe := tipocuenta_existe(p_id);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                tipocuenta_cada_fila(id, nombre)
            BULK COLLECT
            INTO tipocuentas
            FROM
                tipocuenta t
            WHERE
                t.id = p_id;

            RETURN tipocuentas;
        END IF;

    END get_tipocuenta_by_id;
------------------------------------------------------------------------------------------------------------------------------------

    FUNCTION tipocuenta_existe (
        p_id VARCHAR2
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            tipocuenta t
        WHERE
            t.id = p_id;

        RETURN retorno;
    END tipocuenta_existe;
-----------------------------------------------------------------------------------------------------------------------------------

END paquete_tipocuenta_services;