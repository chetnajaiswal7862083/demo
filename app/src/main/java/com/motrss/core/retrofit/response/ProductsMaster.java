package com.motrss.core.retrofit.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * @Author Dhananjay Kulkarni.
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsMaster {

    @JsonProperty("products")
    private ArrayList<Products> list;

    public ArrayList<Products> getList() {
        return list;
    }

    public void setList(ArrayList<Products> list) {
        this.list = list;
    }
}
