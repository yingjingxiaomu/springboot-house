<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表页面</title>

    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/layui/css/layui.css"/>
    <script src="<%=request.getContextPath()%>/js/layui/layui.js"></script>

</head>
<body>

   </div>
    <div id="pTable">
        <a class="layui-btn layui-btn-danger layui-btn-mini"  onclick="deleteAllStaff()">批量删除</a>
        <%--查询表格--%>
        <table class="layui-table" id="layui_table_id" lay-filter="test">
        </table>
        <div id="laypage"></div>
    </div>

   <%--二维码--%>
   <div id="detailsDiv" style="display: none">
       <br>
       <h2 align="center">请扫描下方二维码</h2>
       <div>
           <div style="width: 400px">
               <div style="padding-left:120px" id="demo2"></div>
           </div>
       </div>
   </div>

</body>

    <%--操作--%>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
    </script>
      <script type="text/html" id="wxbutton">
        <button type="button" lay-event="wxcode" class="layui-btn" >
            <i class="layui-icon">&#xe63a;</i>
        </button>
    </script>
    <script>



       //Demo
         layui.use('form', function(){
             var form = layui.form;

             //监听提交
             form.on('submit(formDemo)', function(data){
                 layer.msg(JSON.stringify(data.field));
                 return false;
             });
         });


        //----------------------------------------------------------------
        var limitcount = 7;
        var curnum = 1;
        //列表查询方法
        function productsearch(start,limitsize) {
            layui.use(['table','laypage','laydate'], function(){
                 table = layui.table,
                    laydate=layui.laydate,
                    laypage = layui.laypage;
                table.render({
                    elem: '#layui_table_id'
                    , url: '<%=request.getContextPath()%>/staff/queryStaffList?page='+start+'&limit=' + limitsize
                    /*, where:{pagename:start,pagelimit:limitsize} //传参*/

                    , cols: [[
                        {field: 'id',checkbox:true,title: '编号', width:60,align:'center'}
                        ,{field: 'name', title: '姓名', width:120,align:'center'}
                        ,{field: 'weixin', title: '微信',toolbar: '#wxbutton', width: 120,align:'center'}
                        ,{field: 'phonenumer', title: '联系方式', width: 170,align:'center'}
                        ,{field: 'loginnumber', title: '登录账号', width:150,align:'center'}
                        ,{field: 'password', title: '密码', width:150,align:'center'}

                        ,{field: 'photo', title: '图片', width: 100,
                            templet:"<div><img alt='图片不存在'  src='{{d.photo}}'></div>"
                            ,style:'height:50px;width:50px;line-height:50px;'}
                        ,{field: 'poperation', title: '操作', width:150,fixed: 'right', toolbar: '#barDemo'}



                    ]]
                    , page: false
                    //,fix:true
                    //,scrolling:false
                    , height: 445
                    ,done: function(res, curr, count){


                        $("[data-field='password']").children().each(function(){
                            if($(this).text().length>3){
                                $(this).text("********")
                            }

                        })

                        //如果是异步请求数据方式，res即为你接口返回的信息。
                        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                        laypage.render({
                            elem:'laypage'
                            ,count:count
                            ,curr:curnum
                            ,limit:limitcount
                            ,layout: ['prev', 'page', 'next', 'skip','count','limit']
                            ,jump:function (obj,first) {
                                if(!first){
                                    curnum = obj.curr;
                                    limitcount = obj.limit;
                                    productsearch(curnum,limitcount);
                                }
                            }
                        })
                    }
                })

                //监听工具条
                table.on('tool(test)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                    var data = obj.data //获得当前行数据
                        ,layEvent = obj.event; //获得 lay-event 对应的值
                    if(layEvent === 'detail'){
                        viewLableInfo(data.attrId);
                        layer.msg(data.attrId);
                        //通过监听删除
                    } else if(layEvent === 'del'){

                        layer.confirm("确认要删除吗，删除后不能恢复", { title: "删除确认" }, function (index) {
                            layer.close(index);
                            $.post("../staff/delStaffById?id="+data.id, function (data) {
                                location.reload();
                            });
                        });

                        productsearch(curnum,limitcount);

                       //展示二维码
                    }else if(layEvent == 'wxcode'){

                            showWXcode("微信二维码");
                            $.ajax({
                                url:"../staff/makeQRcode",
                                data:{"content":"<%=request.getContextPath()%>"+data.weixin,"id":data.id},
                                type:"post",
                                dataType:"json",
                                success:function(data){
                                    if(data.success){
                                        $('#demo2').html('<img src="' + data.serPath + '" width="260" height="260" alt="图片不存在">')
                                        alert(data.serPath)
                                    }else{
                                        alert(data.msg)
                                    }

                                },
                                error:function(){
                                    alert('失败!');
                                }

                            })


                    }
                    //通过监听修改
                    else if(layEvent === 'edit'){
                        location.href="<%=request.getContextPath()%>/staff/toAddStaff?id="+data.id;

                   }
                });
                //常规用法
                laydate.render({
                    elem: '#createDate'
                });
                //常规用法
                laydate.render({
                    elem: '#processingTime'
                });

            });
        }
        productsearch( curnum, limitcount);



            //批量删除
            function deleteAllStaff(){
                var ids = "";
                var count=0;
                var checkStatus = table.checkStatus('layui_table_id')
                    ,data = checkStatus.data;
                for (var i in data){
                    ids += "," + data[i].id;
                    count++;
                }

                if(ids == ""){
                    layer.msg("请选择需要删除的员工");
                }else{
                    layer.confirm("确认删除勾选的"+count+"位员工?", {icon: 3, title:"确认"}, function(){
                        ids = ids.substr(1);
                        $.ajax({
                            url:"../staff/delallStaffByIds",
                            type:"post",
                            data:{"ids":ids},
                            dataType:"json",
                            success:function(data){
                                if(data.success){
                                    layer.msg("删除成功!");
                                    window.location.href = location;
                                }else{
                                    layer.open({
                                        content: '删除失败'
                                        ,btn: '我知道了'
                                    });
                                }
                            }
                        })
                    }, function(){
                    });
                }
            }


       //生成二维码
        function showWXcode(msg) {
            $ = layui.jquery
            layui.use('layedit', function(){
                layedit = layui.layedit;
                indexs = layedit.build('info'); //建立编辑器
            });
            layer.open({
                type: 1,
                title: "员工"+msg, //不显示标题栏
                skin: 'layui-layer-demo', //样式类名
                closeBtn: 1, //不显示关闭按钮
                shift: 2,
                area: ['500px', '450px'], //宽高
                shadeClose: true, //开启遮罩关闭
                content:$("#detailsDiv"),
                scrollbar: false, // 父页面 滚动条 禁止
                btnAlign: 'c',
                yes: function(index, layero){
                    layedit.sync(indexs);

                },
                end: function () {
                    location.reload();
                },

            });
        }

    </script>
</html>
