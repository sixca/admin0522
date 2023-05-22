<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>

<style>
    #map{
        width : 400px;
        height: 400px;
        border: 2px solid red;
    }
</style>

<script>
    let marker_detail_map = {
        map:null,
        init:function (){   //카카오 개발자 가이드에서 퍼옴
            var mapContainer = document.querySelector('#map');  //getElementById 대신 querySelector 씀
            var mapOption = {
                center: new kakao.maps.LatLng(${gmarker.lat}, ${gmarker.lng}), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
            map =  new kakao.maps.Map(mapContainer, mapOption) //지도 뿌리기 :: #map에 뿌림.

            // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
            var mapTypeControl = new kakao.maps.MapTypeControl();
            // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
            // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
            map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
            // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
            var zoomControl = new kakao.maps.ZoomControl();
            map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

            // 마커가 표시될 위치입니다
            var markerPosition  = new kakao.maps.LatLng(${gmarker.lat}, ${gmarker.lng});
            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                position: markerPosition
            });
            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);
            // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
            var iwContent = '<img src="/uimg/${gmarker.img}" style="width:60px text-align: center"><div style="padding:5px;">Hello World!</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                content : iwContent
            });
            // 마커에 마우스오버 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                infowindow.open(map, marker);
            });
            // 마커에 마우스아웃 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseout', function() {
                // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                infowindow.close();
            });
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커를 클릭하면 이동
                location.href='${gmarker.target}';
            });
        }
    };

    let marker_detail={
        init: function(){
            $('#update_btn').click(function(){
                marker_detail.send();
            });
            $('#delete_btn').click(function(){
                var c = confirm('삭제 하시겠습니까?');   //confirm창 호출
                if(c == true){
                    location.href='/marker/deleteimpl?id=${gmarker.id}';
                };
            });
        },
        send: function(){
            $('#detail_form').attr({
                method:'post',
                action:'/marker/updateimpl',
                enctype: 'multipart/form-data'   // imgname <-> img파일
            });
            $('#detail_form').submit();
        }
    };
    $(function (){
        marker_detail.init();
        marker_detail_map.init();
    });
</script>

    <div class="container-fluid">

        <!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800">Detail Item : ${gmarker.id}</h1>

        <!-- DataTales Example -->
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">${gmarker.id} : ${gmarker.title}</h6>
            </div>
            <div class="card-body">
                    <form id="detail_form" class="form-horizontal well">
                        <input type="hidden" name="id" value="${gmarker.id}">
                        <input type="hidden" name="img" value="${gmarker.img}">
                        <%--폼과 폼사이 데이터는 날려야 하니 hidden 타입으로.--%>
                        <%-- 이미지가 새롭게 업데이트 되면 바꿔주고, 안 바뀌면 그대로 쓰겠다 --%>

                        <div class="form-group">
                            <div class="col-sm-10">
                                <img src="/uimg/${gmarker.img}" style="width:50px; height:50px">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="title">TITLE:</label>
                            <div class="col-sm-10">
                                <input type="text" name="title" class="form-control" id="title" value="${gmarker.title}">
                            </div>
                        </div>
<%--          target, lat, lnt, img, loc              --%>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="target">TARGET:</label>
                            <div class="col-sm-10">
                                <input type="text" name="target" class="form-control" id="target" value="${gmarker.target}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="lat">Latitude:</label>
                            <div class="col-sm-10">
                                <input type="number" name="lat" class="form-control" id="lat" value="${gmarker.lat}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="lng">Longtitude:</label>
                            <div class="col-sm-10">
                                <input type="number" name="lng" class="form-control" id="lng" value="${gmarker.lng}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="img">IMG:</label>
                            <div class="col-sm-10">
                                <input type="file" name="img" class="form-control" id="img" placeholder="Input img">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="loc">LOCATION:</label>
                            <div class="col-sm-10">
                                <input type="text" name="loc" class="form-control" id="loc" value="${gmarker.loc}">
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label col-sm-2" for="rdate">RDATE:</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" name="rdate" class="form-control" id="rdate"--%>
<%--                                       value="<fmt:formatDate value="${iteminfo.rdate}" pattern="yyyy-MM-dd"/>" readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="control-label col-sm-2" for="rdate">RDATE:</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" name="rdate" class="form-control" id="rdate"--%>
<%--                                       value="<fmt:formatDate value='${new Date()}' pattern='yyyy-MM-dd' />" readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button id="update_btn" type="button" class="btn btn-default">Update</button>
                                <button id="delete_btn" type="button" class="btn btn-default">Delete</button>
                            </div>
                        </div>
                    </form>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-10" id="map">

                        </div>
                </div>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container-fluid -->
</div>