package Dao;

import Dbutlis.ConnectionDb;
import Models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDao {
    private Connection connction;

    public CoursDao() throws SQLException, ClassNotFoundException {
        connction = ConnectionDb.getconnectiondb();
    }

    public void ajouterCours(Course course) throws SQLException, ClassNotFoundException {
        String sql = "insert into Course (title,description) values(?,?)";
        PreparedStatement ps = connction.prepareStatement(sql);
        ps.setString(1, course.getName());
        ps.setString(2, course.getDescription());
        ps.executeUpdate();

    }

    public void modifierCours(Course course) throws SQLException, ClassNotFoundException {
        String sql = "Update course set (title,description)=? where id = ?";
        PreparedStatement ps = connction.prepareStatement(sql);
        ps.setString(1, course.getName());
        ps.setString(2, course.getDescription());
        ps.executeUpdate();
    }

    public void supprimerCours(int Id) throws SQLException, ClassNotFoundException {
        String sql = "delete Course where id = ?";
        PreparedStatement ps = connction.prepareStatement(sql);
        ps.setInt(1, Id);
        ps.executeUpdate();

    }

    public List<Course> getcourses() throws SQLException, ClassNotFoundException {
        List<Course> courses = new ArrayList();
        Course course = null;

        String sql = "select * from course";
        Statement ps = connction.createStatement();
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
              course = new Course(

                      rs.getString("title"),
                      rs.getString("description")
              );

              courses.add(course);
        }
        return courses;
    }
    public Course getCourse(int id) throws SQLException, ClassNotFoundException {
        Course course = null;
        String sql = "select * from course where id = ?";
        PreparedStatement ps = connction.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            course = new Course(

                    rs.getString("title"),
                    rs.getString("description")
            );
        }
        return course;
    }
}
