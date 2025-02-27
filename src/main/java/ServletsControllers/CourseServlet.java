package ServletsControllers;

import Dao.CourseDAO;
import Models.Course;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    private CourseDAO courseDAO = new CourseDAO();

    public CourseServlet() throws SQLException, ClassNotFoundException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            request.setAttribute("courses", courseDAO.findAll());
            System.out.println(courseDAO.findAll());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/course/index.jsp");
            dispatcher.forward(request, response);
        }
        else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Course course = courseDAO.findById(id);
            if (course != null) {
                request.setAttribute("course", course);
                                }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/course/edit.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("add")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/course/add.jsp");
            dispatcher.forward(request, response);
        }
        else if (action.equals("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));
            courseDAO.delete(id);

            response.sendRedirect("courses");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("save")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            Course course = new Course(title, description);
            course.setId(id);
            courseDAO.save(course);
            response.sendRedirect("courses");

        } else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            Course course = new Course(id,title, description);
            courseDAO.update(course);
            response.sendRedirect("courses");
        }
    }
}
