package com.s4m.poo.basics;

public class GestionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_etudiant";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // XAMPP : vide

    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
