package org.pkoleva.rest.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    private String job;
    private String name;
}