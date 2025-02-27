package ServletsControllers;


import Dao.CourseDAO;
import Dao.EnrollementDAO;
import Dao.EtudiantsDao;
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

@WebServlet("/enrollement")
public class EnrollementServlet extends HttpServlet {

    private  EnrollementDAO dao = new EnrollementDAO();
    private  CourseDAO courseDAO= new CourseDAO();
    private EtudiantsDao etudiantsDao = new EtudiantsDao();


    public EnrollementServlet() throws SQLException, ClassNotFoundException {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action= req.getParameter("action");

        if(action.equals("coursId")){
            req.getRequestDispatcher("/cours-students/coursDetails.jsp").forward(req, resp);
        }

        else if (action.equals("enroll"))
        {
            req.getRequestDispatcher("/cours-students/enroll.jsp").forward(req, resp);
        } else if (action.equals("unenroll")) {
            req.getRequestDispatcher("/cours-students/unenroll.jsp").forward(req, resp);
        }


        afficherDetailsCours(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws  IOException {

            String action = req.getParameter("action");
            int coursId = Integer.parseInt(req.getParameter("coursId"));
            int etudiantId = Integer.parseInt(req.getParameter("etudiantId"));

            if ("enroll".equalsIgnoreCase(action)) {
                dao.enrollEtudiant(etudiantId, coursId);
            } else if ("unenroll".equalsIgnoreCase(action)) {
                dao.unenrollEtudiant(etudiantId, coursId);
            }
        resp.sendRedirect("enrollement?coursId=" + coursId);
    }

    private void afficherDetailsCours(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

            String coursIdParam = req.getParameter("coursId");
            if (coursIdParam != null) {
                int coursId = Integer.parseInt(coursIdParam);
                Course course=courseDAO.findById(coursId);
                List<Etudiant> etudiants = dao.getEtudiantsByCoursId(coursId);
                req.setAttribute("etudiants", etudiants);
                req.setAttribute("course",course);
                }
            req.getRequestDispatcher("/cours-students/coursDetails.jsp").forward(req, resp);
    }
}
