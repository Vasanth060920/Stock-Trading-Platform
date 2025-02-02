import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ContactServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Email sending (using JavaMail - requires setup)
        final String to = "your_email@example.com"; // Replace with your email
        final String from = "website_contact@yourdomain.com"; // Replace with a valid from address
        final String subject = "Contact Form Submission";
        final String host = "your_smtp_server.com"; // Replace with your SMTP server

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        // ... (Add other mail properties like authentication if needed) ...

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText("Name: " + name + "\nEmail: " + email + "\nMessage: " + message);

            Transport.send(mimeMessage);
            response.sendRedirect("thankyou.html"); // Redirect to a thank you page

        } catch (MessagingException mex) {
            mex.printStackTrace(); // Handle errors
            response.sendRedirect("error.html"); // Redirect to an error page
        }
    }
}
