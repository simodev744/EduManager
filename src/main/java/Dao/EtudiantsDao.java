package Dao;

import Dbutlis.ConnectionDb;
import Models.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtudiantsDao {



       private Connection conn;

      public EtudiantsDao() throws SQLException, ClassNotFoundException {
         conn= ConnectionDb.getconnectiondb();
      }


    public void ajouterEtudiant(Etudiant etudiant) throws SQLException {
        String sql="insert into etudiant values(?,?,?,?)";
        PreparedStatement statement=conn.prepareStatement(sql);

        statement.setString(1,etudiant.getNom());
        statement.setString(2,etudiant.getPrenom());
        statement.setString(3,etudiant.getEmail());
        statement.setString(4, etudiant.getDatenaissance());

        statement.executeUpdate();

    }

    public void modifierEtudiant(Etudiant etudiant) {

          String sql="SELECT * FROM etudiant WHERE id=?";


    }


    public void deleteEtudiant(int id) {

    }

    public List<Etudiant> getStudients() {
         List <Etudiant> etudiants = new ArrayList();


         return etudiants;
    }

    public Etudiant getStudient(int id) {

        Etudiant etudiant=new Etudiant("name","dfe","test","test");

        return etudiant;

    }
}
