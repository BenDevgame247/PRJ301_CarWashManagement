<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Customer Profile</title>

        <script src="https://cdn.tailwindcss.com"></script>
    </head>

    <body class="min-h-screen bg-slate-100 p-6 flex items-center justify-center">

        <main class="w-full max-w-5xl bg-white rounded-3xl shadow-2xl overflow-hidden">
            <% if (request.getAttribute("profileError") != null) {%>
            <div class="p-8">
                <div class="rounded-xl bg-red-100 px-4 py-3 text-red-700">
                    <%= request.getAttribute("profileError")%>
                </div>

                <div class="mt-6">
                    <a href="${pageContext.request.contextPath}/logout"
                       class="inline-block bg-sky-600 hover:bg-sky-700 transition px-6 py-3 rounded-2xl text-white font-semibold shadow-lg">
                        Logout
                    </a>
                </div>
            </div>
            <% } else { %>

            <div class="grid grid-cols-1 md:grid-cols-3">
                <aside class="bg-sky-600 text-white p-8 flex flex-col items-center justify-center">
                    <div class="w-36 h-36 rounded-full border-4 border-white shadow-lg bg-white/20 flex items-center justify-center">
                        <span class="text-5xl font-bold">
                            ${profile.fullName.substring(0, 1)}
                        </span>
                    </div>

                    <h1 class="text-3xl font-bold mt-6 text-center">
                        ${profile.fullName}
                    </h1>

                    <p class="text-sky-100 mt-2">
                        Customer
                    </p>

                    <div class="mt-8 w-full space-y-4 text-sm">
                        <div class="bg-white/20 rounded-2xl p-4">
                            <p class="font-semibold">Customer ID</p>
                            <p>CUST-${profile.customerId}</p>
                        </div>

                        <div class="bg-white/20 rounded-2xl p-4">
                            <p class="font-semibold">Phone</p>
                            <p>${profile.phone}</p>
                        </div>

                        <div class="bg-white/20 rounded-2xl p-4">
                            <p class="font-semibold">License Plate</p>
                            <p>${profile.plateNumber}</p>
                        </div>
                    </div>
                </aside>

                <section class="md:col-span-2 p-8">
                    <div class="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
                        <div>
                            <h2 class="text-3xl font-bold text-slate-800">
                                Profile Information
                            </h2>

                            <p class="text-slate-500 mt-1">
                                Customer information and loyalty status.
                            </p>
                        </div>

                        <a href="${pageContext.request.contextPath}/logout"
                           class="bg-sky-600 hover:bg-sky-700 transition px-6 py-3 rounded-2xl text-white font-semibold shadow-lg text-center">
                            Logout
                        </a>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mt-10">
                        <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                            <p class="text-slate-500 text-sm">Full Name</p>
                            <h3 class="text-xl font-semibold text-slate-800 mt-2">
                                ${profile.fullName}
                            </h3>
                        </div>

                        <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                            <p class="text-slate-500 text-sm">Email</p>
                            <h3 class="text-xl font-semibold text-slate-800 mt-2">
                                ${profile.email}
                            </h3>
                        </div>

                        <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                            <p class="text-slate-500 text-sm">Phone</p>
                            <h3 class="text-xl font-semibold text-slate-800 mt-2">
                                ${profile.phone}
                            </h3>
                        </div>

                        <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                            <p class="text-slate-500 text-sm">License Plate</p>
                            <h3 class="text-xl font-semibold text-slate-800 mt-2">
                                ${profile.plateNumber}
                            </h3>
                        </div>
                    </div>

                    <div class="mt-10">
                        <h3 class="text-2xl font-bold text-slate-800 mb-5">
                            Loyalty Overview
                        </h3>

                        <div class="grid grid-cols-1 md:grid-cols-3 gap-5">
                            <div class="bg-green-100 rounded-2xl p-6">
                                <p class="text-green-700 font-medium">Tier</p>
                                <h2 class="text-4xl font-bold text-green-900 mt-3">
                                    ${profile.tierName}
                                </h2>
                            </div>

                            <div class="bg-yellow-100 rounded-2xl p-6">
                                <p class="text-yellow-700 font-medium">Current Points</p>
                                <h2 class="text-4xl font-bold text-yellow-900 mt-3">
                                    ${profile.currentPoints}
                                </h2>
                            </div>

                            <div class="bg-blue-100 rounded-2xl p-6">
                                <p class="text-blue-700 font-medium">Next Reward</p>
                                <h2 class="text-2xl font-bold text-blue-900 mt-3">
                                    ${empty profile.rewardName ? "No reward" : profile.rewardName}
                                </h2>
                            </div>
                        </div>
                    </div>

                    <div class="mt-10 bg-slate-100 rounded-3xl p-6 shadow-sm">
                        <h3 class="text-2xl font-bold text-slate-800 mb-6">
                            Vehicle Information
                        </h3>

                        <div class="grid grid-cols-1 md:grid-cols-3 gap-5">
                            <div class="bg-white rounded-2xl p-4">
                                <p class="text-slate-500 text-sm">Brand</p>
                                <p class="font-semibold text-slate-800 mt-2">
                                    ${empty profile.brand ? "Not updated" : profile.brand}
                                </p>
                            </div>

                            <div class="bg-white rounded-2xl p-4">
                                <p class="text-slate-500 text-sm">Model</p>
                                <p class="font-semibold text-slate-800 mt-2">
                                    ${empty profile.model ? "Not updated" : profile.model}
                                </p>
                            </div>

                            <div class="bg-white rounded-2xl p-4">
                                <p class="text-slate-500 text-sm">Color</p>
                                <p class="font-semibold text-slate-800 mt-2">
                                    ${empty profile.color ? "Not updated" : profile.color}
                                </p>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <% }%>
        </main>
    </body>
</html>