package com.twic_client.Beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VilleModel {
    private String codeINSEE;
    private String commune;
    private String codePostal;
    private String libelleAcheminement;
    private String ligne5;
    private String longitude;
    private String latitude;

    public VilleModel() {
    }

    public String toString() {
        return codeINSEE+" "+commune+" "+codePostal+" "+libelleAcheminement+" "+ligne5+" "+latitude+" "+longitude;
    }

    public VilleModel(String codeINSEE, String commune, String codePostal, String libelleAcheminement, String ligne5,
                      String longitude, String latitude) {
        super();
        this.codeINSEE = codeINSEE;
        this.commune = commune;
        this.codePostal = codePostal;
        this.libelleAcheminement = libelleAcheminement;
        this.ligne5 = ligne5;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
