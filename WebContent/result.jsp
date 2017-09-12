<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management Application</title>

</head>
<body>
<h1 align="center">Employee Management</h1>
<h2>
            <a href="new" >Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Employees</a>
              &nbsp;&nbsp;&nbsp;
             <a align="right"  href="logout">Logout</a>
        </h2>
   <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Employees</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Contact</th>
                <th>Address</th>
                <th>Designation</th>
                 <th>Actions</th>
            </tr>
            <c:forEach var="emp" items="${listEmp}">
                <tr>
                    <td><c:out value="${emp.id}" /></td>
                    <td><c:out value="${emp.name}" /></td>
                    <td><c:out value="${emp.contact}" /></td>
                    <td><c:out value="${emp.address}" /></td>
                     <td><c:out value="${emp.desgn}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${emp.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${emp.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>   
</body>
</html>