
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
        <title>Register Page</title>
        
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="min-h-screen bg-slate-100 flex items-center justify-center px-4">
   
        <main class="w-full max-w-md rounded-3xl bg-white px-8 py-10 shadow-lg">
            <div class="flex flex-col items-center gap-2">
                <i class="fa-regular fa-circle-user text-3xl text-[#093C5D] font-medium"></i>
                <h1 class="text-[#093C5D] text-2xl font-bold border-b-2 border-black">
                    REGISTER ACCOUNT
                </h1>
            </div>
            
            <form action="${pageContext.request.contextPath}/register" method="post" class="mt-8 space-y-5">
                
                <div class="flex flex-col">
                    <label for="fullName" class="mb-2 block text-slate-700 font-medium">Full Name</label>
                    <input id="fullName" name="fullName" type="text" placeholder="Enter your name here." required class="w-full size-10 rounded-2xl px-4 py-3 outline-none border border-1 border-slate-300 transition placeholder:text-slate-400 focus:ring-2 focus:ring-[#093C5D]/4">
                </div>
                
                <div class="flex flex-col">
                    <label for="email" class="mb-2 block text-slate-700 font-medium">Email address</label>
                    <input id="email" name="email" type="email" placeholder="username@gmail.com" required class="w-full size-10 rounded-2xl px-4 py-3 outline-none border border-1 border-slate-300 transition placeholder:text-slate-400 focus:ring-2 focus:ring-[#093C5D]/4">
                </div>
                
                <div class="flex flex-col">
                    <label for="phone" class="mb-2 block text-slate-700 font-medium">Phone number</label>
                    <input id="phone" name="phone" type="tel" placeholder="Enter your phone here." pattern="[0-9]{10}" required class="w-full size-10 rounded-2xl px-4 py-3 outline-none border border-1 border-slate-300 transition placeholder:text-slate-400 focus:ring-2 focus:ring-[#093C5D]/4">
                </div>
                
                <div class="flex flex-col">
                    <label for="password" class="mb-2 block text-slate-700 font-medium">Password</label>
                    <input id="password" name="password" type="password" placeholder="Enter your password here." required class="w-full size-10 rounded-2xl px-4 py-3 outline-none border border-1 border-slate-300 transition placeholder:text-slate-400 focus:ring-2 focus:ring-[#093C5D]/4">
                </div>
                
                <button class="mt-2 w-full px-4 py-3 bg-[#093C5D] text-white font-bold text-xl border rounded-2xl hover:bg-[#3B7597]" type="submit"> 
                    REGISTER
                </button>
                
                <div class="mt-6 flex flex-col gap-2 text-base text-slate-700">
                    <label class="flex items-center gap-2">
                        <input type="checkbox" name="terms" required class="h-4 w-4">
                        <span>I agree to the Terms of Services.</span>
                    </label>
                    
                    <label class="flex items-center gap-2">
                        <input type="checkbox" name="policy" required class="h-4 w-4">
                        <span>I agree to the Privacy Policy.</span>
                    </label>
                    
                    <label class="flex items-center gap-2">
                        <input type="checkbox" name="info" required class="h-4 w-4">
                        <span>I confirm that my information is accurate.</span>
                    </label>
                </div>
                
                <p class="w-full text-center text-sm text-slate-600">
                    Already have account?
                    <a href="${pageContext.request.contextPath}/login" class="text-[#2563EB] font-medium text-l hover:text-[#1D4ED8]">
                        Login 
                    </a>
                </p>
            </form>
        </main>
    </body>
</html>
