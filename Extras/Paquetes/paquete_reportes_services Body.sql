CREATE OR REPLACE PACKAGE BODY paquete_reportes_services AS

    PROCEDURE save_reportes (
        p_id                IN    NUMBER,
        p_cantidad          IN    FLOAT,
        p_fecha             IN    TIMESTAMP,
        p_tipo_movimiento   IN    VARCHAR2,
        p_tipo_documento    IN    VARCHAR2,
        p_documento         IN    VARCHAR2,
        respuesta           OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := reporte_existe(p_id);
        IF existe = 0 THEN
            INSERT INTO reportes (
                id,
                cantidad,
                fecha,
                tipo_movimiento,
                tipo_documento,
                documento
            ) VALUES (
                p_id,
                p_cantidad,
                p_fecha,
                p_tipo_movimiento,
                p_tipo_documento,
                p_documento
            );

            COMMIT;
            respuesta := 'EL REPORTE SE GUARDO';
        ELSE
            respuesta := 'EL REPORTE YA EXISTE';
        END IF;

    END save_reportes;
-----------------------------------------------------------------------------------------------------

    PROCEDURE update_reportes (
        p_id                IN    NUMBER,
        p_cantidad          IN    FLOAT,
        p_fecha             IN    TIMESTAMP,
        p_tipo_movimiento   IN    VARCHAR2,
        p_tipo_documento    IN    VARCHAR2,
        p_documento         IN    VARCHAR2,
        respuesta           OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := reporte_existe(p_id);
        IF existe = 0 THEN
            respuesta := 'NO EXISTE EL REPORTE';
        ELSE
            UPDATE reportes r
            SET
                id = p_id,
                cantidad = p_cantidad,
                fecha = p_fecha,
                tipo_movimiento = p_tipo_movimiento,
                tipo_documento = p_tipo_documento,
                documento = p_documento
            WHERE
                r.id = p_id;

            COMMIT;
            respuesta := 'SE ACTUALIZO EL REPORTE.';
        END IF;

    END update_reportes;
---------------------------------------------------------------------------------------------

    PROCEDURE delete_reporte (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := reporte_existe(p_id);
        IF existe = 0 THEN
            DELETE FROM cuenta c
            WHERE
                c.id = p_id;

            COMMIT;
            respuesta := 'LA CUENTA SE BORRO';
        ELSE
            respuesta := 'LA CUENTA NO EXISTE';
        END IF;

    END delete_reporte;
---------------------------------------------------------------------------------------------------------------------------------

    FUNCTION get_reportes RETURN reportes_tabla AS
        reportes reportes_tabla;
    BEGIN
        SELECT
            reportes_cada_fila(id, cantidad, fecha, tipo_movimiento, tipo_documento,
                               documento)
        BULK COLLECT
        INTO reportes
        FROM
            reportes;

        RETURN reportes;
    END get_reportes;
--------------------------------------------------------------------------------------------

    FUNCTION get_reporte_by_id (
        p_id NUMBER
    ) RETURN reportes_tabla AS
        reportes   reportes_tabla;
        existe     NUMBER;
    BEGIN
        existe := reporte_existe(p_id);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                reportes_cada_fila(id, cantidad, fecha, tipo_movimiento, tipo_documento,
                                   documento)
            BULK COLLECT
            INTO reportes
            FROM
                reportes r
            WHERE
                r.id = p_id;

            RETURN reportes;
        END IF;

    END get_reporte_by_id;
--------------------------------------------------------------------------------------------------

    FUNCTION reporte_existe (
        p_id NUMBER
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            reportes r
        WHERE
            r.id = p_id;

        RETURN retorno;
    END reporte_existe;
--------------------------------------------------------------------------------------------------------

END paquete_reportes_services;