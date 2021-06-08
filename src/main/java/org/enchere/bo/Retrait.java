package org.enchere.bo;

public class Retrait {
    private int id;
    private String rue;
    private String code_postal;
    private String ville;

    //Constructor


    public Retrait() {
        super();
    }

    public Retrait(int id, String rue, String code_postal, String ville) {
        super();
        this.id = id;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    //Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", code_postal=" + code_postal +
                ", ville='" + ville + '\'' +
                '}';
    }
}
