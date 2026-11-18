<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Thẻ Bạn Đọc</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body { background-color: #ffffff; display: flex; min-height: 100vh; }

        .sidebar { width: 250px; background-color: #1a2226; color: #b8c7ce; display: flex; flex-direction: column; }
        .sidebar-header { background-color: #1b4b8a; color: white; padding: 15px 20px; font-size: 22px; font-weight: bold; }
        .sidebar-menu { list-style: none; margin-top: 10px; }
        .sidebar-menu li a { display: block; padding: 12px 20px; color: #b8c7ce; text-decoration: none; font-size: 15px; transition: 0.3s; }
        .sidebar-menu li a i { margin-right: 10px; width: 20px; text-align: center; }
        .sidebar-menu li a:hover { color: #ffffff; background-color: #222d32; }
        .sidebar-menu li.active a { background-color: #357ca5; color: white; border-left: 3px solid #3c8dbc; }

        .main-content { flex: 1; display: flex; flex-direction: column; background-color: #f9fafc; }
        .top-header { background-color: #3c8dbc; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; font-size: 15px; }
        .top-header .user-info span { font-weight: bold; margin-left: 5px; }
        .btn-logout { color: white; text-decoration: none; background-color: rgba(255, 255, 255, 0.2); padding: 6px 12px; border-radius: 4px; font-size: 14px; transition: 0.3s; }
        .btn-logout:hover { background-color: rgba(255, 255, 255, 0.4); }

        .content-body { padding: 30px 40px; }
        .content-body h1 { font-size: 26px; color: #333; text-transform: uppercase; margin-bottom: 5px; }
        .content-body p.desc { font-size: 14px; color: #666; margin-bottom: 30px; }

        .form-grid { display: grid; grid-template-columns: 1fr 1fr; column-gap: 30px; row-gap: 20px; }
        .form-group label { display: block; font-size: 14px; color: #333; margin-bottom: 8px; }
        .form-control { width: 100%; padding: 10px; font-size: 14px; border: 1px solid #ccc; border-radius: 4px; }
        .form-control[readonly] { background-color: #f9f9f9; color: #777; cursor: not-allowed; }
        .form-control.input-error { border-color: #e74c3c; border-bottom: 2px solid #e74c3c; }
        .error-text { color: #e74c3c; font-size: 12px; margin-top: 5px; display: none; }
        .server-error { color: #e74c3c; font-size: 14px; margin-bottom: 15px; display: ${not empty errorMsg ? 'block' : 'none'}; }
        .btn-wrapper { margin-top: 35px; text-align: center; }
        .btn-submit { background-color: #4CAF50; color: white; border: none; padding: 10px 30px; font-size: 15px; font-weight: bold; border-radius: 4px; cursor: pointer; transition: 0.3s; }
        .btn-submit:hover { background-color: #45a049; }

        .alert-success { background-color: #e8f5e9; color: #2e7d32; padding: 12px 15px; border-radius: 4px; font-size: 15px; font-weight: 500; margin-bottom: 25px; border: 1px solid #c8e6c9; max-width: 500px; display: ${not empty successMsg ? 'block' : 'none'}; }
        .card-wrapper { max-width: 500px; }
        .library-card { background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%); color: #ffffff; border-radius: 12px; padding: 25px; text-align: left; margin-bottom: 30px; box-shadow: 0 12px 25px rgba(30, 60, 114, 0.25); position: relative; overflow: hidden; }
        .library-card::after { content: ''; position: absolute; top: -40px; right: -40px; width: 130px; height: 130px; background: rgba(255, 255, 255, 0.1); border-radius: 50%; }
        .card-row { display: flex; justify-content: space-between; align-items: center; padding-bottom: 12px; margin-bottom: 12px; border-bottom: 1px solid rgba(255, 255, 255, 0.15); position: relative; z-index: 2; }
        .card-row:last-child { border-bottom: none; padding-bottom: 0; margin-bottom: 0; }
        .card-label { font-size: 12px; text-transform: uppercase; letter-spacing: 0.5px; opacity: 0.8; }
        .card-value { font-size: 16px; font-weight: bold; letter-spacing: 0.5px; }
        .status-badge { background-color: #f1c40f; color: #fff; padding: 4px 10px; border-radius: 20px; font-size: 12px; text-transform: uppercase; font-weight: 700; }
        .status-active { background-color: #2ecc71; }
    </style>
</head>
<body>
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="login.jsp" />
    </c:if>
    <div class="sidebar">
        <div class="sidebar-header">LibMan</div>
        <ul class="sidebar-menu">
            <li><a href="GDChinhBD.jsp"><i class="fas fa-home"></i> Trang chủ</a></li>
            <li class="active"><a href="#"><i class="fas fa-id-card"></i> Thẻ bạn đọc</a></li>
            <li><a href="#" style="color: transparent;">-</a></li>
        </ul>
    </div>
    <div class="main-content">
        <div class="top-header">
            <div class="user-info">Xin chào, <span>${sessionScope.user.hoVaTen}</span></div>
            <a href="LogoutController" class="btn-logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
        </div>
        <div class="content-body">
            <c:choose>
                <c:when test="${not empty theBanDoc}">
                    <h1>THÔNG TIN THẺ THƯ VIỆN</h1>
                    <p class="desc">Dưới đây là thẻ thư viện hiện tại của bạn.</p>
                    <div class="alert-success"><i class="fas fa-check-circle"></i> ${successMsg}</div>
                    <div class="card-wrapper">
                        <div class="library-card">
                            <div class="card-row">
                                <span class="card-label">Mã số thẻ</span><span class="card-value">${theBanDoc.maThe}</span>
                            </div>
                            <div class="card-row">
                                <span class="card-label">Trạng thái</span>
                                <span class="status-badge ${theBanDoc.trangThai == 'Đang hoạt động' ? 'status-active' : ''}">
                                    ${theBanDoc.trangThai}
                                </span>
                            </div>
                            <div class="card-row">
                                <span class="card-label">Ngày cấp</span><span class="card-value">${theBanDoc.ngayDangKy}</span>
                            </div>
                            <div class="card-row">
                                <span class="card-label">Có giá trị đến</span><span class="card-value">${theBanDoc.ngayHetHan}</span>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1>THÔNG TIN ĐĂNG KÝ THẺ BẠN ĐỌC</h1>
                    <p class="desc">Vui lòng điền đầy đủ thông tin</p>
                    <div class="server-error">${errorMsg}</div>
                    <form action="DangKyTheController" method="POST" id="dangKyForm">
                        <div class="form-grid">
                            <div class="form-group"><label>Tên bạn đọc</label><input type="text" class="form-control" value="${thongTinBD.hoVaTen}" readonly tabindex="-1"></div>
                            <div class="form-group"><label>Ngày tháng năm sinh</label><input type="text" class="form-control" value="${thongTinBD.ngaySinh}" readonly tabindex="-1"></div>
                            <div class="form-group"><label>Mã sinh viên</label><input type="text" class="form-control" value="${thongTinBD.maSinhVien}" readonly tabindex="-1"></div>
                            <div class="form-group"><label>Địa chỉ liên hệ</label><input type="text" name="diaChi" class="form-control" value="${thongTinBD.diaChi}"></div>
                            <div class="form-group"><label>Khoa</label><input type="text" class="form-control" value="${thongTinBD.khoa}" readonly tabindex="-1"></div>
                            <div class="form-group"><label>Lớp</label><input type="text" class="form-control" value="${thongTinBD.lop}" readonly tabindex="-1"></div>
                            <div class="form-group"><label>Khóa (Niên khóa)</label><input type="text" class="form-control" value="2023 - 2027" readonly tabindex="-1"></div>
                            <div class="form-group"><label>Ngày đăng ký</label><input type="text" class="form-control" value="<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %>" readonly tabindex="-1"></div>
                            <div class="form-group">
                                <label>Email <span style="color:red">*</span></label>
                                <input type="email" name="email" id="email" class="form-control" value="${thongTinBD.email}">
                                <div class="error-text" id="emailError">Vui lòng nhập Email hợp lệ!</div>
                            </div>
                            <div class="form-group">
                                <label>Số điện thoại <span style="color:red">*</span></label>
                                <input type="text" name="dienThoai" id="dienThoai" class="form-control" value="${thongTinBD.dienThoai}">
                                <div class="error-text" id="phoneError">Vui lòng nhập số điện thoại!</div>
                            </div>
                        </div>
                        <div class="btn-wrapper"><button type="submit" class="btn-submit">+ Đăng ký</button></div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <script>
        const form = document.getElementById('dangKyForm');
        if (form) {
            form.addEventListener('submit', function(event) {
                let isValid = true;
                const emailInput = document.getElementById('email');
                const phoneInput = document.getElementById('dienThoai');
                const emailError = document.getElementById('emailError');
                const phoneError = document.getElementById('phoneError');
                emailInput.classList.remove('input-error'); phoneInput.classList.remove('input-error');
                emailError.style.display = 'none'; phoneError.style.display = 'none';
                if (emailInput.value.trim() === '') { emailInput.classList.add('input-error'); emailError.style.display = 'block'; isValid = false; }
                if (phoneInput.value.trim() === '') { phoneInput.classList.add('input-error'); phoneError.style.display = 'block'; isValid = false; }
                if (!isValid) event.preventDefault();
            });
        }
    </script>
</body>
</html>