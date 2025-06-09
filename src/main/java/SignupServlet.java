

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 * SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String preferredName = request.getParameter("preferredName");
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    Signup sign = new Signup();
	    sign.setName(name);
	    sign.setPreferredName(preferredName);
	    sign.setUsername(username);
	    sign.setEmail(email);
	    sign.setPassword(password);

	    System.out.println("User Input:");
	    System.out.println("Name: " + name);
	    System.out.println("Preferred Name: " + preferredName);
	    System.out.println("Username: " + username);
	    System.out.println("Email: " + email);
	    System.out.println("Password: " + password);

	    String jdbcURL = "jdbc:mysql://localhost:3306/assignment_prabhdip";
	    String dbUser = "root";
	    String dbPass = "password";

	    String sql = "INSERT INTO Signup (userName, userEmail, pass, preferredName, fullName) VALUES (?, ?, ?, ?, ?)";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
	        PreparedStatement ps = connection.prepareStatement(sql);

	        ps.setString(1, username);
	        ps.setString(2, email);
	        ps.setString(3, password);
	        ps.setString(4, preferredName);
	        ps.setString(5, name); // full name

	        ps.executeUpdate();
	        ps.close();
	        connection.close();

	        System.out.println("Data inserted into database.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.sendRedirect("success.html");

	}
}

