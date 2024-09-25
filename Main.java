package conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progra", "root", "76901613986");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el genero de la pelicula: ");
            String genero = scanner.nextLine();
            System.out.println("Ingrese el nombre de la pelicula: ");
            String nombre = scanner.nextLine();
            Pelicula pelicula = new Pelicula();
            pelicula.setGenero(genero);
            pelicula.setNombre(nombre);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO biblioteca (genero, nombre) VALUES (?, ?)");
            statement.setString(1, pelicula.getGenero());
            statement.setString(2, pelicula.getNombre());
            statement.executeUpdate();

            statement = connection.prepareStatement("SELECT * FROM biblioteca");
            ResultSet result = statement.executeQuery();

            List<Pelicula> peliculas = new ArrayList<>();

            while (result.next()) {
                peliculas.add(new Pelicula(result.getString("genero"), result.getString("nombre")));
            }

            System.out.println();
            System.out.println("Peliculas en la biblioteca: ");
            for (Pelicula l : peliculas) {
                System.out.println(l.getPelicula() + " - " + l.getNombre());
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}