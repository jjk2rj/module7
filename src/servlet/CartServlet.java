package servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.Course;
import business.User;
import data.UserIO;

//import edu.jhu.sessions.business.Cart;
//import edu.jhu.sessions.business.LineItem;
//import edu.jhu.sessions.business.Product;
//import edu.jhu.sessions.data.ProductIO;

/**
 * Servlet implementation class CartServlet
 *
 */
@WebServlet("/CartServlet")
 public class CartServlet extends HttpServlet {
    private static String[] coursenames = {"A0 - Web Services" , 
                                                "A1 -J2EE Design Patterns",
                                                "A2 - Service Oriented Architecture",
                                                "A3 - Enterprise Service Bus",
                                                "A4 - Secure Messaging",
                                                "A5 - Web Services Security"};
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.html";
        ServletContext sc = getServletContext();
        
        // get a connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://jhu-web-app.ciyge4nmdyzs.us-east-1.rds.amazonaws.com:3306/jhu_web_app";
            String username = "root";
            String password = "opensesame"; 
            
            Connection connection = DriverManager.getConnection(
                    dbURL, username, password);
            
            System.out.println("Connected");
            
            connection.close();
        } catch ( SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        
  
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";  // default action
        }

        // perform action and set URL to appropriate page
        if (action.equals("shop")) {
            url = "/index.html";    // the "index" page
        } 
        else if (action.equals("compute")) 
        {
            HttpSession session = request.getSession();
            System.out.println("compute");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String employmentStatus = request.getParameter("employmentStatus");

            String[] courses = request.getParameterValues("courses");
            ArrayList<Course> coursesList = new ArrayList<>();
            for(String courseName : coursenames) {
                Course newCourse = new Course();
                newCourse.name = courseName;
                for(String c : courses) {
                    if(c.equals(courseName)) {
                        newCourse.isSelected = true;
                        break;
                    }
                }
                coursesList.add(newCourse);
            }
            String[] extras = request.getParameterValues("extras");

            User user = new User(name, email, coursesList, employmentStatus, extras);  
            
            url = "/cart.jsp";
            
            session.setAttribute("user", user);
        }
        else if(action.equals("edit") || action.equals("add")){
            url = "/editCart.jsp";
            // add code to edit load cart from session
        }
        else if(action.equals("confirm")){
            HttpSession session = request.getSession();
            System.out.println("compute");

            User user = (User) session.getAttribute("user");  
            
            url = "/cart.jsp";
            
            session.setAttribute("user", user);
            String path = sc.getRealPath("/WEB-INF/EmailList.txt");
            UserIO.add(user, path);
        }
        else if(action.equals("remove")) {
            HttpSession session = request.getSession();
            
            String courseToBeRemoved = request.getParameter("course");
            System.out.println(courseToBeRemoved);
            User user = (User) session.getAttribute("user");  
            user.removeCourse(courseToBeRemoved);
            session.setAttribute("user", user);
//          request.getAttribute()
            url = "/cart.jsp";
            
        }
        sc.getRequestDispatcher(url)
                .forward(request, response);
    }
    
}