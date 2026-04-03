<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xác nhận</title>
    <style>
        body {
            background-color: #f3ebda;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            padding-top: 50px;
            margin: 0;
        }
        .form-container { width: 450px; }
        h2 {
            text-align: center;
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 40px;
        }
        .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 25px;
        }
        .form-row label {
            width: 120px;
            font-size: 18px;
        }

        .value-box {
            flex: 1;
            height: 35px;
            background-color: white;
            border: none;
            padding: 0 10px;
            font-size: 16px;
            display: flex;
            align-items: center;
        }
        .btn-row {
            display: flex;
            margin-top: 35px;
            padding-left: 120px;
            gap: 50px;
        }
        .btn-action {
            background-color: #b2e0e0;
            border: none;
            padding: 10px 25px;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-cancel {
            background-color: #f7cece;
            border: none;
            padding: 10px 30px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Xac nhan thong tin da nhap</h2>

        <form action="confirmBook" method="post">
            <div class="form-row">
                <label>bookcode</label>
                <div class="value-box">${requestScope.bookcode}</div>
            </div>
            <div class="form-row">
                <label>title</label>
                <div class="value-box">${requestScope.title}</div>
            </div>
            <div class="form-row">
                <label>author</label>
                <div class="value-box">${requestScope.author}</div>
            </div>
            <div class="form-row">
                <label>category</label>
                <div class="value-box">${requestScope.category}</div>
            </div>

            <input type="hidden" name="bookcode" value="${requestScope.bookcode}">
            <input type="hidden" name="title" value="${requestScope.title}">
            <input type="hidden" name="author" value="${requestScope.author}">
            <input type="hidden" name="category" value="${requestScope.category}">

            <div class="btn-row">
                <button type="submit" name="action" value="confirm" class="btn-action">Confirm</button>
                <button type="submit" name="action" value="back" class="btn-cancel">Back</button>
            </div>
        </form>
    </div>
</body>
</html>