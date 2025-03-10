package ServletsControllers;

import Dao.EtudiantsDao;
import Models.Etudiant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/etudiants")
public class StudentServlets extends HttpServlet {

    private EtudiantsDao etudiantsDao;

    @Override
    public void init() throws ServletException {
        try {
            etudiantsDao = new EtudiantsDao();
            }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            try {
                afficher(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        switch (action) {
            case "add":
                ajouter(req, resp);
                break;
            case "update":
                try {
                    modifier(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    supprimer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;
            default:
                try {
                    afficher(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        }
    }


    private void afficher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        List<Etudiant> etudiants = etudiantsDao.getStudients();
        req.setAttribute("etudiants", etudiants);
        req.getRequestDispatcher("/student/studentList.jsp").forward(req, resp);
    }


    private void supprimer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        etudiantsDao.deleteEtudiant(id);

        resp.sendRedirect("etudiants");


    }

    private void modifier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        int id = Integer.parseInt(req.getParameter("id"));
        Etudiant etudiant = etudiantsDao.getStudient(id);
        req.setAttribute("etudiant", etudiant);
        req.getRequestDispatcher("/student/ModifierEtudiant.jsp").forward(req, resp);
    }

    private void ajouter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/student/ajouterEtudiant.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String prenom = req.getParameter("prenom");
            String dateNaissance = req.getParameter("datenaissance");
            String param = req.getParameter("page");
            Etudiant etudiant = new Etudiant(name, prenom, email, dateNaissance);


            if (param.contains("ajouter")) {

                try {
                    etudiantsDao.ajouterEtudiant(etudiant);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("etudiants");
            }
            if (param.contains("modifier")) {
                System.out.println(etudiant);

                try {
                    int id=Integer.parseInt(req.getParameter("id"));
                     etudiant.setId(id);
                     etudiantsDao.modifierEtudiant(etudiant);
                    } catch (SQLException e) {
                    throw new RuntimeException(e);
                    }
                resp.sendRedirect("etudiants");

            }

        }
    }

