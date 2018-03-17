<%--
  Created by IntelliJ IDEA.
  User: 18811
  Date: 2018/3/15
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="../js/jquery-3.2.1/jquery-3.2.1.js"></script>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>

    <%--轮播--%>
    <link type="text/css" rel="stylesheet" href="../js/unslider/css/css.css">
    <script src="../js/unslider/js/index.js"></script>
</head>
<body>


<div class="layui-row">
    <div class="layui-col-md4">
        <div class="grid-demo grid-demo-bg1">
            <div data-options="region:'west'" style="width:400px;">
                <br><br><br>
                <table>
                    <tr>
                        <td> 卖房房源主键ID: </td>
                        <td> <input  id="housing" class="easyui-textbox" readonly="true" value="${newhouse.id}"></td>
                    </tr>

                    <tr>
                        <td>员工表关联ID</td>
                        <td>
                            <input  id="modelsaa" class="easyui-textbox" readonly="true" value="${newhouse.teId}">
                        </td>
                    </tr>

                    <tr>
                        <td>标题</td>
                        <td>
                            <input type="text"  value="${newhouse.title}" class="easyui-textbox" id="describesaa" style="height:50px"  data-options="required:true" readonly="true">
                        </td>
                    </tr>

                    <tr>
                        <td>售价</td>
                        <td>
                            <input id="promotionsaa" value="${newhouse.price}"  class="easyui-textbox" data-options="required:true" readonly="true"></input>/元
                        </td>
                    </tr>

                    <tr>
                        <td>建筑面积</td>
                        <td>
                            <input id="proportionaa" value="${newhouse.area}" class="easyui-textbox" data-options="required:true" readonly="true"></input>/m²
                        </td>
                    </tr>

                    <tr>
                        <td>室</td>
                        <td>
                            <input id="rentaa"  value="${newhouse.room}" class="easyui-textbox" data-options="required:true" readonly="true"></input>室
                        </td>
                    </tr>

                    <tr>
                        <td>厅</td>
                        <td>
                            <input id="phoneaa" value="${newhouse.hall}" class="easyui-textbox" data-options="required:true" readonly="true"></input>厅
                        </td>
                    </tr>
                    <tr>
                        <td>卫生间</td>
                        <td>
                            <input id="toile"  value="${newhouse.toilet}" class="easyui-textbox" data-options="required:true" readonly="true"></input>卫
                        </td>
                    </tr>
                    <tr>
                        <td>所属小区</td>
                        <td>
                            <input id="communi"  value="${newhouse.community}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>省</td>
                        <td>
                            <input id="provinc"  value="${newhouse.province}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>市</td>
                        <td>
                            <input id="cit"  value="${newhouse.city}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>

                    <tr>
                        <td>县</td>
                        <td>
                            <input id="count"  value="${newhouse.county}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </div>



    <div class="layui-col-md4">
        <div class="grid-demo">
            <div data-options="region:'center'" style="width:400px;">
                                <div class="all">
                                    <div class="top-img">
                                        <div class="activeimg">
                                            <img src="<%=request.getContextPath() %>/js/unslider/img/111.jpg" width="300" height="200">
                                            <img src="<%=request.getContextPath() %>/js/unslider/img/112.jpg" width="300" height="200">
                                            <img src="<%=request.getContextPath() %>/js/unslider/img/113.jpg" width="300" height="200">
                                            <img src="<%=request.getContextPath() %>/js/unslider/img/114.jpg" width="300" height="200">
                                            <img src="<%=request.getContextPath() %>/js/unslider/img/115.jpg" width="300" height="200">
                                        </div>
                                        <div class="left"><img src="<%=request.getContextPath() %>/js/unslider/img/left.png"> </div>
                                        <div class="right"><img src="<%=request.getContextPath() %>/js/unslider/img/right.png"></div>
                                    </div>
                                    <!-- 存放缩略图的容器-->
                                    <div class="bot-img">
                                        <ul>
                                            <li class="active"><img src="<%=request.getContextPath() %>/js/unslider/img/111.jpg" > </li>
                                            <li><img src="<%=request.getContextPath() %>/js/unslider/img/112.jpg"> </li>
                                            <li><img src="<%=request.getContextPath() %>/js/unslider/img/113.jpg" > </li>
                                            <li><img src="<%=request.getContextPath() %>/js/unslider/img/114.jpg"> </li>
                                            <li><img src="<%=request.getContextPath() %>/js/unslider/img/115.jpg"> </li>
                                        </ul>
                                    </div>
                                </div>
                   </div>
                 </div>
            </div>


    <div class="layui-col-md4">
        <div class="grid-demo grid-demo-bg1">
            <div data-options="region:'east'" style="width:400px;">
                <br><br><br>
               <table>

                    <tr>
                        <td>建造年代</td>
                        <td>
                            <input id="buildingTim"  value="${newhouse.buildingTime}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>房屋类型</td>
                        <td>
                            <input id="roomTyp"  value="${newhouse.roomType}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>房屋朝向</td>
                        <td>
                            <input id="roomDirectio"  value="${newhouse.roomDirection}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>所在楼层</td>
                        <td>
                            <input id="floo"  value="${newhouse.floor}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>装修程度</td>
                        <td>
                            <input id="decorat"  value="${newhouse.decorate}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>参考单价</td>
                        <td>
                            <input id="unitPric"  value="${newhouse.unitPrice}" class="easyui-textbox" data-options="required:true" readonly="true"></input>/元
                        </td>
                    </tr>
                    <tr>
                        <td>参考月供</td>
                        <td>
                            <input id="monthlyPayment"  value="${newhouse.monthlyPayments}" class="easyui-textbox" data-options="required:true" readonly="true"></input>/元
                        </td>
                    </tr>
                    <tr>
                        <td>核心卖点</td>
                        <td>
                            <input id="sellingPoin"  value="${newhouse.sellingPoint}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>业主心态</td>
                        <td>
                            <input id="ownerMentalit"  value="${newhouse.ownerMentality}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>小区配套</td>
                        <td>
                            <input id="communityComplet"  value="${newhouse.communityComplete}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>服务介绍</td>
                        <td>
                            <input id="serviceIntroduc"  value="${newhouse.serviceIntroduce}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>发布日期</td>
                        <td>
                            <input id="releaseTim"  value="${newhouse.releaseTime}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>房屋编号</td>
                        <td>
                            <input id="roomNu"  value="${newhouse.roomNum}" class="easyui-textbox" data-options="required:true" readonly="true"></input>
                        </td>
                    </tr>
               </table>
                </center>
             </div>
        </div>
    </div>
</div>



<%--嵌套布局--%>








</body>
</html>
