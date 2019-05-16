调用API示例程序

项目主要结构及作用:
    main-->
         --CallbackAPIDemo
	 --DeviceAPIDemo
	 --GroupAPIDemo
	 --MemberAPIDemo
	 --PermissionAPIDemo
	 --VisAPIDemo
    pojo-->
         --Data
         --Result
    utils-->
         --SendAPIUtil：调用TakeTokenUtil获取token（如果需要）,再调用PostSendUtil发送请求
         --PostSendUtil：把API用post请求给服务器
         --TakeTokenUtil：获取token

调用接口的一般步骤：
	public static void demo(String token){
		url//设置需要访问接口的url相对地址

		headerMap//创建Header参数map
		headerMap.put("属性名","属性值")
		.....

		reqMap//创建请求参数map
		reqMap.put("属性名","属性值")
		.....

		SendAPIUtil.sendAPI(url,reqMap,headerMap,token);//调用工具类发送请求
	}

	public static void main(String token){
		String token = TakeTokenUtil.getToken(..);//调用工具类获取token
		demo(token);
	}