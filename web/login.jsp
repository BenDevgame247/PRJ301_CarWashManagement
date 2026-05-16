<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="email" name="email" placeholder="Email">
    <input type="password" name="password" placeholder="Password">
    <button type="submit">Login</button>
</form>

<p style="color:red">${error}</p>

