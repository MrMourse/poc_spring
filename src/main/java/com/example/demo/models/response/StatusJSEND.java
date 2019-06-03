package com.example.demo.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Objet regroupant les divers status JSEND possibles.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum StatusJSEND {

    //Tout s'est bien passé
    SUCCESS ("success"),

    //Il y a eu un problème
    //ou une condition non satisfaite.
    FAIL ("fail"),

    //Une erreur est apparue.
    ERROR ("error");

    private final String name;

    //Constructeur
    StatusJSEND(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
