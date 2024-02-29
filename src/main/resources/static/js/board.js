let index={
    init:function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
        
           $("#btn-reply-save").on("click",()=>{
            this.replySave();
        });
    },
   
    save: function(){

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };


        $.ajax({
          type:"POST",                                                   
          url:"/api/board",                                           
          data:JSON.stringify(data),                                    
          contentType:"application/json;charset=utf-8",
          dataType:"json"
        }).done(function(resp){
        alert("글작성이 완료됨");
        location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));

       
        });
    },
    
     update: function(){
        let id = $("#id").val();
        
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };


        $.ajax({
          type:"PUT",                                                   
          url:"/api/board/"+id,                                           
          data:JSON.stringify(data),                                    
          contentType:"application/json;charset=utf-8",
          dataType:"json"
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


// index객체 내에 
// init on함수를 이용해서 #btn-save를 클릭시 save함수를 호출

// save함수
// data 객체 안에 username,password,emial을 joinForm.jsp의 form태그에 있는 username,password,email값을 가져와 매핑
index.init();