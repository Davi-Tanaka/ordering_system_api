package com.app.application.service.model;

import com.app.application.service.model.IResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<D, E> implements IResponseModel<D, E>{
    
    private D data;
    private E error;

    @Override
    public boolean hasError() {
        return error != null;
    }
}