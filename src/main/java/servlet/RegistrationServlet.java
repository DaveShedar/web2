package servlet;

import model.User;
import service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Registration servlet, append email and password");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("Registration servlet, append email and password equal 0");
            return;
        }

        System.out.println("Registration servlet, add new User");
        User user = new User(UserService.getUserService().getNewId(), email, password);
        System.out.println(UserService.getUserService().addUser(user));
        System.out.println("Registration servlet, add new User" + ". Current id is " + UserService.getUserService().getCurrentId());
        System.out.println(user.getEmail() + " : " +  user.getPassword() + " : " + user.getId());
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
