package com.example.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.Employee;
import com.example.model.Login;
import com.example.model.EmployeeDAO;


@WebServlet("")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private EmployeeDAO empDAO;
	 HttpSession session =null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    // init method
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        empDAO = new EmployeeDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		  String action = request.getServletPath();
		  System.out.println("action"+action);
		//   String email=(String)session.getAttribute("email");
			session=request.getSession(true);
			session.setMaxInactiveInterval(60);
	        //redirect user to home page if already logged in
			
	      if(session.isNew())
	      {
	    	  if(!action.equals("/login"))
	    	  {
	    		  response.sendRedirect("welcome.jsp");  
	    		  return;
	    	  }
	    	    	 
	      }
	     
	    	  try {
		            switch (action) {
		            case "/new":
		                showNewForm(request, response);
		                break;
		            case "/insert":
		                insertEmp(request, response);
		                break;
		            case "/delete":
		                deleteBook(request, response);
		                break;
		            case "/edit":
		                showEditForm(request, response);
		                break;
		            case "/update":
		                updateBook(request, response);
		                break;
		            case "/list":
		            	 listBook(request, response);
			                break;
		            case "/logout":
		            	logout(request,response);
		            	break;
		            case "/login":
		            	loginValidate(request,response);
		            	break;
		            default:
		            	response.sendRedirect("welcome.jsp");
		            	break;
		            }
		        }
		      
		      catch (SQLException ex) {
		            throw new ServletException(ex);
		        }
	      
	     
	      
	}
	@SuppressWarnings("null")
	private void loginValidate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
		System.out.println("objj");
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		Login obj=new Login(uname,pwd);
		
		System.out.println("obj"+obj.getUname());
       String result= empDAO.loginCheck(obj);
   	System.out.println("result"+result);
        request.setAttribute("result", result);
        PrintWriter out = response.getWriter();
        if(result.equals("super")){
        	System.out.println("result  if("+result);
			session.setAttribute("email",obj.getUname());	
            response.sendRedirect("list");
        }
        if(result.equals("normal")){
			session.setAttribute("email",obj.getUname());	
            response.sendRedirect("home_user.jsp");
        }
        if(result.equals("false")){
        	out.println("<font color=red size=4px>"+"Invalid username or password <a href='index.jsp' >try again</a>");
        	// response.sendRedirect("index.jsp");
        }
      
    }
	
	private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
	
	//out.println("thanq you!!, Your session was destroyed successfully!!");
	HttpSession session = request.getSession(false);
	// session.setAttribute("user", null);
	session.removeAttribute("email");
	 response.sendRedirect("index.jsp");
	}
	private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listemp = empDAO.listAllEmployees();
        request.setAttribute("listEmp", listemp);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addemp.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id from servle 111111t"+id);
        Employee existingEmp = empDAO.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addemp.jsp");
        request.setAttribute("emp", existingEmp);
        dispatcher.forward(request, response);
 
    }
 
    private void insertEmp(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
      //  int contact = Integer.parseInt(contact1.trim());
        String desgn = request.getParameter("desgn");
        Employee newEmp = new Employee(name, contact, address,desgn);
        empDAO.insertEmp(newEmp);
        response.sendRedirect("list");
    }
 
    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String id1=request.getParameter("id");
    	  // System.out.println("id from servlet"+id1);
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        String desgn = request.getParameter("desgn");
     
       // System.out.println("id from servlet name "+name);
        Employee emp = new Employee(id,name, contact, address,desgn);
        empDAO.updateBook(emp);
        response.sendRedirect("list");
    }
 
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Employee emp = new Employee(id);
       empDAO.deleteBook(emp);
        response.sendRedirect("list");
 
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
