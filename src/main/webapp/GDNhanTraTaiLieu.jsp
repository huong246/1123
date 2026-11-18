<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhận Trả Tài Liệu</title>
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
        .content-body h1 { font-size: 24px; color: #333; text-transform: uppercase; margin-bottom: 5px; }
        .search-container { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); border: 1px solid #e1e5eb; margin-bottom: 30px; }
        .search-group { display: flex; gap: 15px; max-width: 650px; }
        .search-input { flex: 1; padding: 12px 15px; font-size: 15px; border: 1px solid #ccc; border-radius: 4px; outline: none; }
        .btn-search { background-color: #FF9800; color: white; border: none; padding: 0 25px; font-size: 15px; font-weight: bold; border-radius: 4px; cursor: pointer; transition: 0.3s; }
        .btn-search:hover { background-color: #e68a00; }
        .btn-reset { background-color: #3c8dbc; color: white; border: none; padding: 0 20px; font-size: 15px; font-weight: bold; border-radius: 4px; cursor: pointer; transition: 0.3s; text-decoration: none; display: flex; align-items: center; justify-content: center; }
        .btn-reset:hover { background-color: #357ca5; }
        .btn-reset i { margin-right: 5px; }
        .server-error { color: #e74c3c; margin-top: 10px; font-size: 14px; display: ${not empty errorMsg ? 'block' : 'none'}; }
        .reader-details-card { background-color: #fff; border-left: 4px solid #3c8dbc; padding: 20px 25px; border-radius: 6px; margin-bottom: 25px; box-shadow: 0 2px 10px rgba(0,0,0,0.05); border-top: 1px solid #e1e5eb; border-right: 1px solid #e1e5eb; border-bottom: 1px solid #e1e5eb; }
        .reader-details-card h3 { font-size: 16px; color: #2c3e50; margin-bottom: 15px; text-transform: uppercase; border-bottom: 1px dashed #ccc; padding-bottom: 10px; }
        .reader-grid { display: grid; grid-template-columns: repeat(2, 1fr); row-gap: 12px; column-gap: 30px; }
        .detail-item { font-size: 14px; color: #444; }
        .detail-item i { color: #7f8c8d; width: 20px; text-align: center; margin-right: 5px; }
        .detail-item strong { color: #2c3e50; font-weight: 600; }
        .table-container { background: #ffffff; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); border: 1px solid #e1e5eb; overflow: hidden; margin-bottom: 25px; }
        .data-table { width: 100%; border-collapse: collapse; text-align: left; }
        .data-table th, .data-table td { padding: 12px 15px; border-bottom: 1px solid #edf2f9; font-size: 13px; color: #333; vertical-align: middle; }
        .data-table th { background-color: #f8fafc; color: #5e6e82; font-weight: 600; text-transform: uppercase; font-size: 12px; }
        .data-table tr:hover { background-color: #fcfdfe; }
        input[type="checkbox"] { width: 16px; height: 16px; cursor: pointer; }
        .select-status { padding: 6px 8px; border: 1px solid #ccc; border-radius: 4px; font-size: 13px; outline: none; width: 100%; max-width: 140px; }
        .fine-amount { font-weight: bold; color: #e74c3c; }
        .btn-wrapper { text-align: right; }
        .btn-submit { background-color: #4CAF50; color: white; border: none; padding: 12px 30px; font-size: 15px; font-weight: bold; border-radius: 4px; cursor: pointer; transition: 0.3s; }
        .btn-submit:hover { background-color: #45a049; }
        .btn-submit:disabled { background-color: #95a5a6; cursor: not-allowed; }
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
            <div class="user-info">Xin chào Nhân viên, <span>${sessionScope.user.hoVaTen}</span></div>
            <a href="LogoutController" class="btn-logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
        </div>

        <div class="content-body">
            <h1>NHẬN TRẢ TÀI LIỆU</h1>

            <div class="search-container">
                <form action="NhanTraController" method="GET">
                    <input type="hidden" name="action" value="search" />
                    <div class="search-group">
                        <input type="text" name="maBanDoc" class="search-input" value="${param.maBanDoc}" placeholder="Nhập Mã Bạn Đọc / Mã Thẻ..." required autofocus />
                        <button type="submit" class="btn-search"><i class="fas fa-barcode"></i> Tìm kiếm/quét thẻ</button>
                        <a href="NhanTraController" class="btn-reset" title="Làm mới"><i class="fas fa-redo"></i> Làm lại</a>
                    </div>
                </form>
                <div class="server-error"><i class="fas fa-exclamation-triangle"></i> ${errorMsg}</div>
            </div>

            <c:if test="${not empty theBanDoc}">
                <div class="reader-details-card">
                    <h3>Thông tin thẻ mượn</h3>
                    <div class="reader-grid">
                        <div class="detail-item"><i class="fas fa-user"></i> Họ và tên: <strong>${theBanDoc.banDoc.hoVaTen}</strong></div>
                        <div class="detail-item"><i class="fas fa-id-card"></i> Mã thẻ: <strong>${theBanDoc.maThe}</strong></div>
                        <div class="detail-item"><i class="fas fa-venus-mars"></i> Giới tính: <strong>${theBanDoc.banDoc.gioiTinh}</strong></div>
                        <div class="detail-item"><i class="far fa-calendar-alt"></i> Hiệu lực: <strong>${theBanDoc.ngayDangKy}</strong> đến <strong>${theBanDoc.ngayHetHan}</strong></div>
                        <div class="detail-item"><i class="fas fa-graduation-cap"></i> Khoa: <strong>${theBanDoc.banDoc.khoa}</strong></div>
                        <div class="detail-item"><i class="fas fa-chalkboard"></i> Lớp: <strong>${theBanDoc.banDoc.lop}</strong></div>
                        <div class="detail-item"><i class="fas fa-envelope"></i> Email: <strong>${theBanDoc.banDoc.email}</strong></div>
                        <div class="detail-item"><i class="fas fa-phone-alt"></i> Điện thoại: <strong>${theBanDoc.banDoc.dienThoai}</strong></div>
                        <div class="detail-item" style="grid-column: span 2; margin-top: 5px; padding-top: 10px; border-top: 1px dashed #ccc;">
                         <i class="fas fa-file-invoice-dollar" style="color: #e74c3c;"></i> Công nợ hiện tại:
                          <strong style="font-size: 16px;">
                          <c:choose>
                          <c:when test="${theBanDoc.banDoc.congNo > 0}">
                              <span style="color: #e74c3c;">- <fmt:formatNumber value="${theBanDoc.banDoc.congNo}" type="number" groupingUsed="true"/> VNĐ</span>
                           </c:when>
                           <c:otherwise>
                           <span style="color: #2ecc71;">0 VNĐ</span>
                           </c:otherwise>
                           </c:choose>
                           </strong>
                           </div>
                    </div>
                </div>

                <form action="NhanTraController" method="POST" id="nhanTraForm">
                    <input type="hidden" name="action" value="taoPhieu" />
                    <div class="table-container">
                        <table class="data-table" id="tableMuonTra">
                            <thead>
                                <tr>
                                    <th style="text-align: center; width: 40px;">TT</th>
                                    <th style="text-align: center; width: 40px;"></th>
                                    <th>Mã bản sao</th>
                                    <th>Tên tài liệu</th>
                                    <th>Ngày mượn</th>
                                    <th>Hạn trả</th>
                                    <th>Tình trạng khi mượn</th>
                                    <th>Tình trạng khi trả</th>
                                    <th>Tiền phạt</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${dsMuon}" varStatus="status">
                                    <tr data-row-id="${item.id}">
                                        <td style="text-align: center;">${status.index + 1}</td>
                                        <td style="text-align: center;">
                                            <input type="checkbox" name="ctMuonId" value="${item.id}" class="check-item" />
                                        </td>
                                        <td><strong>${item.banSao.maBanSao}</strong></td>
                                        <td>${item.banSao.taiLieu.ten}</td>
                                        <td>${item.phieuMuon.ngayMuon}</td>
                                        <td class="han-tra">${item.hanTra}</td>
                                        <td class="tinh-trang-muon" data-price="${item.banSao.taiLieu.gia}">${item.tinhTrangMuon}</td>
                                        <td>
                                            <select name="tinhTrang_${item.id}" class="select-status" onchange="calculateFine(this, ${item.id})">
                                                <option value="Bình thường" ${item.tinhTrangMuon == 'Bình thường' ? 'selected' : ''}>Bình thường</option>
                                                <option value="Bẩn" ${item.tinhTrangMuon == 'Bẩn' ? 'selected' : ''}>Bẩn</option>
                                                <option value="Rách bìa" ${item.tinhTrangMuon == 'Rách bìa' ? 'selected' : ''}>Rách bìa</option>
                                                <option value="Rách trang" ${item.tinhTrangMuon == 'Rách trang' ? 'selected' : ''}>Rách trang</option>
                                                <option value="Mất">Mất</option>
                                            </select>
                                        </td>
                                        <c:set var="tienPhatHienTai" value="${not empty mapPhatBanDau[item.id] ? mapPhatBanDau[item.id] : 0}" />

                                        <td class="fine-amount" id="fine_${item.id}" data-base-fine="${tienPhatHienTai}">
                                            <fmt:formatNumber value="${tienPhatHienTai}" type="number" groupingUsed="true"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty dsMuon}">
                                    <tr>
                                        <td colspan="9" style="text-align: center; padding: 30px; color: #7f8c8d;">
                                            Bạn đọc này hiện không có tài liệu nào đang mượn.
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <div class="btn-wrapper">
                        <button type="submit" class="btn-submit" id="btnSubmit" ${empty dsMuon ? 'disabled' : ''}>
                            <i class="fas fa-file-invoice"></i> Tạo phiếu trả
                        </button>
                    </div>
                </form>
            </c:if>
        </div>
    </div>

    <script>
        function calculateFine(selectElement, rowId) {
            const row = document.querySelector(`tr[data-row-id="${rowId}"]`);
            const tinhTrangMuon = row.querySelector('.tinh-trang-muon').textContent.trim();
            const giaSach = parseFloat(row.querySelector('.tinh-trang-muon').getAttribute('data-price')) || 0;
            const tinhTrangTra = selectElement.value;
            const fineCell = document.getElementById('fine_' + rowId);

            let baseFine = parseFloat(fineCell.getAttribute('data-base-fine')) || 0;
            let totalFineAmount = baseFine;

            if (tinhTrangTra !== tinhTrangMuon) {
                if (tinhTrangTra === 'Bẩn') totalFineAmount += (giaSach * 0.05);
                else if (tinhTrangTra === 'Rách bìa') totalFineAmount += (giaSach * 0.10);
                else if (tinhTrangTra === 'Rách trang') totalFineAmount += (giaSach * 0.20);
                else if (tinhTrangTra === 'Mất') totalFineAmount += giaSach;
            }

            fineCell.textContent = new Intl.NumberFormat('vi-VN').format(totalFineAmount);
            row.querySelector('.check-item').checked = true;
        }

        document.getElementById('nhanTraForm')?.addEventListener('submit', function(e) {
            const checkedBoxes = document.querySelectorAll('.check-item:checked');
            if (checkedBoxes.length === 0) {
                e.preventDefault();
                alert("Vui lòng chọn ít nhất một tài liệu cần trả!");
            }
        });

        window.onload = function() {
            const selects = document.querySelectorAll('.select-status');
            selects.forEach(select => {
                const rowId = select.closest('tr').getAttribute('data-row-id');
                const checkStatus = select.closest('tr').querySelector('.check-item').checked;
                calculateFine(select, rowId);
                select.closest('tr').querySelector('.check-item').checked = checkStatus;
            });
        };
    </script>
</body>
</html>