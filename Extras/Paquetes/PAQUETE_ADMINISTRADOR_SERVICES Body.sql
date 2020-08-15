CREATE OR REPLACE PACKAGE BODY paquete_administrador_services AS

    PROCEDURE save_administrador (
        p_id               IN    NUMBER,
        p_clave            IN    VARCHAR2,
        p_documento        IN    VARCHAR2,
        p_nombre           IN    VARCHAR2,
        p_tipo_documento   IN    VARCHAR2,
        respuesta          OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
    -- TAREA: Se necesita implantación para PROCEDURE PAQUETE_ADMINISTRADOR_SERVICES.SAVE_ADMINISTRADOR
        existe := administrador_existente(p_id);
        IF existe = 0 THEN
            INSERT INTO administrador (
                id,
                clave,
                documento,
                nombre,
                tipo_documento
            ) VALUES (
                p_id,
                p_clave,
                p_documento,
                p_nombre,
                p_tipo_documento
            );

            COMMIT;
            respuesta := 'EL ADMINISTRADOR SE GUARDO SACTIFACTORIAMENTE';
        ELSE
            respuesta := 'EL ADMINISTRADOR YA EXISTE';
        END IF;

    END save_administrador;
-----------------------------------------------------------------------------------------------------------------

    PROCEDURE update_administrador (
        p_id               IN    NUMBER,
        p_clave            IN    VARCHAR2,
        p_documento        IN    VARCHAR2,
        p_nombre           IN    VARCHAR2,
        p_tipo_documento   IN    VARCHAR2,
        respuesta          OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
    -- TAREA: Se necesita implantación para PROCEDURE PAQUETE_ADMINISTRADOR_SERVICES.UPDATE_ADMINISTRADOR
        existe := administrador_existente(p_id);
        IF existe = 0 THEN
            respuesta := 'EL ADMINISTRADR NO EXISTE';
        ELSE
            UPDATE administrador d
            SET
                id = p_id,
                clave = p_clave,
                documento = p_documento,
                nombre = p_nombre,
                tipo_documento = p_tipo_documento
            WHERE
                d.id = p_id;

            COMMIT;
            respuesta := 'EL CLIENTE SE ACTUALIZO SATISFACTORIAMENTE';
        END IF;

    END update_administrador;
----------------------------------------------------------------------------------------------------------------------------

    PROCEDURE delete_administrador (
        p_id        IN    NUMBER,
        respuesta   OUT   VARCHAR2
    ) AS
        existe NUMBER;
    BEGIN
        existe := administrador_existente(p_id);
        IF existe = 0 THEN
            DELETE FROM administrador a
            WHERE
                a.id = p_id;

            COMMIT;
            respuesta := 'EL ADMINISTRADOR SE BORRO';
        ELSE
            respuesta := 'EL ADMIISTRADOR NO EXISTE';
        END IF;

    END delete_administrador;
 
 
 ------------------------------------------------------------------------------------------------------------------------------

    FUNCTION get_administrador RETURN administrador_tabla AS
        administrador administrador_tabla;
    BEGIN
    -- TAREA: Se necesita implantación para FUNCTION PAQUETE_ADMINISTRADOR_SERVICES.GET_ADMINISTRADOR
        SELECT
            administrador_cada_fila(id, clave, documento, nombre, tipo_documento)
        BULK COLLECT
        INTO administrador
        FROM
            administrador;

        RETURN administrador;
    END get_administrador;
------------------------------------------------------------------------------------------------------------

    FUNCTION get_administrador_by_id (
        p_id NUMBER
    ) RETURN administrador_tabla AS
        administrador   administrador_tabla;
        existe          NUMBER;
    BEGIN
    --TAREA: Se necesita implantación para FUNCTION PAQUETE_ADMINISTRADOR_SERVICES.GET_ADMINISTRADOR_BY_ID
        existe := administrador_existente(p_id);
        IF existe = 0 THEN
            RETURN NULL;
        ELSE
            SELECT
                administrador_cada_fila(id, clave, documento, nombre, tipo_documento)
            BULK COLLECT
            INTO administrador
            FROM
                administrador;

            RETURN administrador;
        END IF;

    END get_administrador_by_id;
-----------------------------------------------------------------------------------------------------------------------

    FUNCTION administrador_existente (
        p_id NUMBER
    ) RETURN NUMBER AS
        retorno NUMBER;
    BEGIN
    -- TAREA: Se necesita implantación para FUNCTION PAQUETE_ADMINISTRADOR_SERVICES.ADMINISTRADOR_EXISTENTE
        SELECT
            COUNT(*)
        INTO retorno
        FROM
            administrador d
        WHERE
            d.id = p_id;

        RETURN retorno;
    END administrador_existente;

END paquete_administrador_services;