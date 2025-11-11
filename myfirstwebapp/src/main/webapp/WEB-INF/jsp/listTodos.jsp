<%@ include file="common/header.jspf"%>
<body>
	welcome ${name}
	<hr>
	<%@ include file="common/navigation.jspf"%>
	<div class="container">
		<h2>your todos are:</h2>
		<table class="table">
			<thead>
				<tr>
					<td>description</td>
					<td>targetDate</td>
					<td>isDone</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<th>${todo.description}</th>
						<th>${todo.targetDate}</th>
						<th>${todo.done}</th>
						<th><a href="update-todo?id=${todo.id} "
							class="btn btn-success">Update</a></th>
						<th><a href="delete-todo?id=${todo.id} "
							class="btn btn-warning">Delete</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="add-todo" class="btn btn-success">Add Todo</a>
	</div>
	<%@ include file="common/footer.jspf"%>