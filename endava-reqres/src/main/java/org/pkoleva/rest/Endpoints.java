package org.pkoleva.rest;

public class Endpoints {
    public static final String users="/api/users";

    public static final String userById(String userId){
        return users + "/" + userId;
    }

}
