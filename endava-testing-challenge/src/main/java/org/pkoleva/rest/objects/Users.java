package org.pkoleva.rest.objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Users {
    private String page;
    private String perPage;
    private String total;
    private String totalPages;
    private User[] data;
}