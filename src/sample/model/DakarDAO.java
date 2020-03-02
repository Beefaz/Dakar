package sample.model;

import java.sql.*;
import java.util.ArrayList;

public class DakarDAO {
    public static void insert(Dakar dakar) {
        String query = "INSERT INTO komandos (teamName, nameSurname, sponsors, racingCars, members) VALUES (?,?,?,?,?)";
        String url = "jdbc:mysql://localhost:3306/dakaras?serverTimezone=UTC";

        try {
            Connection prisijungimas = DriverManager.getConnection(url, "root", "");
            PreparedStatement uzklausa = prisijungimas.prepareStatement(query);
            uzklausa.setString(1, dakar.getTeamName());
            uzklausa.setString(2, dakar.getNameSurname());
            uzklausa.setString(3, dakar.getSponsors());
            uzklausa.setString(4, dakar.getRacingCars());
            uzklausa.setInt(5, dakar.getMembers());
            uzklausa.executeUpdate();
            uzklausa.close();


            System.out.println("Sukurtas naujas įrašas");
        } catch (SQLException e) {
            System.out.println("Batai. Nepavyko :)");
            e.printStackTrace();
        }
    }
}
