package servlet;

import com.google.gson.Gson;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
//    public LoginServlet(UserService userService) {
//    }

//    private final UserService userService;
//
//    public LoginServlet(UserService userService) {
//        this.userService = userService;
//    }

//    @Override
//    public void doGet(HttpServletRequest request,
//                      HttpServletResponse response) throws ServletException, IOException {
//        User user = UserService.getUserService().getUserById(UserService.getUserService().maxId.get());
//        if (user == null) {
//            System.out.println("doGet Loginservlet, user == null. UNAUTH");
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        } else {
//            System.out.println("Loginservlet, user == null. AUTH");
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        System.out.println("doPost Loginservlet, input email and password");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email + " : " + password);

        if (email == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("LoginServlet, email and password equals 0");
            return;
        }

        User user = new User(email, password);

        if(UserService.getUserService().isExistsThisUser(user)){
            response.setContentType("text/html;charset=utf-8");
            System.out.println("LoginServlet, put in authMap. AUTH");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        response.setContentType("text/html;charset=utf-8");
        System.out.println("LoginServlet, check in dataBase. UNAUTH");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
