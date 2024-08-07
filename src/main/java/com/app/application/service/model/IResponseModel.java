package com.app.application.service.model;

public interface IResponseModel<D, E> {
    /**
     * 
     * @return E
     */
    public E getError();
    
    /**
     * 
     * @return D
     */
    public D getData();
    
    /**
     * Check if error is different than null
     * @return  boolean
     */
    public boolean hasError();
}
