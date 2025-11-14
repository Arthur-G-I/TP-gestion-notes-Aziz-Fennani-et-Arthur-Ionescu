package com.s4m.poo.basics;


public class Notation {

    private double note;
    private int coef;
    private String matiere;

    public Notation(String matiere, double note, int coef) {
        this.matiere = matiere;
        this.note = note;
        this.coef = coef;
    }

    public double getNote() { return note; }
    public int getCoef() { return coef; }
    public String getMatiere() { return matiere; }

    public void afficher() {
        System.out.println(matiere + " : " + note + " (coef " + coef + ")");
    }
}
