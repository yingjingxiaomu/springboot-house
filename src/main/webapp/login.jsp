<%--
  Created by IntelliJ IDEA.
  User: xzl
  Date: 2018/1/22
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/html5css/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/html5css/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/html5css/css/component.css" />
    <!--[if IE]>
    <script src="<%=request.getContextPath()%>/js/html5css/js/html5.js"></script>
    <![endif]-->
    <script>
        function keyLogin(){
            if (event.keyCode==13){//回车键的键值为13
                        document.getElementById("loginButton").click(); //调用登录按钮的登录事件
            }
        }
    </script>
</head>
<body onkeydown="keyLogin()">

<form id="loginForm" >
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎您的登录</h3>
                <form action="#" name="f" method="post">
                    <table>
                        <tr>
                            <td>
                                <div class="input_outer">
                                    <span class="u_user"></span>
                                    <input name="loginnumber" id="loginnumber"class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
                                </div>
                            </td>
                        </tr>
                        <tr>
                        <td>
                            <div class="input_outer">
                                <span class="us_uer"></span>
                                <input name="password" id="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
                            </div>
                        </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="input_veri">
                                    <span class="us_veri"></span>
                                    <input name="verification" id="verification" class="textveru" style="color: #FFFFFF !important; position:absolute; z-index:50;"  placeholder="请输入验证码">
                                    <div  style="padding-left: 150px">
                                        <input type="button" class="act-but dxsubmit" id="button" onclick="getCheckNode()"value="获取验证码"/>
                                    </div>
                                </div>
                                    <%--<input type="button" id="button" onclick="getCheckNode()"  style="color: #000033" value="发送验证码"/>--%>

                            </td>
                        </tr>
                    </table>

                    <div class="mb2"><a class="act-but submit" id="loginButton" style="color: #FFFFFF">登录</a></div>
                </form>
            </div>
        </div>
    </div>
</div>
</form><!-- /container -->
<script src="<%=request.getContextPath()%>/js/html5css/js/TweenLite.min.js"></script>
<script src="<%=request.getContextPath()%>/js/html5css/js/EasePack.min.js"></script>
<script src="<%=request.getContextPath()%>/js/html5css/js/rAF.js"></script>
<script src="<%=request.getContextPath()%>/js/html5css/js/demo-1.js"></script>
<script>

    var count = 60;
    var time;
    var flag = true;

    function getCheckNode() {
        var buttonValue=$("#button").val();
        if(buttonValue=="获取验证码"){

            if($("#loginnumber").val()!=null&&$("#loginnumber").val()!=""){
                if(flag){
                    $.ajax({
                        url:"<%=request.getContextPath()%>/login/getVerification",
                        type:"post",
                        data:$("#loginForm").serialize(),
                        dataType:"json",
                        async:false,
                        success:function(data){
                            if(data.success){
                                //alert(data.msg);
                                $("#verification").val(data.msg);
                                time = window.setInterval('countdown()',1000)
                                flag = false;
                            }else{
                                /* $.messager.alert('警告!',data.msg);*/
                                alert(data.msg);
                            }
                        }

                    })


                }
            }else{
                alert("您还注册");
            }
        }

    }

    function countdown(){
        $('#button').val("倒计时"+--count+"s");
        if(count <= 0){
            $('#button').val("获取验证码");
            count = 60;
            flag = true;
            window.clearInterval(time);
        }
    }

    $("#loginButton").click(function(){
        $.ajax({
            url:"<%=request.getContextPath()%>/login/getlogin",
            type:"post",
            data:$("#loginForm").serialize(),
            dataType:"json",
            async:false,
            success:function(data){
                if(data.success){
                    //location.href="<%=request.getContextPath()%>/login/tologin";
                    location.href="<%=request.getContextPath()%>/index.jsp";
                }else{
                   alert(data.msg);
                }
            }
        })
    })

</script>



</body>
</html>
