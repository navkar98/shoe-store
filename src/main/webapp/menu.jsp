<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cs636.shoestore.domain.Shoe" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Shoe Store</title>
	<style>
		table, th, td {
			border: 1px solid black;
			border-collapse: collapse;
			padding: 5px;
		}
	</style>
</head>
<body>
	<h1>Shoe Store</h1>
	
	<form action="filter" method="post">
		<h2>Filters</h2>
		
		<h3>Brand</h3>
		<p>
			<input type="radio" name="brand" value="nike"> Nike<br>
			<input type="radio" name="brand" value="adidas"> Adidas<br>
			<input type="radio" name="brand" value="reebok"> Reebok<br>
		</p>
		
		<h3>Gender</h3>
		<p>
			<input type="radio" name="gender" value="male"> Male<br>
			<input type="radio" name="gender" value="female"> Female<br>
		</p>
		
		<h3>Price</h3>
		<p>
			Min: <input type="number" name="minPrice"><br>
			Max: <input type="number" name="maxPrice"><br>
		</p>
		
		<h3>Color</h3>
		<p>
			<select name="color">
				<option value="">--Select--</option>
				<option value="#000000">black</option>
				<option value="#FFFFFF">white</option>
				<option value="#FF0000">red</option>
				<option value="#0000FF">blue</option>
				<option value="#008000">green</option>
			</select>
		</p>
		
		<input type="submit" value="Apply Filters">
	</form>
	
	<hr>
	
	<h2>Shoe List</h2>
	
	<table>
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Buy</th>
		</tr>
		
		<%
			List<Shoe> shoes = (List<Shoe>) request.getAttribute("shoes");
			for (Shoe shoe : shoes) {
		%>
		
		<tr>
			<td><%=shoe.getName()%></td>
			<td><%=shoe.getPrice()%></td>
			<td><%=shoe.getDescription()%></td>
			<td>
				<form action="buy" method="post">
					<input type="hidden" name="id" value="<%=shoe.getId()%>">
					<input type="hidden" name="price" value="<%=shoe.getPrice()%>">
					<input type="hidden" name="user_id" value="<%=request.getAttribute("user_id")%>">
					<input type="submit" value="Buy">
				</form>
			</td>
		</tr>
		
		<%
			}
		%>
	</table>
	
</body>
</html>
