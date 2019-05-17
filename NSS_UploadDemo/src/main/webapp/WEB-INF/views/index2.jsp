<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js">
    </script>
    <script type="application/javascript">
        $(function () {
           // alert('这里是人脸照片上传');
            var token = $("#token").val();
            //document.getElementById("div1").append(token) ;
            $('#btn').click(function () {
                var formData = new FormData($( "#upload-form" )[0]);
                console.log(formData);
                $.ajax({
                    url:'http://172.16.127.172:8081/nss-cloud2-api/api/v2/upload_face_image',
                    type:'post',
                    //contentType: "application/json;charset=utf-8",
                    contentType:false,
                    processData:false,
                    headers:{
                        "accessToken":token
                    },
                    data:formData,
                    xhrFields: {
                        withCredentials: true
                    },
                    success:function (data) {
                        var item = eval(data);
                        for (var i in item) {
                            if (item[i]=="-99999") {
                                alert("系统异常")
                            }else if(item[i]=="-13013"){
                                alert("检测不到人脸")
                            }
                            if(item[i]=="[object Object]") {
                                alert("文件id是：\n" + item[i].fileId);
                                document.getElementById("div1").append("文件id是：\n" + item[i].fileId);
                            }
                        }
                    },
                    error:function () {
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>上传人脸照片</h1>
    获得的token为：
    <div id="div0">
        <%= session.getAttribute("token") %>
    </div>
    <br>
    <%
       String s1 = session.getAttribute("token").toString();
    %>
    <form id="upload-form" enctype="multipart/form-data">
        <input name="updateFile" id="updateFile" type="file" />
        <input id="token" name="token" type= "hidden" value= <%=s1%> />
        <input type="button" id="btn" value="上传" />
    </form>
    <div id="div1">

    </div>
</body>
</html>
