/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aluno
 */
public class LivroBD {

    /**
     * @param args the command line arguments
     */
    Connection connection = null;
    Statement statement = null;

    public Connection conectarBD() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean criarTabela() {
        try {
            statement.executeUpdate("drop table if exists livro");
            statement.executeUpdate("create table livro (id integer, titulo string, autor string)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void create(Livros l) {

        try {
            String sqlString = "insert into livro values("
                    + l.getId()
                    + ","
                    + "'" + l.getTitulo() + "'" + ","
                    + "'" + l.getAutor() + "'" + ")";
            statement.executeUpdate(sqlString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Livros searchById(int id) {
        Livros l = null;
        try {
            ResultSet rs = statement.executeQuery("select * from livro where id=" + id);
            while (rs.next()) {
                l = new Livros();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public boolean delete(Livros l) {
        try {
            String sqlString = "delete from livro where id=" + l.getId();
            statement.executeUpdate(sqlString);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean update(Livros l) {
        if (searchById(l.getId()) == null) {
            return false;
        }
        try {
            String sqlString = "update livro set titulo=? and autor=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sqlString);
            ps.setInt(3, l.getId());
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.execute();
            //statement.executeUpdate(sqlString);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }      
    
    public String listarTudo() {
        String resultados = "";
        try {
            ResultSet rs = statement.executeQuery("select * from livro");
            while (rs.next()) {
                // read the result set
                resultados += "" + rs.getInt("id") + "," + rs.getString("titulo") + rs.getString("autor") + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }
}

