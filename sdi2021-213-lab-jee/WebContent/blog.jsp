<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="com.uniovi.sdi.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<title>JSP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<jsp:useBean id="comentario" class="com.uniovi.sdi.Comentario" />
	<jsp:setProperty name="comentario" property="*" />
	<c:if test="${comentario.nombre != null}">
		<jsp:useBean id="comentariosService"
			class="com.uniovi.sdi.ComentariosService" />
		<jsp:setProperty name="comentariosService" property="nuevoComentario"
			value="${comentario}" />
		<c:redirect url="/blog" />
	</c:if>
	
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li><a href="index.jsp">Inicio</a></li>
			
		</ul>
		
	</div>
	</nav>
	
	
	<div class="container" id="contenedor-principal">
		<h2>Comentarios</h2>
		<c:forEach var="c" items="${comentarios}">
			<tr>
				<p><b>${c.nombre}</b>: ${c.comentario}</p>
			</tr>
		</c:forEach>

		<br />
		<h2>Nuevo comentario</h2>
		<form class="form-horizontal" method="post" action="blog.jsp">
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Nombre:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="nombre"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="text">Comentario:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="comentario"
						required="true" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Enviar comentario</button>
				</div>
			</div>
		</form>
	</div>
</body>

</html>