let index={
    init:function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
    },
   
    save: function(){

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };


        //Ajax호출시 디폴트가 비동기 호출이다.
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 
        $.ajax({
          type:"POST",                                                   
          url:"/auth/joinProc",                                           
          data:JSON.stringify(data),                                    
          contentType:"application/json;charset=utf-8",
          dataType:"json"
        }).done(function(resp){
          if(resp.status === 500){
            alert("회원가입에 실패했습니다.");
          }else{
                  alert("회원가입이 완료됨");
                 location.href="/";
          }

        }).fail(function(error){
            alert(JSON.stringify(error));

       
        });
    },
    update:function(){
        
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };


        $.ajax({
          type:"PUT",                                                   
          url:"/user",                                           
          data:JSON.stringify(data),                                    
          contentType:"application/json;charset=utf-8",
          dataType:"json"
        }).done(function(resp){
        alert("수정이 완료됨");
        location.href="/";
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