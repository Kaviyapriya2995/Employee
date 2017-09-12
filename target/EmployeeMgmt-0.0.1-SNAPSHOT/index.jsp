<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <title>Login System</title>
    </head>
 
    <body>
    <h1 align="center">  Login page</h1>
       
   
        <form   action="login" method="post">
            <table align="center" cellpadding="5">
                <tr>
                    <td><b>Username:</b></td>
                    <td><input type="text" name="uname" required/></td>
                </tr>
 
                <tr>
                    <td><b>Password:</b></td>
                    <td><input type="password" name="pwd" required/></td>
                </tr>
 
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Login"/></td>
                </tr>
 
            </table>
        </form>
    
    </body>
</html>