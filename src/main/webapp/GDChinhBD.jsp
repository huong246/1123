<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ Bạn Đọc</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #ffffff;
            display: flex;
            min-height: 100vh;
        }

        .sidebar {
            width: 250px;
            background-color: #1a2226;
            color: #b8c7ce;
            display: flex;
            flex-direction: column;
        }

        .sidebar-header {
            background-color: #1b4b8a;
            color: white;
            padding: 15px 20px;
            font-size: 22px;
            font-weight: bold;
        }

        .sidebar-menu {
            list-style: none;
            margin-top: 10px;
        }

        .sidebar-menu li a {
            display: block;
            padding: 12px 20px;
            color: #b8c7ce;
            text-decoration: none;
            font-size: 15px;
            transition: 0.3s;
        }

        .sidebar-menu li a i {
            margin-right: 10px;
            width: 20px;
            text-align: center;
        }

        .sidebar-menu li a:hover {
            color: #ffffff;
            background-color: #222d32;
        }

        .sidebar-menu li.active a {
            background-color: #357ca5;
            color: white;
            border-left: 3px solid #3c8dbc;
        }

        .main-content {
            flex: 1;
            display: flex;
            flex-direction: column;
            background-color: #f9fafc;
        }

        .top-header {
            background-color: #3c8dbc;
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 15px;
        }

        .top-header .user-info span {
            font-weight: bold;
            margin-left: 5px;
        }

        .btn-logout {
            color: white;
            text-decoration: none;
            background-color: rgba(255, 255, 255, 0.2);
            padding: 6px 12px;
            border-radius: 4px;
            font-size: 14px;
            transition: 0.3s;
        }

        .btn-logout:hover {
            background-color: rgba(255, 255, 255, 0.4);
        }

        .content-body {
            padding: 40px;
        }

        .content-body h1 {
            font-size: 26px;
            color: #333;
            text-transform: uppercase;
            margin-bottom: 5px;
        }

        .content-body p.desc {
            font-size: 15px;
            color: #666;
            margin-bottom: 40px;
        }

        .dashboard-grid {
            display: flex;
            gap: 30px;
            flex-wrap: wrap;
        }

        .action-card {
            background: #ffffff;
            width: 260px;
            padding: 30px 20px;
            border-radius: 8px;
            border: 1px solid #e1e5eb;
            text-align: center;
            text-decoration: none;
            color: #333;
            box-shadow: 0 4px 15px rgba(0,0,0,0.04);
            transition: all 0.3s ease;
        }

        .action-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            border-color: #3c8dbc;
        }

        .action-card i {
            font-size: 45px;
            color: #3c8dbc;
            margin-bottom: 20px;
            display: block;
        }

        .action-card h3 {
            font-size: 18px;
            margin-bottom: 10px;
            color: #2c3e50;
        }

        .action-card p {
            font-size: 13px;
            color: #7f8c8d;
        }

    </style>
</head>
<body>
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="login.jsp" />
    </c:if>
    <div class="sidebar">
        <div class="sidebar-header">LibMan</div>
        <ul class="sidebar-menu">
            <li class="active"><a href="GDChinhBD.jsp"><i class="fas fa-home"></i> Trang chủ</a></li>
            <li><a href="DangKyTheController"><i class="fas fa-id-card"></i> Thẻ bạn đọc</a></li>
            <li><a href="#" style="color: transparent;">-</a></li>
        </ul>
    </div>
    <div class="main-content">
        <div class="top-header">
            <div class="user-info">
                Xin chào, <span>${sessionScope.user.hoVaTen}</span>
            </div>
            <a href="LogoutController" class="btn-logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
        </div>
        <div class="content-body">
            <h1>HỆ THỐNG THƯ VIỆN</h1>
            <p class="desc">Vui lòng chọn chức năng bạn muốn thực hiện.</p>

            <div class="dashboard-grid">
                <a href="DangKyTheController" class="action-card">
                    <i class="fas fa-id-card"></i>
                    <h3>Thẻ bạn đọc</h3>
                    <p>Khởi tạo thẻ thư viện mới hoặc xem thông tin thẻ hiện tại của bạn.</p>
                </a>
            </div>
        </div>
    </div>

</body>
</html>