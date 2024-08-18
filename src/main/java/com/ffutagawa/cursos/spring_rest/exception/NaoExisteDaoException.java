package com.ffutagawa.cursos.spring_rest.exception;

public class NaoExisteDaoException extends RuntimeException{

    public NaoExisteDaoException(String message) {
        super(message);
    }
}
