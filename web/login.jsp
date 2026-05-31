
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Red+Rose:wght@300..700&display=swap" rel="stylesheet">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
        <title>Login</title>
    </head>
    <body>
        <main class="container login-form">
            <div class="login-card">
                <div class="header-login">
                    <div class="login-icon">
                        <i class="fa-regular fa-circle-user icon"></i>
                    </div>
                    <div class="header-title">
                        <h1 class="login-title">LOGIN ACCOUNT</h1>
                    </div>
                </div>
                
                <form class="login-fields" action="${pageContext.request.contextPath}/login" method="post"> 
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input id="email" type="email" name="email" placeholder="username@gmail.com" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" type="password" name="password" placeholder="Enter your password" required>
                    </div>
                    
                    
                    
                    <a class="forgot-link" href="#">Forgot your password?</a>
                    
                    <button class="login-button" type="submit">LOGIN</button>
                    
                    <p class="register-text">
                        Don't have an account?
                        <a href="${pageContext.request.contextPath}/register">Register</a>
                    </p>
                </form>
            </div>
        </main>
    </body>
</html>
