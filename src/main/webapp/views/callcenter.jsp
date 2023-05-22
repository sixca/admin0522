<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    #to {
        width: 400px;
        height: 200px;
        overflow: auto;
        border: 2px solid green;
    }
</style>

<script>
    let callcenter = {
        id:null,
        stompClient:null,
        init:function(){
            this.id = $('#adm_id').text();
            $("#connect").click(function() {
                callcenter.connect();
            });
            $("#disconnect").click(function() {
                callcenter.disconnect();
            });
            $("#sendto").click(function() {
                callcenter.sendTo();
            });
        },
        connect:function(){
            var sid = this.id;
            var socket = new SockJS('${adminserver}/ws');  // 웹소켓 접속정보
            this.stompClient = Stomp.over(socket);  // 이 소켓을 넣어주고

            this.stompClient.connect({}, function(frame) {   //접속 시도
                callcenter.setConnected(true);
                console.log('Connected: ' + frame);
                this.subscribe('/send/to/'+sid, function(msg) {
                    $('#target').val(JSON.parse(msg.body).sendid); // 메세지 수신 시, 해당 발신자 id를 input태그에 박기.
                    $("#to").prepend(
                        "<h4>" + JSON.parse(msg.body).sendid +":"+
                        JSON.parse(msg.body).content1
                        + "</h4>");
                });
            });
        },
        disconnect:function(){
            if (this.stompClient !== null) {
                this.stompClient.disconnect();
            }
            callcenter.setConnected(false);
            console.log("Disconnected");
        },
        setConnected:function(connected){
            if (connected) {
                $("#status").text("Connected");
            } else {
                $("#status").text("Disconnected");
            }
        },
        sendTo:function(){
            var msg = JSON.stringify({
                'sendid' : this.id,
                'receiveid' : $('#target').val(),
                'content1' : $('#totext').val()
            });
            this.stompClient.send('/receiveto', {}, msg);
        }
    };
    $(function(){
        callcenter.init();
    })

</script>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">1:1 Call Center</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">1:1 Call Center</h6>
        </div>
        <div class="card-body">
            <div id="container"></div>
            <div class="col-sm-5">
                <h1 id="adm_id">${loginadm.id}</h1>
                <H1 id="status">Status</H1>
                <button id="connect">Connect</button>
                <button id="disconnect">Disconnect</button>

                <h3>To</h3>
                <input type="text" id="target">
                <input type="text" id="totext"><button id="sendto">Send</button>
                <div id="to"></div>
            </div>
        </div>
    </div>
    <!-- /.container-fluid -->
</div>