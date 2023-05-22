//login
let login_form={
    init:function (){
        $('#login_btn').click(function(){
            login_form.send();
        });
    },
    send:function (){
        $('#login_form').attr({
            'action':'/loginimpl',      //MainController에 보내는 Action
            'method':'post'
        });
        $('#login_form').submit();
    }
};

//register
let register_form={
    init:function (){
        $('#register_btn').click(function(){
            register_form.send();
        });
    },
    send:function (){
        $('#register_form').attr({
            'action':'/registerimpl',      //MainController에 보내는 Action
            'method':'post'
        });
        $('#register_form').submit();
    }
};

//jsp01

let jsp01 = {
    data:0,
    init:function(num){
    this.data = num;
    // this.data = '<c:out values="${num}"/>';
    this.display();
    },
    display:function(){
        $('#jsp01').text(this.data+'');
    }
};
