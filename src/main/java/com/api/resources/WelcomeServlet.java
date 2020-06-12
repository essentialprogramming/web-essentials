package com.api.resources;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("")
public class WelcomeServlet extends HttpServlet {

    @Inject
    private ServletContext context;



    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String pathInfo = request.getPathInfo(); // /{value}/test

        if (pathInfo == null) {
            sendErroMEssage(response.getWriter());
        } else{

            String[] pathParts = pathInfo.split("/");
            String location = pathParts[1]; // {value}
            response.setContentType("text/html");//setting the content type
            PrintWriter out = response.getWriter();//get the stream to write the data

            //writing html in the stream
            out.println("<html><body>");
            out.println("<h1>Welcome to " + location + ", " + name + "! </h1>");
            out.println("</body></html>");

            out.close();//closing the stream
        }


    }

    private void sendErroMEssage(PrintWriter out){
        out.print("Please tell us where do you want to go");
    }
}
