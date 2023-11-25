import java.sql.*;

public class DAO {

    public static Connection connection;
    public PreparedStatement createStatement;
    public ResultSet resultSet;

    // Método para establecer la conexión a la base de datos
    public void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:hsqldb:./Paises");
    }

    // Método para crear la tabla de países en la base de datos
    public void createTable() throws SQLException {
        connection = DriverManager.getConnection("jdbc:hsqldb:./Paises");
        // Comentado para evitar eliminar la tabla sino esta creada ya que sino haria Bum
        // createStatement = connection.prepareStatement("DROP TABLE PAISES");
        // createStatement.execute();
        createStatement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS PAISES(REGION VARCHAR(35), NOMBRE_PAIS VARCHAR(15) PRIMARY KEY, PRESIDENTE VARCHAR(40), PIB INTEGER, GINI DOUBLE)");
        createStatement.execute();
    }

    // Método para buscar un país por nombre en la base de datos
    public void buscar(String q1) throws SQLException, SQLSyntaxErrorException {
        connection = DriverManager.getConnection("jdbc:hsqldb:./Paises");
        createStatement = connection.prepareStatement("SELECT * FROM PAISES WHERE NOMBRE_PAIS =?");
        createStatement.setString(1, q1);

        resultSet = createStatement.executeQuery();

        while (resultSet.next()) {
            System.out.print("Pais: " + resultSet.getString("NOMBRE_PAIS") + " - ");
            System.out.print("Presidente: " + resultSet.getString("PRESIDENTE") + " - ");
            System.out.print("PIB: " + resultSet.getInt("PIB") + " - ");
            System.out.print("GINI: " + resultSet.getInt("GINI"));
            System.out.println();
        }
    }

    // Método para insertar un país en la base de datos
    public void insertar(Pais_serializable a) throws SQLException, SQLSyntaxErrorException {
        connection = DriverManager.getConnection("jdbc:hsqldb:./Paises");
        createStatement = connection.prepareStatement("INSERT INTO PAISES (NOMBRE_PAIS, PRESIDENTE, PIB, GINI) VALUES (?, ?, ?, ?)");
        createStatement.setString(1, a.getNombre_pais());
        createStatement.setString(2, a.getNombre_presi());
        createStatement.setString(3, a.getPib());
        createStatement.setString(4, a.getGini());

        createStatement.execute();
    }

    // Método para modificar el PIB de todos los países a un valor específico
    public void modificarPIB(int contador) throws SQLException {
        connection = DriverManager.getConnection("jdbc:hsqldb:./Paises");
        createStatement = connection.prepareStatement("UPDATE PAISES SET PIB =?");
        createStatement.setInt(1, contador);
        createStatement.executeUpdate();
    }

    // Método para reducir el coeficiente de Gini de un país específico
    public void reducirGini(String pais) throws SQLException {
        connection = DriverManager.getConnection("jdbc:hsqldb:./Paises");
        createStatement = connection.prepareStatement("UPDATE PAISES SET GINI = GINI * 2 / 3 WHERE NOMBRE_PAIS = ?");
        createStatement.setString(1, pais);
        createStatement.executeUpdate();
    }
}
