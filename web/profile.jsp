<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Car Wash Profile</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen bg-slate-100 p-6 flex items-center justify-center">

    <div class="w-full max-w-5xl bg-white rounded-3xl shadow-2xl overflow-hidden grid grid-cols-1 md:grid-cols-3">

        <!-- Left Side -->
        <div class="bg-sky-600 text-white p-8 flex flex-col items-center justify-center">

            <img
                src="https://i.pravatar.cc/300"
                alt="Profile"
                class="w-36 h-36 rounded-full border-4 border-white shadow-lg object-cover"
            />

            <h1 class="text-3xl font-bold mt-6">Nguyen Van A</h1>
            <p class="text-sky-100 mt-2">Car Wash Staff</p>

            <div class="mt-8 w-full space-y-4 text-sm">

                <div class="bg-white/20 rounded-2xl p-4">
                    <p class="font-semibold">Employee ID</p>
                    <p>EMP001</p>
                </div>

                <div class="bg-white/20 rounded-2xl p-4">
                    <p class="font-semibold">Phone</p>
                    <p>0123 456 789</p>
                </div>

                <div class="bg-white/20 rounded-2xl p-4">
                    <p class="font-semibold">Working Shift</p>
                    <p>Morning Shift</p>
                </div>

            </div>
        </div>

        <!-- Right Side -->
        <div class="md:col-span-2 p-8">

            <div class="flex items-center justify-between">
                <div>
                    <h2 class="text-3xl font-bold text-slate-800">
                        Profile Information
                    </h2>

                    <p class="text-slate-500 mt-1">
                        Manage your personal information.
                    </p>
                </div>

                <button class="bg-sky-600 hover:bg-sky-700 transition px-6 py-3 rounded-2xl text-white font-semibold shadow-lg">
                    Edit Profile
                </button>
            </div>

            <!-- Info Cards -->
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6 mt-10">

                <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                    <p class="text-slate-500 text-sm">Full Name</p>
                    <h3 class="text-xl font-semibold text-slate-800 mt-2">
                        Nguyen Van A
                    </h3>
                </div>

                <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                    <p class="text-slate-500 text-sm">Email</p>
                    <h3 class="text-xl font-semibold text-slate-800 mt-2">
                        nguyenvana@gmail.com
                    </h3>
                </div>

                <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                    <p class="text-slate-500 text-sm">Address</p>
                    <h3 class="text-xl font-semibold text-slate-800 mt-2">
                        Ho Chi Minh City
                    </h3>
                </div>

                <div class="bg-slate-100 rounded-2xl p-5 shadow-sm">
                    <p class="text-slate-500 text-sm">Role</p>
                    <h3 class="text-xl font-semibold text-slate-800 mt-2">
                        Staff
                    </h3>
                </div>

            </div>

            <!-- Statistics -->
            <div class="mt-10">
                <h3 class="text-2xl font-bold text-slate-800 mb-5">
                    Performance Overview
                </h3>

                <div class="grid grid-cols-1 md:grid-cols-3 gap-5">

                    <div class="bg-green-100 rounded-2xl p-6">
                        <p class="text-green-700 font-medium">Cars Washed</p>
                        <h2 class="text-4xl font-bold text-green-900 mt-3">
                            245
                        </h2>
                    </div>

                    <div class="bg-yellow-100 rounded-2xl p-6">
                        <p class="text-yellow-700 font-medium">Monthly Income</p>
                        <h2 class="text-4xl font-bold text-yellow-900 mt-3">
                            $1,250
                        </h2>
                    </div>

                    <div class="bg-blue-100 rounded-2xl p-6">
                        <p class="text-blue-700 font-medium">Customer Rating</p>
                        <h2 class="text-4xl font-bold text-blue-900 mt-3">
                            4.9
                        </h2>
                    </div>

                </div>
            </div>

            <!-- Recent Activities -->
            <div class="mt-10 bg-slate-100 rounded-3xl p-6 shadow-sm">

                <h3 class="text-2xl font-bold text-slate-800 mb-6">
                    Recent Activities
                </h3>

                <div class="space-y-4">

                    <div class="flex items-center justify-between bg-white rounded-2xl p-4">
                        <div>
                            <p class="font-semibold text-slate-800">
                                Completed Premium Wash
                            </p>

                            <p class="text-sm text-slate-500">
                                Toyota Camry - 08:30 AM
                            </p>
                        </div>

                        <span class="bg-green-100 text-green-700 px-4 py-2 rounded-xl text-sm font-semibold">
                            Done
                        </span>
                    </div>

                    <div class="flex items-center justify-between bg-white rounded-2xl p-4">
                        <div>
                            <p class="font-semibold text-slate-800">
                                Updated Customer Booking
                            </p>

                            <p class="text-sm text-slate-500">
                                Honda Civic - 10:15 AM
                            </p>
                        </div>

                        <span class="bg-blue-100 text-blue-700 px-4 py-2 rounded-xl text-sm font-semibold">
                            Updated
                        </span>
                    </div>

                    <div class="flex items-center justify-between bg-white rounded-2xl p-4">
                        <div>
                            <p class="font-semibold text-slate-800">
                                Received Customer Feedback
                            </p>

                            <p class="text-sm text-slate-500">
                                Excellent Service
                            </p>
                        </div>

                        <span class="bg-yellow-100 text-yellow-700 px-4 py-2 rounded-xl text-sm font-semibold">
                            Review
                        </span>
                    </div>

                </div>
            </div>

        </div>
    </div>

</body>
</html>
