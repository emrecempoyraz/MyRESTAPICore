package com.rest.pojo.workspace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;

/*
Response'da luşan id lerin zaman zaman null dönmesi soruna sebep olabilir bu yüzden
Jackson annotations kullanıyorum.
 */

//@JsonInclude(JsonInclude.Include.NON_NULL) Boş gönderme dedim
//@JsonInclude(JsonInclude.Include.NON_DEFAULT) ID ve Integer gönderme dedim

// Deserialization --> allowsetters Serialization --> allowgetters
@JsonIgnoreProperties(value = {"id"}, allowSetters = true) // Bunu yapmazsam bu kullanım hata verir
public class Workspace {
    @JsonIgnore // Id ve Integer gönderme dedim.I default 0
    private int i;
    private String id;
    @JsonIgnore // Boş hashmap gönderme dedim
    private HashMap<String, String> myHashMap;
    private String name;
    private String type;
    private String description;

    public Workspace(){

    }

    public Workspace(String name, String type, String description){
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public HashMap<String, String> getMyHashMap() {
        return myHashMap;
    }

    public void setMyHashMap(HashMap<String, String> myHashMap) {
        this.myHashMap = myHashMap;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
