package com.cognixia.jump;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn;
	private PreparedStatement pstmt;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.conn = ConnectionManager.getConnection();
		
		try {
			this.pstmt = this.conn.prepareStatement("select * from film_list where rating = ? and price = ? limit ?");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			this.pstmt.close();
			this.conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> ratings = Arrays.asList(request.getParameterValues("rating"));
		double rate = Double.parseDouble(request.getParameter("rental-rate"));
		int size = Integer.parseInt(request.getParameter("result-size"));
		boolean retrieved = false;
		List<Film> films = new ArrayList<Film>();	
		
		if(!ratings.isEmpty()) {
			for(String rating: ratings) {
				try {
					this.pstmt.setString(1, rating);
					this.pstmt.setDouble(2, rate);
					this.pstmt.setInt(3, size);

					ResultSet rs = this.pstmt.executeQuery();

					while(rs.next()) {
						films.add(new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getString(8)));		
						retrieved = true;
					}

					rs.close();

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		pw.println("<html>");
		
		pw.println("<head>"
				+ "<title>FilmsFiltered</title>"
				+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"
				+ "<style>"
				+ "body {" 
				+ "background-color: black;" 
				+ "color: white;}"
				+ ".btn {" 
				+ "background-color: none;" 
				+ "border: 2px solid crimson;" 
				+ "color: white;}" 
				+ "div.container {" 
				+ "background-color: black;" 
				+ "color: white;}"
				+ "div.container-fluid {" 
				+ "background-color: black;" 
				+ "color: white;}"
				+ "table {  border-collapse: collapse; table-layout: auto; width: 400%;} "
				+ "th {"
				+ "background-color: darkred;"
				+ "color: white;"
				+ "text-align: left;"
				+ "padding: 15px; }"
				+ "td {"
				+ "text-align: left;"
				+ "border-bottom: 1px solid crimson;"
				+ "padding: 10px; }"
				+ "</style>"
				+ "</head>");
		
		pw.println("<body>");
		
		pw.println("<div class=\"container\">"  
				+	"<div class=\"jumbotron jumbotron-fluid bg-transparent text-white\">" 
				+ 	"<div class=\"container-fluid\">" 
				+ 	"<h1 class=\"display-4\">Selected Films</h1>"
				+   "<p class=\"lead\">With the rating " + ratings.toString() + " and a rental rate of $" + rate + "</p>"
				+ 	"</div>"
				+ "</div>");
		pw.println("<hr>");
		
		if(retrieved) {
			pw.println("<div style=\"overflow-x:auto;\">");
			pw.println("<table>");
			pw.println("<tr>"
					+ "<th>Film ID</th>"
					+ "<th>Title</th>"
					+ "<th>Description</th>"
					+ "<th>Genre</th>"
					+ "<th>Price</th>"
					+ "<th>Length</th>"
					+ "<th>Rating</th>"
					+ "<th>Actors</th>"
					+ "</tr>");
			for(Film film: films) {
				pw.println("<tr>"
						+ "<td>" + film.getId() + "</td>"
						+ "<td>" + film.getTitle() + "</td>"
						+ "<td>" + film.getDescription() + "</td>"
						+ "<td>" + film.getGenre() + "</td>"
						+ "<td>$" + film.getPrice() + "</td>"
						+ "<td>" + film.getLength() + " mins</td>"
						+ "<td>" + film.getRating() + "</td>"
						+ "<td>" + film.getActors() + "</td>"
						+ "</tr>");
			}
			pw.println("</table></div>");
		}
		else {
			pw.println("<p>No films were not found.</p>");
		}
		
		pw.println("<br><br><form action=\"http://localhost:8080/FindFilm/\">"
				+"<input type=\"submit\" value=\"Back\" class=\"btn\">" 
				+ "</form>");
		
		pw.println("</body>");
		
		pw.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
