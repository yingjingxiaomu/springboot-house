<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/3/15
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工页面</title>

    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/layui/css/layui.css"/>
    <script src="<%=request.getContextPath()%>/js/layui/layui.js"></script>
</head>
<body>
<div id="addDiv">
    <form class="layui-form" style="padding-top: 10px" id="addStaffForm" enctype="multipart/form-data">
     <input id="editId" type="hidden" name="id" value="${data.id}"/>
        <input id="editPhoto"  type="hidden"  value="${data.photo}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">员工名称</label>
            <div class="layui-input-block">
                <input type="text" id="editName" value="${data.name}" name="name" required  lay-verify="required" placeholder="请输入员工名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-block">
                <input type="text" id="editPhonenumer" value="${data.phonenumer}" name="phonenumer" required  lay-verify="required" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">登录账号</label>
            <div class="layui-input-block">
                <input type="text" id="editLoginnumber" value="${data.loginnumber}" name="loginnumber" required  lay-verify="required" placeholder="请输入登录账号" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">登录密码</label>
            <div class="layui-input-block">
                <input type="password" id="editPassword" value="${data.password}" name="password" required  lay-verify="required" placeholder="请输入登录密码" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">微信</label>
            <div class="layui-input-block">
                <input type="text" id="editWeixin" <%--value="${data.weixin}"--%>  value="https://u.wechat.com/MHDAclO1jLGjTInrPx_eGn8" name="weixin"  lay-verify="required" placeholder="请输入微信" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">

            <div id="demo2" class="layui-upload-list" style="padding-left: 110px">

            </div>
            <br>
            <div>
                <label class="layui-form-label">员工头像</label>
                <input  type="hidden" name="photo" id="imgs" value="${data.photo}">
                <button type="button" class="layui-btn" id="test2">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo" onclick="addBookButton()">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>


<script>






    var $;
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;

        upload.render({
            elem: '#test2'
            , url: '../staff/headImgUpload'
            , multiple: true
            , number: 3
            , size: 1024
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    var str = '<img style="width:180px;height:160px;border-style:solid;border-width:1px; border-color:black;"src="<%=request.getContextPath()%>'+result+'">';
                    $("#demo2").html(str);
                });
            }
            , done: function (res) {
                $("#imgs").val(res.path)

            }
            , allDone: function (obj) {

                console.log(obj)
            }
        });
    })

    //自认为perfect图片回显
    $ = layui.jquery;
    if($("#editId").val()!=""){
        var photo = $("#editPhoto").val();

        var str = '<img style="width:180px;height:160px;border-style:solid;border-width:1px; border-color:black;"src="<%=request.getContextPath()%>'+photo+'">';
        $("#demo2").html(str);
    }


    //添加
    function addBookButton() {
        $ = layui.jquery;
        $.ajax({
            url:"../staff/addStaffList",
            type:"post",
            data:$("#addStaffForm").serialize(),
            dateType:"text",
            success:function () {
                $("#editId").val("");
                location.href="<%=request.getContextPath()%>/staff/toStaffListPage"
            }
        })
    }

</script>

</body>
</html>
