<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script>
    <script type="application/javascript">
        $(function () {
            //alert('这里是文件上传');
            var token = $("#div0").innerText;
            console.log(token)
           // var token = 'dcb2b69b-da80-4477-811e-c6628dd0f095';
            //var token = session.getAttribute("token");
            var button = $('#btn');
            button.click(function () {
                var formData = new FormData($("#upload-form")[0]);
               // console.log(formData);
                $.ajax({
                    url:'http://172.16.127.100:8080/nss-cloud2-api/api/v2/upload_file',
                    type:'post',
                    //contentType: "application/json;charset=utf-8",
                    contentType:false,
                    processData:false,//这个很有必要，不然不行
                    //dataType:"json",
                    headers:{
                        "accessToken":token
                    },
                    data:formData,
                    xhrFields: {
                        withCredentials: true
                    },
                    success:function (data) {
                        alert(data);
                    },
                    error:function () {
                        alert("失败");
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h1>上传文件</h1>
    获得的token为：
    <div id="div0">
        <%= session.getAttribute("token")%>
    </div>
    <br>
    <form id="upload-form" enctype="multipart/form-data">
        <input name="updateFile" id="updateFile" type="file" />
        <input type="button" id="btn" value="上传">
    </form>

</body>
</html>
