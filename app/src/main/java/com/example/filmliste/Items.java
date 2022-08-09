package com.example.filmliste;

public class Items {


    private int image;
    private int id;
    private String titre;
    private String description;
    private int duree;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Items(int id, String titre, String description, int duree, int image) {
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.duree=duree;
        this.id=id;
    }

    public int getImage() {
        return (int) image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

}
