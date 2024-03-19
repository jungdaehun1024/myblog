let index = {
	init:function(){
	$("#btn-msg-save").on("click",()=>{
            this.save();
        });
	},
	
	save:function(){
		let data = {
			title: $("#title").val(),
			content:$("#content").val(),
			senderId:$("#senderId").val(),
			receiverName:$("#receiverName").val(),
		 	senderName:$("#senderName").text()
		}
		
		$.ajax({
			type:"POST",
			url:`/message/send`,
			data:JSON.stringify(data),    //data객체를 Json형식의 문자열로                                 
            contentType:"application/json;charset=utf-8", //Json타입을 요청으로 
            dataType:"json" // 서버는 Json타입의 데이터를 받는다.
		}).done(function(){
			console.log(senderId);
			alert("발송 완료")
			location.href = "/"
		}).fail(function(){
			alert("발송 실패!")
		})
	}
	

	
	
}

// 이벤트 핸들러를 설정
index.init();