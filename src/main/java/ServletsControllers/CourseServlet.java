package Servlet;

import Dao.CoursDao;
import Models.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {

    private CoursDao courseDao;

    @Override
    public void init() throws ServletException {
        try {
            courseDao = new CoursDao();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                courseDao.deleteCourse(id);
                response.sendRedirect("CourseServlet");
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Course course = courseDao.getCourse(id);
                request.setAttribute("course", course);
                request.getRequestDispatcher("edit.jsp").forward(request, response);
            } else {
                // Par défaut, liste des cours
                List<Course> courses = courseDao.getCourses();
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String instructor = request.getParameter("instructor");
                Course course = new Course();
                course.setTitle(title);
                course.setDescription(description);
                courseDao.ajouterCourse(course);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                Course course = new Course(id, title, description);
                courseDao.modifierCourse(course);
            }
            response.sendRedirect("CourseServlet");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
