<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giao Diện Hóa Đơn Trả</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, sans-serif; }
        body { background-color: #ffffff; display: flex; min-height: 100vh; }
        .sidebar { width: 250px; background-color: #1a2226; color: #b8c7ce; display: flex; flex-direction: column; }
        .sidebar-header { background-color: #1b4b8a; color: white; padding: 15px 20px; font-size: 22px; font-weight: bold; }
        .sidebar-menu { list-style: none; margin-top: 10px; }
        .sidebar-menu li a { display: block; padding: 12px 20px; color: #b8c7ce; text-decoration: none; font-size: 15px; }
        .sidebar-menu li.active a { background-color: #357ca5; color: white; border-left: 3px solid #3c8dbc; }
        .main-content { flex: 1; display: flex; flex-direction: column; background-color: #f9fafc; }
        .top-header { background-color: #3c8dbc; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; }
        .content-body { padding: 40px; display: flex; flex-direction: column; align-items: center; }
        .invoice-card { background: #ffffff; width: 100%; max-width: 1000px; border-radius: 8px; box-shadow: 0 5px 20px rgba(0,0,0,0.08); border: 1px solid #e1e5eb; margin-top: 10px; }
        .invoice-header { background-color: #f8fafc; padding: 25px 30px; border-bottom: 2px dashed #e1e5eb; text-align: center; }
        .invoice-header h2 { font-size: 22px; color: #2c3e50; text-transform: uppercase; margin-bottom: 5px; }
        .invoice-body { padding: 30px; }
        .data-table { width: 100%; border-collapse: collapse; text-align: left; border: 1px solid #e1e5eb; }
        .data-table th, .data-table td { padding: 12px 15px; border-bottom: 1px solid #edf2f9; font-size: 13px; color: #333; }
        .data-table th { background-color: #f8fafc; color: #5e6e82; font-weight: 600; text-transform: uppercase; font-size: 12px; }
        .fine-note { font-size: 14px; color: #e74c3c; font-style: italic; text-align: center; margin-top: 20px; }
        .success-note { font-size: 16px; color: #2ecc71; font-weight: bold; text-align: center; margin-top: 20px; }
        .invoice-footer { background-color: #f8fafc; padding: 20px 30px; border-top: 1px solid #e1e5eb; display: flex; justify-content: center; gap: 15px; }
        .btn-action { color: white; border: none; padding: 12px 25px; font-size: 14px; font-weight: bold; border-radius: 4px; cursor: pointer; transition: 0.3s; display: inline-flex; align-items: center; gap: 8px; }
        .btn-back { background-color: #7f8c8d; } .btn-back:hover { background-color: #636e72; }
        .btn-print { background-color: #3498db; } .btn-print:hover { background-color: #2980b9; }
        .btn-submit { background-color: #4CAF50; } .btn-submit:hover { background-color: #45a049; }
        .btn-pay { background-color: #e74c3c; } .btn-pay:hover { background-color: #c0392b; }
        .btn-finish { background-color: #2c3e50; } .btn-finish:hover { background-color: #1a252f; }
        @media print {
            .sidebar, .top-header, .invoice-footer { display: none !important; }
            .main-content { background-color: #fff; }
            .invoice-card { box-shadow: none; border: none; margin: 0 auto; }
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
            <li><a href="GDChinhNV.jsp"><i class="fas fa-home"></i> Trang chủ</a></li>
            <li class="active"><a href="NhanTraController"><i class="fas fa-exchange-alt"></i> Nhận trả tài liệu</a></li>
        </ul>
    </div>

    <div class="main-content">
        <div class="top-header">
            <div class="user-info">Xin chào, <span>${sessionScope.user.hoVaTen}</span></div>
            <a href="LogoutController" class="btn-action" style="background: rgba(255,255,255,0.2);"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
        </div>

        <div class="content-body">
            <c:if test="${not empty errorMsg}">
                <div style="color: red; margin-bottom: 15px; font-weight: bold;"><i class="fas fa-exclamation-triangle"></i> ${errorMsg}</div>
            </c:if>

            <div class="invoice-card">
                <div class="invoice-header">
                    <h2>
                        <c:choose>
                            <c:when test="${sessionScope.phase == 'preview'}">PHIẾU TRẢ TÀI LIỆU </c:when>
                            <c:otherwise>PHIẾU TRẢ TÀI LIỆU (Mã: PT${sessionScope.maPhieuTra})</c:otherwise>
                        </c:choose>
                    </h2>
                    <p>Bạn đọc: <strong>${theBanDoc.banDoc.hoVaTen}</strong> | Mã thẻ: <strong>${theBanDoc.maThe}</strong></p>
                    <p>Ngày lập: <%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date()) %></p>
                </div>

                <div class="invoice-body">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Mã bản sao</th>
                                <th>Tên tài liệu</th>
                                <th>Ngày mượn</th>
                                <th>Hạn trả</th>
                                <th>TT Mượn</th>
                                <th>TT Trả</th>
                                <th style="text-align: right;">Phạt chậm</th>
                                <th style="text-align: right;">Phạt hỏng</th>
                                <th style="text-align: right;">Cộng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sessionScope.dsHoaDon}">
                                <tr>
                                    <td><strong>${item.maBanSao}</strong></td>
                                    <td>${item.tenTaiLieu}</td>
                                    <td>${item.ngayMuon}</td>
                                    <td>${item.hanTra}</td>
                                    <td>${item.tinhTrangMuon}</td>
                                    <td><strong>${item.tinhTrangTra}</strong></td>
                                    <td style="text-align: right;"><fmt:formatNumber value="${item.phatMuon}" type="number" groupingUsed="true"/> đ</td>
                                    <td style="text-align: right;"><fmt:formatNumber value="${item.phatHong}" type="number" groupingUsed="true"/> đ</td>
                                    <td style="text-align: right; color: #d35400; font-weight: bold;"><fmt:formatNumber value="${item.tongPhat}" type="number" groupingUsed="true"/> đ</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr style="background-color: #f8fafc;">
                                <td colspan="5" style="text-align: left; font-weight: bold; color: #2c3e50;">TỔNG CỘNG:</td>
                                <td colspan="3" style="text-align: right; font-weight: bold; color: #2980b9;">${sessionScope.tongSoQuyen} quyển</td>
                                <td style="text-align: right; font-weight: bold; font-size: 15px; color: #c0392b;">
                                    <fmt:formatNumber value="${sessionScope.tongTienPhat}" type="number" groupingUsed="true"/> VNĐ
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <c:if test="${sessionScope.phase == 'confirmed'}">
                        <c:if test="${sessionScope.tongTienPhat > 0}">
                            <c:choose>
                                <c:when test="${sessionScope.daThanhToan == true}">
                                    <p class="success-note"><i class="fas fa-check-circle"></i> Nhận trả tài liệu thành công. Đã thanh toán tiền phạt.</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="fine-note"><i class="fas fa-exclamation-triangle"></i> Tiền phạt đã được cộng vào Công Nợ. Vui lòng thanh toán nếu bạn đọc có tiền mặt.</p>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${sessionScope.tongTienPhat == 0}">
                            <p class="success-note"><i class="fas fa-check-circle"></i> Nhận trả tài liệu thành công.</p>
                        </c:if>
                    </c:if>
                </div>

                <div class="invoice-footer">
                    <c:if test="${sessionScope.phase == 'preview'}">
                        <button type="button" class="btn-action btn-back" onclick="history.back()">
                            <i class="fas fa-arrow-left"></i> Quay lại
                        </button>
                        <form action="NhanTraController" method="POST" style="margin: 0;">
                            <input type="hidden" name="action" value="xacNhan" />
                            <button type="submit" class="btn-action btn-submit"><i class="fas fa-check"></i> Xác nhận</button>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.phase == 'confirmed'}">
                        <button type="button" class="btn-action btn-print" onclick="window.print()">
                            <i class="fas fa-print"></i> In phiếu trả
                        </button>

                        <c:if test="${sessionScope.tongTienPhat > 0 && empty sessionScope.daThanhToan}">
                            <form action="NhanTraController" method="POST" style="margin: 0;">
                                <input type="hidden" name="action" value="thanhToan" />
                                <button type="submit" class="btn-action btn-pay"><i class="fas fa-money-bill-wave"></i> Thanh toán ngay</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.daThanhToan == true}">
                            <button type="button" class="btn-action btn-print" onclick="window.print()">
                                <i class="fas fa-file-invoice-dollar"></i> In hóa đơn phạt
                            </button>
                        </c:if>
                        <form action="NhanTraController" method="POST" style="marg*in: 0;">
                            <input type="hidden" name="action" value="hoanTat" />
                            <button type="submit" class="btn-action btn-finish"><i class="fas fa-check-double"></i> Hoàn tất</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>