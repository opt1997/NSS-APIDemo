<html>
<head>
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.js">
    </script>
    <script type="application/javascript">
        $(function () {
            var data ={};
            data["clientId"] = "46d44df3";
            data["clientSecret"] = "a7316346-12c8-46a5-b535-ab0409c20620";
            var tokenid2 =null;
                $.ajax({
                    url:'http://172.16.127.100:8080/nss-cloud2-api/api/v2/auth/get_token',
                    type:'post',
                    dataType:'json',
                    contentType: 'application/json; charset=UTF-8',
                    data:JSON.stringify(data),
                    success:function(data,status,req){
                        tokenid2 =  req.getResponseHeader('accessToken');
                       console.log(data)
                       $('#token').val(tokenid2)
                        $('#token2').val(tokenid2)
                    }
                });
            });
    </script>
</head>
<body>
    <h1>Hello World!</h1>
    <form action="/springdemo/home/index" method="post">
        <input id="token" name="token" type="hidden">
        <input id="btn" type="submit" value="Go To Upload_file">
    </form>
    <form action="/springdemo/home/index2" method="post">
        <input id="token2" name="token" type="hidden">
        <input id="btn2" type="submit" value="Go To upload_face_image">
    </form>
</body>
</html>
