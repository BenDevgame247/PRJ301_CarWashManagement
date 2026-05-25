
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Red+Rose:wght@300..700&display=swap" rel="stylesheet">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css">
        <title>Register Page</title>
    </head>
    <body>
        <main class="container register-form">
            <div class="register-card">
                <div class="header-register">
                    <div class="register-icon">
                        <i class="fa-regular fa-circle-user icon"></i>
                    </div>
                    <div class="header-title">
                        <h1 class="register-title">REGISTER ACCOUNT</h1>
                    </div>
                </div>
                
                <form class="register-fields" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="fullname">Full name</label>
                        <input id="fullname" type="name" name="fullname" placeholder="Enter your name here." required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input id="email" type="email" name="email" placeholder="username@gmail.com" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="phone">Phone number</label>
                        <input id="phone" type="tel" name="phone" placeholder="Enter your number here." pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" type="password" name="password" placeholder="Enter your password" required>
                    </div>
                    
                    <!--<a class="forgot-link" href="#">Forgot your password?</a>-->
                    
                    <div class="checkbox-required">
                        <label class="checkbox-line">
                            <input name="terms" type="checkbox" required>
                            <span>I agree to the Terms of Services.</span>
                        </label>
                        
                        <label class="checkbox-line">
                            <input name="privacy" type="checkbox" required>
                            <span>I agree to the Privacy Policy.</span>
                        </label>
                        
                        <label class="checkbox-line">
                            <input name="accurate" type="checkbox" required>
                            <span>I confirm that my information is accurate.</span>
                        </label>
                    </div>
                    
                    <button class="register-button" type="submit">REGISTER</button>
                    
                    <p class="login-text">
                        Already have accounts?
                        <a href="${pageContext.request.contextPath}/login">Login</a>
                    </p>
                </form>
            </div>
        </main>
    </body>
</html>
