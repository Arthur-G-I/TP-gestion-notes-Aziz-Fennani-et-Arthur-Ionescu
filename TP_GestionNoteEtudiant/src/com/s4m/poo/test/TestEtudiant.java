package com.s4m.poo.test;
import com.s4m.poo.basics.Etudiant;
import com.s4m.poo.basics.Notation;


import com.s4m.poo.basics.*;

public class TestEtudiant {

    public static void main(String[] args) {

        Etudiant e = new Etudiant("Fennani", "Mohamed Aziz");
        Etudiant e1 = new Etudiant("Ionescu", "Arthur");
        Etudiant e2 = new Etudiant("Cooper", "Emily");


        e2.sauvegarder();

        e2.ajouterNote(new Notation("Maths", 7, 2));
        e2.ajouterNote(new Notation("Anglais", 7, 1));
        e2.ajouterNote(new Notation("Informatique", 10, 3));

        e2.calculerMoyenne();

        e2.afficher();
    }
}
