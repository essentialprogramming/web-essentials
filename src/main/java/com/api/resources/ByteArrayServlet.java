package com.api.resources;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;


@WebServlet("")
public class ByteArrayServlet extends HttpServlet {

    @Inject
    private ServletContext context;



    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            byte[] byteArray = buffer.toByteArray();

            // no, don't do this, it returns the address of the object in memory
            System.out.println("Text [Byte Format] : " + byteArray.toString());

            // convert bytes[] to string
            String stringValue = new String(byteArray, StandardCharsets.UTF_8);
            System.out.println("Output : " + stringValue);

        } finally {
            if(inputStream!=null)
                inputStream.close();
        }
    }

    private void sendErroMEssage(PrintWriter out){
        out.print("Please tell us where do you want to go");
    }
}
