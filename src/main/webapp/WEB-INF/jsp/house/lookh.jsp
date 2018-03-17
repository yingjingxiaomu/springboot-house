<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>

    <style type="text/css">.layui-table-fixed-r td{height:30px!important;}
    .layui-table-fixed-r th{height:30px!important;}
    .layui-table img {
        max-width: 30px;min-height: 30px;
    }
    </style>
</head>
<body>


</div>
<div id="pTable">
    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;<a class="layui-btn layui-btn-danger layui-btn-mini"  onclick="deleteAllHouse()">批量删除</a>
    <%--查询表格--%>
    <table class="layui-table" id="layui_table_id" lay-filter="test">
    </table>
    <div id="laypage"></div>
</div>


</body>

<%--操作--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-sm" lay-event="editDetails">房源详细信息</a>
</script>
<script src="../js/layui/layui.js"></script>
<script type="text/javascript">




    //-----手风琴----------------------------

    //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
    layui.use(['element','form'], function(){
        var element = layui.element;


    });

    //-------------跳转详情方法 并且传id查出该id的说有数据----------------------




//----------条件查询----------------------------------
    layui.use(['table','form','laydate'], function(){
        var table = layui.table,
            laydate = layui.laydate;

        var form = layui.form;
        form.on('select(province)', function (data) {
            //加载市
            $.ajax({
                url:"../conditional/selectProvince",
                type:"post",
                data:{"pid":data.value},
                dataType:"json",
                async:false,
                success:function(cityData){
                    var  province= '<option value="-1">请选择市</option>';
                    $.each(cityData,function(){
                        province += '<option value="'+this.id+'">'+this.name+'</option>'
                    })
                    $("#city").html(province);
                    form.render('select');
                }
            })
        });
        form.on('select(city)', function (data) {
            //加载县
            $.ajax({
                url:"../conditional/selectProvince",
                type:"post",
                data:{"pid":data.value},
                dataType:"json",
                async:false,
                success:function(countyData){
                    var  province= '<option value="-1">请选择县/区</option>';
                    $.each(countyData,function(){
                        province += '<option value="'+this.id+'">'+this.name+'</option>'
                    })
                    $("#county").html(province);
                    form.render('select');
                }
            })

        });

        //--------------------------------------------------------

        var tableList = table.render({
            elem: '#layui_table_id'
            , url: '../house/queryHouseList'
            , cols: [[
                {field:"id",checkbox: true,title:"用户id",width:100},
                {field:"teId",title:"用户名",width:100},
                {field:"title",title:"标题",width:100},
                {field:"price",title:"售价",width:100},
                {field:"room",title:"室",width:100},
                {field:"hall",title:"厅",width:100},
                {field:"toilet",title:"卫生间",width:100},
                {field:"area",title:"面积",width:100},
                {field:"community",title:"所属小区",width:100},
                {field:"buildingTime",title:"建造年代",width:100},
                {field:"roomType",title:"房屋类型",width:100},
                {field:"roomDirection",title:"房屋朝向",width:100},
                {field:"floor",title:"所在楼层",width:100},
                {field:"decorate",title:"装修程度",width:100},
                {field:'eonwertyukl', title: '操作', width: 230,fixed: 'right', toolbar: '#barDemo'}
            ]]
            , page: true
            , height: 580
            ,done: function(res, curr, count) {
                $("[data-field='sex']").children().each(function () {
                    if ($(this).text() == 1) {
                        $(this).text("男")
                    } else if ($(this).text() == 2) {
                        $(this).text("女")
                    }
                })
            }
        });

        $('#searchBtn').on('click', function(){
            tableList.reload({
                where:$('#houseForm').serializeJson()
            });
            return false;
        });
        //-----------------------------------------

        //监听工具条
        table.on('tool(test)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                viewLableInfo(data.attrId);
                layer.msg(data.attrId);
            } else if (layEvent === 'del') {
                //layer.msg('删除');
                layer.confirm("确认要删除吗，删除后不能恢复", {title: "删除确认"}, function (index) {
                    layer.close(index);
                    $.post("../house/delHouseById?id=" + data.id, function (data) {
                        location.reload();
                    });
                });

            } else if (layEvent === 'edit') {
                location.href="<%=request.getContextPath()%>/house/lookhouse?id="+data.id;

            }else if(layEvent == 'editDetails'){
                location.href="<%=request.getContextPath()%>/house/lookhxq?id="+data.id;
            }
        });
    });

//-----------------------------------------------------



    //----------------------------------------------------------------

    var limitcount = 10;
    var curnum = 1;


    //批量删除
    function deleteAllHouse(){

        alert(111111111111)
        var ids = "";
        var count=0;
        var checkStatus = table.checkStatus('layui_table_id')
            ,data = checkStatus.data;
        for (var i in data){
            ids += "," + data[i].id;
            count++;
        }
     alert(ids)
        if(ids == ""){
            layer.msg("请选择需要删除的房源");
        }else{
            layer.confirm("确认删除勾选的"+count+"个房源?", {icon: 3, title:"确认"}, function(){
                ids = ids.substr(1);
                $.ajax({
                    url:"../house/delAllHouseByIds",
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

</script>
</html>

