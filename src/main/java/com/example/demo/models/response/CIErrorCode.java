package com.example.demo.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Objet regroupant les divers errors code possibles.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CIErrorCode {

    //Page non trouvée.
    USERNOTFOUND ("001404");

    private final String name;

    //Constructeur
    CIErrorCode(String name){
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
