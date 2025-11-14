package com.s4m.poo.basics;

import java.sql.*;
import java.util.ArrayList;

public class Etudiant {

    private int id;
    private String nom;
    private String prenom;
    private double moyenne;
    private String avis;    // ✔ you forgot this!

    // Tableau interne de notes
    private ArrayList<Notation> notes = new ArrayList<>();

    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // ---------------------- BD ----------------------

    public void sauvegarder() {
        try {
            Connection c = GestionBD.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO etudiants(nom, prenom, moyenne, avis) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setDouble(3, 0);
            ps.setString(4, ""); // avis vide au début

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);

        } catch (Exception e) { e.printStackTrace(); }
    }

    // ---------------------- NOTES ----------------------

    public void ajouterNote(Notation n) {
        notes.add(n);

        try {
            Connection c = GestionBD.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO notations(etudiant_id, matiere, note, coef) VALUES (?, ?, ?, ?)"
            );

            ps.setInt(1, id);
            ps.setString(2, n.getMatiere());   // ✔ matière ajoutée
            ps.setDouble(3, n.getNote());
            ps.setInt(4, n.getCoef());

            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }


    // ---------------------- MOYENNE ----------------------

    public void calculerMoyenne() {
        double somme = 0;
        int coefTotal = 0;

        for (Notation n : notes) {
            somme += n.getNote() * n.getCoef();
            coefTotal += n.getCoef();
        }

        moyenne = somme / coefTotal;

        // ✔ round to 2 decimals
        moyenne = Math.round(moyenne * 100.0) / 100.0;

        // update in BD
        try {
            Connection c = GestionBD.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE etudiants SET moyenne = ? WHERE id = ?"
            );

            ps.setDouble(1, moyenne);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }

        // ✔ automatically compute avis
        calculerAvis();
    }

    // ---------------------- AVIS ----------------------

    public void calculerAvis() {
        if (moyenne >= 10)
            avis = "Admis";
        else
            avis = "Refusé";

        try {
            Connection c = GestionBD.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE etudiants SET avis = ? WHERE id = ?"
            );

            ps.setString(1, avis);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    // ---------------------- AFFICHAGE ----------------------

    public void afficher() {
        System.out.println("Étudiant : " + nom + " " + prenom);
        System.out.println("Moyenne : " + moyenne);
        System.out.println("Avis : " + avis); // ✔ you forgot this
        System.out.println("Notes : ");
        for (Notation n : notes) {
            n.afficher();
        }
    }
}
