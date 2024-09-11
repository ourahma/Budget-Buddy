package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilitaire {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            // chargement du pilote JDBC MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // etablissement de la connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/moneymanager";
            String utilisateur = "root";
            String motDePasse = "";
            connection = DriverManager.getConnection(url, utilisateur, motDePasse);
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du pilote JDBC : " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'établissement de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
    
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Gestion de l'erreur de fermeture de la connexion
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
}
}