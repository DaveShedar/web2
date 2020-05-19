import model.User;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import service.UserService;
import servlet.ApiServlet;
import servlet.LoginServlet;
import servlet.RegistrationServlet;

import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws Exception{

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ApiServlet()), "/api/*");
        context.addServlet(new ServletHolder(new LoginServlet(new UserService())), "/login");
        context.addServlet(new ServletHolder(new RegistrationServlet(new UserService())), "/register");


        ResourceHandler resource_handler = new ResourceHandler();
        HandlerList handlers = new HandlerList();
        resource_handler.setResourceBase("templates");
        resource_handler.setWelcomeFiles(new String[]{"registerPage.html"});

        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();

    }
}
