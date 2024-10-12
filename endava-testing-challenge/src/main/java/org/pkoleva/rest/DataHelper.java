package org.pkoleva.rest;

import io.restassured.response.Response;

import java.util.Date;

public class DataHelper {

    public <Object> Object getObject(Response r, String path, Class<Object> object){
        return r.jsonPath().getObject(path, object);
    }

    public String  generateUniqueNumber(){
        Date date= new Date();
        return String.valueOf(date.getTime());
    }
}
