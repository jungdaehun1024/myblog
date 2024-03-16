let index={
    init:function(){
		//id를 btn-save로 가진 HTML요소가 클릭됐을 때 save함수를 실행시킨다.
        $("#btn-save").on("click",()=>{
            this.save();
        });
        //id를 btn-update로 가진 HTML요소가 클릭됐을 때 update함수를 실행시킨다.
        $("#btn-update").on("click",()=>{
            this.update();
        });
        
        //id를 btn-delete로 가진 HTML요소가 클릭됐을 때 deleteById함수를 실행시킨다.
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
        
        //id를 btn-reply-save로 가진 HTML요소가 클릭됐을 때 reply-save함수를 실행시킨다.
        $("#btn-reply-save").on("click",()=>{
            this.replySave();
        });
         $("#btn-search").on("click",()=>{
            this.search();
        });
         $("#btn-like").on("click",()=>{
            this.likeSave();
        })
    },
   
    save: function(){
		
        let data = {
            title: $("#title").val(), // id가 title인 input태그의 값을 가져온다.
            content: $("#content").val(), //id가 content인 input태그의 값을 가져온다.
        };


        $.ajax({
          type:"POST",                                                   
          url:"/api/board",                                           
          data:JSON.stringify(data),   //JS객체를 JOSN문자열로                                  
          contentType:"application/json;charset=utf-8", // JSON타입을 요청으로 보낸다.
          dataType:"json" // 서버에서는 JSON타입의 데이터를 받는다.
        }).done(function(resp){ //요청 성공시 "글작성 완료됨"이 알러트 메시지로 나온다.
        alert("글작성이 완료됨");
        location.href="/"; //완료 후 인덱스 페이지로 이동 
        }).fail(function(error){  // 요청 실패했을 때는 error객체를 JSON형태로 알러트메시지로 보여줌
            alert(JSON.stringify(error));

       
        });
    },
    
    likeSave:function(){
		let data ={
			boardId:$("#id").text(),
			currentLike:$("#currentLike").text(),
		}
		
		$.ajax({
		  type:"POST",                                                 
          url:`/api/board/like/${data.boardId}`,                                           
          data:JSON.stringify(data),    //data객체를 Json형식의 문자열로                                 
          contentType:"application/json;charset=utf-8", //Json타입을 요청으로 
          dataType:"json" // 서버는 Json타입의 데이터를 받는다.
		}).done(function(response){
			  $("#currentLike").text(response.data);
		}).fail(function(){
			alert("실패");
		})
		
	},
    search: function(){
		let keyword = $("#search").val();
		if(keyword ===""){
			alert("검색어를 입력해주세요");  // 검색어를 입력하지 않고 검색버튼 클릭시 알러트
			return 0;
		}
		
		$.ajax({
			type:"GET",
			url:`/search/${keyword}`
		}).done(function(){
			 location.href = `/search/${keyword}`
		}).fail(function(){
			alert("게시글 찾기 실패")
		})
	},
    
     update: function(){

        let id = $("#id").val(); //게시글 번호(id)를 받아온다.
        let data = {
            title: $("#title").val(), // title을 받아온다.
            content: $("#content").val(), // content 를 받아온다.
        };


        $.ajax({
          type:"PUT",                                                 
          url:"/api/board/"+id,                                           
          data:JSON.stringify(data),    //data객체를 Json형식의 문자열로                                 
          contentType:"application/json;charset=utf-8", //Json타입을 요청으로 
          dataType:"json" // 서버는 Json타입의 데이터를 받는다.
        }).done(function(resp){
        alert("글수정이 완료됨");
        location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));

       
        });
    },
    deleteById: function(){
        let id = $("#id").text();
        
        $.ajax({
          type:"DELETE",                                                   
          url:"/api/board/"+id,                                           
          dataType:"json"
        }).done(function(resp){
        alert("글삭제 완료됨");
        location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));

       
        });
    },
         replySave: function(){

        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val(),
        };
        
       
          $.ajax({
          type:"POST",                                                   
          url:`/api/board/${data.boardId}/reply`,                                           
          data:JSON.stringify(data),                                    
          contentType:"application/json;charset=utf-8",
          dataType:"json"
        }).done(function(resp){
        alert("댓글작성이 완료됨");
        location.href=`/board/${data.boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));

       
        });
 
    },
           replyDelete: function(boardId,replyId){       
          $.ajax({
          type:"DELETE",                                                   
          url:`/api/board/${boardId}/reply/${replyId}`,                                                                              
          dataType:"json"
        }).done(function(resp){
        alert("댓글삭제 완료됨");
        location.href=`/board/${boardId}`;
        }).fail(function(error){
            alert(JSON.stringify(error));

       
        });
 
    },
 	    
      
 }


// 이벤트 핸들러를 설정
index.init();