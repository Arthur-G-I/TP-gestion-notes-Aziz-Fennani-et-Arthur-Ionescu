//import model.Etudiant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String username = "root", password = "";
        String url = "jdbc:mysql://localhost:3306/etudiants";
        try{
            Connection con = DriverManager.getConnection(url, username,
                    password);
            //selection
            var data = con.prepareStatement(" SELECT * FROM etudiants").executeQuery();
            while (data.next()) {
                String name = data.getString("name");
                Float moyenne = data.getFloat("moyenne");
                String avis = data.getString("avis");
                System.out.println(String.format("Nom: %s, Moyenne: %s, Avis %s", name, moyenne, avis));
            }
 /*
 //Insertion
 var stmt = con.prepareStatement("insert into etudiants (name, 
moyenne, avis) VALUES (?, ?, ?)");
 stmt.setString(1, "Kemuel");
 stmt.setFloat(2, 12);
 stmt.setString(3, "Passe en classe supérieure");
 stmt.executeUpdate();
 */
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
    }
}