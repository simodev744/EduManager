package Dao;

import Dbutlis.ConnectionDb;
import Models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;

    public CourseDAO() throws SQLException, ClassNotFoundException {
        connection= ConnectionDb.getconnectiondb();
    }



    public void save(Course course) {
        String query = "INSERT INTO course (title, description) VALUES (?, ?)";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(Course course) {
        String query = "UPDATE course SET description = ?,title=? WHERE id = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, course.getDescription());
                preparedStatement.setString(2, course.getTitle());
                preparedStatement.setInt(3,course.getId());
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM course";

        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Course course = new Course(id,title, description);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }


    public Course findById(int id) {
        String query = "SELECT * FROM course WHERE id = ?";
        Course course = null;

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String description = resultSet.getString("description");
                String title = resultSet.getString("title");
                course = new Course(title, description);
                course.setId(id);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }


    public void delete(int id) {
        String query = "DELETE FROM course WHERE id = ?";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
