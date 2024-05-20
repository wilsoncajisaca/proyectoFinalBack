package com.example.demo.tools;

import com.example.demo.exception.GeneralException;
import com.example.demo.pojos.errors.ApiError;

public class ExceptionTool {
    protected GeneralException generalErrorNotFoundRole() {
        return new GeneralException(new ApiError("No se encontro el Rol seleccionado"));
    }

    protected GeneralException generateErrorUserPassNotFound() {
        return new GeneralException(new ApiError("Usuario y/o contraseña incorrectas"));
    }

    protected GeneralException userNotAvalaible() {
        return new GeneralException(new ApiError("Tu usuario no se encuentra disponible para transaccionar"));
    }

    protected GeneralException parameterNotFound() {
        return new GeneralException(new ApiError("El parametro no fue encontrado."));
    }

    protected GeneralException sanctionNotFound() {
        return new GeneralException(new ApiError("No se pudo encontrar la multa seleccionada."));
    }

    protected GeneralException sinisterNotFound() {
        return new GeneralException(new ApiError("No se encontro el Siniestro reportado."));
    }

    protected GeneralException incorrectCredentials(String tokenError) {
        return new GeneralException(new ApiError(tokenError));
    }
}
