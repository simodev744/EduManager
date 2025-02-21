

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


    // Save a new course
    public void save(Course course) {
        String query = "INSERT INTO courses (title, description) VALUES (?, ?)";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing course
    public void update(Course course) {
        String query = "UPDATE courses SET description = ? WHERE title = ?";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, course.getDescription());
            preparedStatement.setString(2, course.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find all courses
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";

        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Course course = new Course(title, description);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    // Find a course by title
    public Course findByTitle(String title) {
        String query = "SELECT * FROM courses WHERE title = ?";
        Course course = null;

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String description = resultSet.getString("description");
                course = new Course(title, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    // Delete a course by title
    public void delete(String title) {
        String query = "DELETE FROM courses WHERE title = ?";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
