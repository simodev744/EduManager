package Dao;

import Dbutlis.ConnectionDb;
import Models.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantsDao {

     private Connection conn;

      public EtudiantsDao() throws SQLException, ClassNotFoundException {
         conn= ConnectionDb.getconnectiondb();
      }


    public void ajouterEtudiant(Etudiant etudiant) throws SQLException {
        String sql="INSERT INTO student(name,prenom,email,datenaissance) VALUES(?,?,?,?)";
        PreparedStatement statement=conn.prepareStatement(sql);

        statement.setString(1,etudiant.getNom());
        statement.setString(2,etudiant.getPrenom());
        statement.setString(3,etudiant.getEmail());
        statement.setString(4, etudiant.getDatenaissance());

        statement.executeUpdate();

    }

    public void modifierEtudiant(Etudiant etudiant) throws SQLException {

        String query = "UPDATE student SET name = ?, prenom = ?, email = ?, datenaissance = ? WHERE id = ?";
        PreparedStatement statement=conn.prepareStatement(query);

            statement.setString(1,etudiant.getNom());
            statement.setString(2,etudiant.getPrenom());
            statement.setString(3,etudiant.getEmail());
            statement.setString(4,etudiant.getDatenaissance());
            statement.setInt(5,etudiant.getId());
            statement.executeUpdate();


    }

    public void deleteEtudiant(int id) throws SQLException {
        String sql="DELETE FROM student WHERE id=?";
        PreparedStatement statement=conn.prepareStatement(sql);
        statement.setInt(1,id);
        statement.executeUpdate();
    }

    public List<Etudiant> getStudients() throws SQLException {
         List <Etudiant> etudiants = new ArrayList();
         Etudiant etudiant=null;
         String sql="SELECT * FROM student";
         PreparedStatement statement = conn.prepareStatement(sql);
         statement.executeQuery();

         ResultSet resultSet=statement.getResultSet();

         while(resultSet.next()){

             etudiant=new Etudiant(
                     resultSet.getInt("id"),
                     resultSet.getString("name"),
                     resultSet.getString("prenom"),
                     resultSet.getString("email"),
                     resultSet.getString("datenaissance"));

             etudiants.add(etudiant);
         }

         return etudiants;
    }


    public Etudiant getStudient(int id) throws SQLException {
          String sql="SELECT * FROM student WHERE id=?";
          PreparedStatement statement=conn.prepareStatement(sql);
           statement.setInt(1,id);
           ResultSet resultSet=statement.executeQuery();
           Etudiant etudiant=null;
           if(resultSet.next()){
                etudiant=new Etudiant(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("datenaissance"));
           }


        return etudiant;

    }
}
