import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mail.Sendmail;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Getcoviddata")
public class Getcoviddata extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
    {
      response.setContentType("text/html");
      PrintWriter out=response.getWriter();
      String data = request.getParameter("covid");
      Sendmail mail = new Sendmail();
//        System.out.println(data);
      if(mail.sendmsg(data)){
          out.write("SUCCESS");
      }
      else{
          out.write("FAILED");
      }
      out.close();
    }
}