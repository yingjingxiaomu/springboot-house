<%--
  Created by IntelliJ IDEA.
  User: 樱井小暮
  Date: 2018/3/15
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/layui/layui.js"></script>
    <link rel="stylesheet" href="../js/layui/css/layui.css">
</head>



<body>
<form class="layui-form" id="addHouse"  style="padding-top:10px">
    <input type="hidden" name="id" id="newhouseid" value="${newhouse.id}"  size="10" lay-verify="title" autocomplete="off" placeholder="请输入名字" class="layui-input">

    <input type="hidden"  value="${newhouse.roomType}" id="housetype0"/>
    <input type="hidden"  value="${newhouse.decorate}" id="decorate0"/>



    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="title" value="${newhouse.title}"  size="10" lay-verify="title" autocomplete="off" placeholder="请输入名字" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">售价</label>
            <div class="layui-input-inline">
                <input type="text" name="price" value="${newhouse.price}"  lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">请选择房屋情况</label>
        <div class="layui-input-inline">
            <select name="room"  id="roomid">
                <option >请选择室</option>
                <option value="1" >1室</option>
                <option value="2" >2室</option>
                <option value="3" >3室</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="hall" id="hallid" >
                <option >请选择厅</option>
                <option value="1" >1厅</option>
                <option value="2" >2厅</option>
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="toilet"  id="toiletid" >
                <option >请选择卫</option>
                <option value="1" >1卫</option>
                <option value="2" >2卫</option>
                <option value="3" >3卫</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">面积</label>
            <div class="layui-input-inline">
                <input type="text" name="area" value="${newhouse.area}"  placeholder="请输入" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">小区</label>
            <div class="layui-input-inline">
                <input type="text" name="community" value="${newhouse.community}"    autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">地区</label>
        <div class="layui-input-inline">
            <select name="province" id="province" lay-filter="province">
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="city" id="city" lay-filter="city">
            </select>
        </div>
        <div class="layui-input-inline">
            <select name="county" id="county">
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">年代</label>
        <div class="layui-input-block">
            <select name="buildingTime"  lay-verify="required" id="editBtime">
                <option value="00" >选择</option>
                <option value="90年代" >90年代</option>
                <option value="80年代" >80年代</option>
                <option value="70年代" >70年代</option>
                <option value="60年代" >60年代</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">请选择住宅</label>
        <div class="layui-input-block">
            <select name="roomType"  lay-verify="required" id="roomType" value="${newhouse.roomType}">
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">房屋方向</label>
            <div class="layui-input-inline">
                <input type="text" name="roomDirection" value="${newhouse.roomDirection}" id="roomDirection" placeholder="例如:东南"  autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">所在楼层</label>
            <div class="layui-input-inline">
                <input type="text"  name="floor" value="${newhouse.floor}"  placeholder="例如:22楼" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">装修情况</label>
        <div class="layui-input-block">
            <select name="decorate"  id="decorate" value="${newhouse.decorate}">
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">房屋单价</label>
            <div class="layui-input-inline">
                <input type="text" name="unitPrice" value="${newhouse.unitPrice}" id="unitPrice" placeholder="79333 元/m²" lay-verify="required" autocomplete="off" class="layui-input">每平方米
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">参考月供</label>
            <div class="layui-input-inline">
                <input type="text" name="monthlyPayments" value="${newhouse.monthlyPayments}" id="monthlyPayments" lay-verify="required" autocomplete="off" class="layui-input">每平方米
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">核心卖点</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" placeholder="请输入核心卖点" name="sellingPoint" id="sellingPoint" >
                ${newhouse.sellingPoint}
            </textarea>
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">业主心态</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" placeholder="请输入业主心态" name="ownerMentality" id="ownerMentality" >
                ${newhouse.ownerMentality}
            </textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">小区配套</label>
        <div class="layui-input-block">
             <textarea class="layui-textarea" placeholder="请输入小区配套" name="communityComplete" id="communityComplete" >
                 ${newhouse.communityComplete}
             </textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">服务介绍</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" placeholder="请输入服务介绍" name="serviceIntroduce" id="serviceIntroduce" >
                ${newhouse.serviceIntroduce}
            </textarea>
        </div>
    </div>



    <div class="layui-form-item">
        <label class="layui-form-label">室内图</label>

        <div class="layui-upload">
            <button type="button" class="layui-btn" id="testimg1">室内图上传</button>
            <div style="padding-left: 100px" class="layui-upload-list" id="demo1"></div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">户型图</label>

        <div class="layui-upload">
            <button type="button" class="layui-btn" id="testimg2">户型图上传</button>
            <div style="padding-left: 100px" class="layui-upload-list" id="demo2"></div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">环境图</label>
        <div class="layui-upload">
            <button type="button" class="layui-btn" id="testimg3">环境图上传</button>
            <div style="padding-left: 100px" class="layui-upload-list" id="demo3"></div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">

            <button class="layui-btn" onclick="addBookButton()">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
   //富文本编辑器 服务介绍
    layui.use('layedit', function(){
        layedit = layui.layedit;
        indexsone = layedit.build('serviceIntroduce',{
            height:200,
            width:200
        }); //建立编辑器
    });

   //富文本编辑器 核心卖点
   layui.use('layedit', function(){
       layedit = layui.layedit;
       indexstwo = layedit.build('sellingPoint',{
           height:200,
           width:200
       }); //建立编辑器
   });

   //富文本编辑器 业主心态
   layui.use('layedit', function(){
       layedit = layui.layedit;
       indexsthree = layedit.build('ownerMentality',{
           height:200,
           width:200
       }); //建立编辑器
   });

   //富文本编辑器 小区配套
   layui.use('layedit', function(){
       layedit = layui.layedit;
       indexsfour = layedit.build('communityComplete',{
           height:200,
           width:200
       }); //建立编辑器
   });





    //加载省
    $.ajax({
        url:"../house/selectProvince",
        type:"post",
        dataType:"json",
        async:false,
        success:function(data){
            var  province= '<option value="-1">请选择省</option>';
            $.each(data,function() {
                province += '<option value="' + this.id + '">' + this.name + '</option>'
            })
            //alert(province);
            $("#province").html(province);
        }
    })



    //装修程度的房子
    $.ajax({
        url:"../house/selectDecorate",
        type:"post",
        dataType:"json",
        async:false,
        success:function(data){

            var sel ="<option value='-1'>--请选择--</option>";
            for (var i = 0; i < data.length; i++) {
                sel+="<option value="+data[i].id+">"+data[i].name+"</option>";
            }
            $("#decorate").html(sel);
            $("#decorate").val($("#decorate0").val());

        },
        error:function(){
            alert("查询下拉失败");
        },
    })


    //选择住宅房子
    $.ajax({
        url:"../house/selectHouseType",
        type:"post",
        dataType:"json",
        async:false,
        success:function(data){

            var sel ="<option value='-1'>--请选择--</option>";
            for (var i = 0; i < data.length; i++) {
                sel+="<option value="+data[i].id+">"+data[i].name+"</option>";
            }
            $("#roomType").html(sel);
            $("#roomType").val($("#housetype0").val());

        },
        error:function(){
            alert("查询下拉失败");
        },
    })

    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate


 //-----------省市县----
        form.on('select(province)', function (data) {
            $("#county").text("请选择县/区");
            //加载市
            $.ajax({
                url:"../house/selectArea",
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
                url:"../house/selectArea",
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




    })

    //所有下拉框回显
    if($("#newhouseid").val()!=""){


        //几室几厅几卫回显
        $("#roomid").val(${newhouse.room});

        $("#hallid").val(${newhouse.hall});

        $("#toiletid").val(${newhouse.toilet});

        //建造年代
        $("#editBtime").val('${newhouse.buildingTime}');



        //-------地址三级联动-----------
        //省回显
        $("#province").val('${newhouse.province}');

        //市加载全部
        var  countrys= '<option value="-1">请选择市</option>';
        countrys = '<option value="${country.id}">${country.name}</option>';
        $("#city").html(countrys)

        //县加载全部
        var  citys= '<option value="-1">请选择县/区</option>';
        citys = '<option value="${city.id}">${city.name}</option>';


        $("#county").html(citys)



    }


  //添加
    function addBookButton() {
        layedit.sync(indexsone);
        layedit.sync(indexstwo);
        layedit.sync(indexsthree);
        layedit.sync(indexsfour);
        $.ajax({
            url:"../house/addhouse",
            type:"post",
            data:$("#addHouse").serialize(),
            dateType:"json",
            success:function () {
                alert("success")
                location.href="<%=request.getContextPath()%>/house/tiaocx"
            }
        })

    }



    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;

        upload.render({
            elem: '#testimg1'
            , url: '../house/headImgUpload?typeId='+1
            , multiple: true
            , number: 5
            , size: 1024
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    //alert(result);
                    $('#demo1').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="100" width="100">')
                });
            }
            , done: function (res) {
                //上传完毕
                /*for(var i=0;i++;i<res.get("urllist").length){

                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="100" width="100">')

                }*/

            }
            , allDone: function (obj) {
                console.log(obj)
            }
        });
    })

    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;

        upload.render({
            elem: '#testimg2'
            , url: '../house/headImgUpload?typeId='+2
            , multiple: true
            , number: 5
            , size: 1024
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    //alert(result);
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="100" width="100">')
                });
            }
            , done: function (res) {
                //上传完毕
                /*for(var i=0;i++;i<res.get("urllist").length){

                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="100" width="100">')

                }*/

            }
            , allDone: function (obj) {
                console.log(obj)
            }
        });
    })

    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;

        upload.render({
            elem: '#testimg3'
            , url: '../house/headImgUpload?typeId='+3
            , multiple: true
            , number: 5
            , size: 1024
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    //alert(result);
                    $('#demo3').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="100" width="100">')
                });
            }
            , done: function (res) {
                //上传完毕
                /*for(var i=0;i++;i<res.get("urllist").length){

                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" height="100" width="100">')

                }*/

            }
            , allDone: function (obj) {
                console.log(obj)
            }
        });
    })










</script>

</body>
</html>
