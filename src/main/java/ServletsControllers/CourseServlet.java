package ServletsControllers;

import Dao.CoursDao;
import Models.Course;
import Models.Etudiant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    private  CoursDao coursDao;

    @Override
    public void init() throws ServletException {
        try {
            coursDao=new CoursDao();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action){
            case "ajouter": ajouter(request,response);
                break;
                case "modifier":modifier(request,response);
                    break;
                    case "supprimer":
                        try {
                            supprimer(request,response);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
            default:
                try {
                    afficher(request,response);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    private void afficher(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        List<Course> course = coursDao.getcourses();
        request.setAttribute("course", course);
        request.getRequestDispatcher("/course/ListCours.jsp");
    }

    private void supprimer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        coursDao.supprimerCours("course");
    }

    private void modifier(HttpServletRequest request, HttpServletResponse response) {
    }

    private void ajouter(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
