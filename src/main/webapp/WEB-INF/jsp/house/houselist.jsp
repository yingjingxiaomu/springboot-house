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
                , url: '<%=request.getContextPath()%>/house/queryHouseList?page='+start+'&limit=' + limitsize
                /*, where:{pagename:start,pagelimit:limitsize} //传参*/

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
                , page: false
                //,fix:true
                //,scrolling:false
                , height: 500
                ,done: function(res, curr, count){


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
    function deleteAllHouse(){

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
