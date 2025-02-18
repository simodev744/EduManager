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


@WebServlet("/students")
public class StudentServlets extends HttpServlet {

private EtudiantsDao etudiantsDao;

    @Override
    public void init() throws ServletException {
        try {
            etudiantsDao=new EtudiantsDao();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action=req.getParameter("action");

        switch (action){
            case "add": ajouter(req,resp);
                break;
            case "update": modifier(req,resp);
                break;
            case "delete": supprimer(req,resp);
                break;
                default: afficher(req,resp);
        }
    }

    private void afficher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        etudiantsDao.getStudients();
        req.getRequestDispatcher("/student/studentList.jsp").forward(req,resp);
    }

    private void supprimer(HttpServletRequest req, HttpServletResponse resp) {

        int id= Integer.parseInt(req.getParameter("id"));
        etudiantsDao.deleteEtudiant(id);


    }

    private void modifier(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student/ModifierEtudiant.jsp").forward(req,resp);
    }

    private void ajouter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/student/ajouterEtudiant.jsp").forward(req,resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String prenom=req.getParameter("prenom");
        String dateNaissance=req.getParameter("datenaissance");
        String param=req.getParameter("page");

        Etudiant etudiant=new Etudiant(name,prenom,email,dateNaissance);



       if(param.contains("ajouter")){

           try {
               etudiantsDao.ajouterEtudiant(etudiant);
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }

           resp.sendRedirect("students?action=list");
       }

       if(param.contains("modifier")){

           etudiantsDao.modifierEtudiant(etudiant);

           resp.sendRedirect("students?action=list");


        }

    }
}
