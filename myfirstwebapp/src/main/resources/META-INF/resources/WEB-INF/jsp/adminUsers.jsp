<%@ include file="common/header.jspf"%>
<body>
	<%@ include file="common/navigation.jspf"%>
	<div class="container">
		<h2>Admin - Create User</h2>
		<c:if test="${not empty successMessage}">
			<div class="alert alert-success">${successMessage}</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>

		<form:form method="post" modelAttribute="userForm">
			<fieldset class="mb-3">
				<form:label path="username">Username</form:label>
				<form:input path="username" type="text" class="form-control" required="required" />
				<form:errors path="username" cssClass="text-danger" />
			</fieldset>

			<fieldset class="mb-3">
				<form:label path="password">Password</form:label>
				<form:input path="password" type="password" class="form-control" required="required" />
				<form:errors path="password" cssClass="text-danger" />
			</fieldset>

			<fieldset class="mb-3">
				<form:label path="role">Role</form:label>
				<form:select path="role" class="form-control">
					<form:option value="USER" label="USER" />
					<form:option value="ADMIN" label="ADMIN" />
				</form:select>
				<form:errors path="role" cssClass="text-danger" />
			</fieldset>

			<input type="submit" class="btn btn-primary" value="Create User" />
		</form:form>

		<hr />
		<h4>Users in this session</h4>
		<ul>
			<c:forEach items="${users}" var="user">
				<li>${user}</li>
			</c:forEach>
		</ul>
	</div>
	<%@ include file="common/footer.jspf"%>
</body>
