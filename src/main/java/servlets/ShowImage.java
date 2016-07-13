package servlets;
import models.Book;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * Created by Merei on 12.07.2016.
 */
@javax.servlet.annotation.WebServlet(name = "showImage", urlPatterns = "/showImage", asyncSupported = true)
public class ShowImage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");

        OutputStream out = response.getOutputStream();

        try {
            int index = Integer.valueOf(request.getParameter("index"));

            ArrayList<Book> list = (ArrayList<Book>) request.getSession(false).getAttribute("currentBooks");

            Book book = list.get(index);

            response.setContentLength(book.getImage().length);
            out.write(book.getImage());
        }finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
