<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
        <style>
           
tr:nth-child(even) {
  background-color: rgb(197, 212, 255);
  
}

tr:nth-child(odd) {
  background-color: rgb(255, 225, 150);
  
}

   tr:hover {background-color: lightgrey;}   
   table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
        </style>
    </head>
   <body style="background-color:rgba(0, 128, 0, 0.3);">
        <c:if test="${users ne null}">
        <h1>User Management System</h1>
        <table>
            <tr>
                <td>Email</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Role</td>
                <td>Active</td>
                <td>Edit</td>
                <td>Delete</td>
            </tr>
             <c:forEach items="${users}" var="user">
                <tr>
                 <td>${user.email}</td>
                 <td>${user.fname}</td>
                 <td>${user.lname}</td>
                 <td>${user.getRolename()}</td>
                 <td>
                     <c:if test="${user.active == 1}">
                         <input type="checkbox" name="chkBox" checked = "checked">
                     </c:if>
                     <c:if test="${user.active == 0}">
                         <input type="checkbox" name="chkBox">
                     </c:if>   
                 </td>
                 <td><a href="users?action=edit&amp;email=${user.email}"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
                                                <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
                                            </svg></a></td>
                 
                 <td><a href="users?action=delete&amp;email=${user.email}"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
                                                <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5Zm-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5ZM4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06Zm6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528ZM8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5Z"/>
                                            </svg></a></td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    <center><a href="users?action=add">Add User</a> </center>
        <c:if test="${adduser==true}">
            <h2>Add User</h2>
            <form action="users" method="post">
                <table border ="1px">
                      <tr>  <td>Email</td>
                        <td><input type="text" name="email"></td> </tr>
                    <tr> <td>First Name</td> <td><input type="text" name="fname"></td> </tr>
                    <tr> <td>Last Name</td>  <td><input type="text" name="lname"></td> </tr>
                    <tr>  <td>Role</td>  <td>
                            <select name="role">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.roleid}">${role.rolename}</option>
                                </c:forEach>
                            </select>
                        </td> </tr>
                    <tr> <td>Active</td> <td>
                            <input type="checkbox" id="chk" name="active" value="1" checked="checked"/>
                        </td> </tr>
                    <tr> <td>Password</td>  <td> <input type="password" name="password"></td>
                    </tr>
                </table>
                <input type="hidden" name="action" value="create">
                <input type="submit" value="Add">
                <a href="users">Cancel</a>
                
            </form>
        </c:if>
        <c:if test="${selectedUser ne null}">
            <h2>Edit User</h2>
            <form action="users" method="post">
                <table border ="1px">
                   
                    
                    <tr>
                        <td>Email</td>
                        <td>${selectedUser.email}</td> </tr>
                    <tr>  <td>First Name</td>
                        <td><input type="text" name="fname" value="${selectedUser.fname}"</td> </tr>
                    <tr>   <td>Last Name</td>
                        <td><input type="text" name="lname" value="${selectedUser.lname}"</td> </tr>
                    <tr>    <td>Role</td>
                        <td>
                            
                            <select name="role">
                                <c:forEach items="${roles}" var="role">
                                                                        <option value="${role.roleid}" <c:if test="${role.roleid == selectedUser.role}">selected ="selected"</c:if>>${role.rolename}
                                    </option>
                                </c:forEach>
                            </select>
                        </td> </tr>
                    <tr>   <td>Active</td>
                        <td>
                            <c:choose>
                                <c:when test="${selectedUser.active == 1}">
                                  <input type="checkbox" id="chk" name="active" 
                                       value="${selectedUser.active}" checked="checked"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="chk" name="active" 
                                          value="${selectedUser.active}"/>
                                </c:otherwise>
                            </c:choose>
                        </td> </tr>
                    <tr> <td>Password</td>
                        <td> <input type="password" name="password" value="${selectedUser.password}"</td> 
                    </tr>
                </table>
                
                <input type="hidden" name="email" value="${selectedUser.email}">
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Save">
                 <a href="users">Cancel</a>
            </form>
        </c:if>
    </body>
</html>
