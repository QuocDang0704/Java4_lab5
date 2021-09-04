<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	<base href="/Lab5/">
    <title>Them sua xoa User</title>
  </head>
  <body>
    <main class="container">
    	<div class="row">
    		<div class="col">
    			<c:if test="${not empty message}">
    				<div class="alert alert-success">${message }</div>
    			</c:if>
    			<c:if test="${not empty error}">
    				<div class="alert alert-danger">${error }</div>
    			</c:if>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col">
    			<form action="UserServlet" method="post">
    				<div class="form-group">
    					<label for="userId">User ID:</label>
    					<input type="text" name="id" id="id" class="form-control" value="${user.id }" />
    				</div>
    				<div class="form-group">
    					<label for="password">Password:</label>
    					<input type="password" name="password" class="form-control" id="password" />
    				</div>
    				<div class="form-group">
    					<label for="fullname">Fullname:</label>
    					<input type="text" name="fullname" class="form-control" id="fullname" value="${user.fullname }"/>
    				</div>
    				<div class="form-group">
    					<label for="email">Email Address:</label>
    					<input type="email" name="email" class="form-control" id="email" value="${user.email }"/>
    				</div>
    				<div class="form-group">
    					<label >Role:</label>
    					<label class="ml-5"><input type="radio" class="form-check-input" id="admin" 
    					name="admin" value="true" ${user.admin? 'checked':'' }/>Admin</label>
    					<label class="ml-5"><input type="radio" class="form-check-input" id="user" 
    					name="admin" value="false" ${!user.admin? 'checked':'' }/>user</label>
    				</div>
    				<div class="form-group">
    					<button class="btn btn-primary" formaction="UserServlet/create">Create</button>
    					<button class="btn btn-warning" formaction="UserServlet/update">Update</button>
    					<button class="btn btn-danger" formaction="UserServlet/delete">Delete</button>
    					<button class="btn btn-info" formaction="UserServlet/reset">Reset</button>
    				</div>
    			</form>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col">
    			<table class="table table-stripe">
    				<tr>
    					<th>User ID</th>
    					<th>Fullname</th>
    					<th>Email</th>
    					<th>Role</th>
    					<th>&nbsp;</th>
    				</tr>
    				<c:forEach var="item" items="${users }">
    					<tr>
	    					<td>${item.id }</td>
	    					<td>${item.fullname }</td>
	    					<td>${item.email }</td>
	    					<td>${item.admin? 'Admin' : 'User' }</td>
	    					<td>
	    						<a href="UserServlet/edit?id=${item.id }">Edit</a>
	    						<a href="UserServlet/delete?id=${item.id }">Delete</a>
	    					</td>
    					</tr>
    				</c:forEach>
    			</table>
    		</div>
    	</div>
    </main>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
  </body>
</html>