<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sách</title>
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
            text-transform: uppercase;
        }
        .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 25px;
            position: relative;
        }
        .form-row label {
            width: 120px;
            font-size: 18px;
        }
        .form-row input, .form-row select {
            flex: 1;
            height: 35px;
            border: none;
            padding: 0 10px;
            font-size: 16px;
            outline: none;
        }
        .form-row select {
            appearance: auto;
            background-color: white;
            cursor: pointer;
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
            padding: 10px 30px;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-cancel {
            background-color: #f7cece;
            border: none;
            padding: 10px 25px;
            font-size: 16px;
            cursor: pointer;
        }
        .error {
            color: red;
            font-size: 0.85em;
            position: absolute;
            right: -140px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>UNG DUNG NHAP SACH</h2>

        <form action="addBook" method="post">
           <div class="form-row">
               <label>bookcode</label>
               <input type="text" name="bookcode" value="${not empty requestScope.bookcode ? requestScope.bookcode : param.bookcode}">
               <span class="error">${errorBookcode}</span>
           </div>
           <div class="form-row">
               <label>title</label>
               <input type="text" name="title" value="${not empty requestScope.title ? requestScope.title : param.title}">
               <span class="error">${errorTitle}</span>
           </div>
           <div class="form-row">
               <label>author</label>
               <input type="text" name="author" value="${not empty requestScope.author ? requestScope.author : param.author}">
               <span class="error">${errorAuthor}</span>
           </div>
           <div class="form-row">
               <label>category</label>
               <select name="category">
                   <option value="Comic" ${sessionScope.savedCategory == 'Comic' ? 'selected' : ''}>Comic</option>
                   <option value="Novel" ${sessionScope.savedCategory == 'Novel' ? 'selected' : ''}>Novel</option>
                   <option value="Science" ${sessionScope.savedCategory == 'Science' ? 'selected' : ''}>Science</option>
               </select>
               <span class="error">${errorCategory}</span>
           </div>

            <div class="btn-row">
                <button type="submit" class="btn-action">Add</button>
                <button type="button" class="btn-cancel" onclick="window.location.href='resetBook'">Reset</button>
            </div>
        </form>
    </div>
</body>
</html>