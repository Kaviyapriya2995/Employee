<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Employee Application</title>
</head>
<body>
    <center>
        <h1>Employee Management</h1>
        <h2>
            <a href="new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Employees</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${emp != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${emp == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${emp != null}">
                        Edit Employee Details
                    </c:if>
                    <c:if test="${emp == null}">
                        Add New Employee
                    </c:if>
                </h2>
            </caption>
                <c:if test="${emp != null}">
                    <input type="hidden" name="id" value="<c:out value='${emp.id}' />" />
                </c:if>           
            <tr>
                <th>Employee Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${emp.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Employee Contact </th>
                <td>
                    <input type="text" name="contact" size="15"
                            value="<c:out value='${emp.contact}' />"
                    />
                </td>
            </tr>
            <tr>
                <th> Employee Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                            value="<c:out value='${emp.address}' />"
                    />
                </td>
            </tr>
               <tr>
                <th> Employee Designation: </th>
                <td>
                    <input type="text" name="desgn" size="45"
                            value="<c:out value='${emp.desgn}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>