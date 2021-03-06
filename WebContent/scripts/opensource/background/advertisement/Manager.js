var advertisementManager = {
    addUrl : "./background/advertisement/toAdd",
    deleteUrl : "./background/advertisement/deleteAdvertisement",
    editUrl : "./background/advertisement/toEdit",
    updateImgUrl : "./background/advertisement/updateImg",
    init : function () {
        $(window).load(function () {
            $("#advertisementGid").datagrid({
                url:'./background/advertisement/getAdvertisementList.json',
                border:false,
                fit:true,
                singleSelect:true,
                idFiled:'id',
                pagination:true,
                pageSize:20,
                striped:true,
                fitColumns:true,
                columns : [ [ {
                    field : 'id',
                    title : 'id',
                    align : 'center',width:100,hidden:true
                },{
                    field : 'is_delete',
                    title : '是否是默认',
                    align : 'center',width:60,
                    formatter : function (
                            v, r, i) {
                            switch (v) {
                                case 0 :
                                    return "否";
                                case 1 :
                                	return "是";
                            }
                        }
                },{
                	field : 'title',
                	title : '名称',
                	align : 'center',width:100
                },{
                    field : 'advert_describe',
                    title : '描述',
                    align : 'center',width:100
                },{
                    field : 'type',
                    title : '类型',
                    align : 'center',width:100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 0 :
                                return "轮播图";
                            case 1 :
                                return "广告位";
                            case 2 :
                                return "商品图片";
                        }
                    }
                },{
                    field : 'source',
                    title : '轮播图来源',
                    align : 'center',
                    width : 100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 1:
                                return "景点";
                            case 2:
                                return "酒店";
                            case 3:
                                return "饭店";
                            case 4:
                                return "小吃";
                            case 5:
                                return "特产";
                            case 6:
                                return "活动";
                            case 7:
                                return "结伴游";
                            case 8:
                                return "app首页轮播图";
                            case 9:
                                return "商城轮播图";
                        }
                    }
                },{
                    field : 'memo',
                    title : '备注',
                    align : 'center',
                    width : 100
                },{
                    field : 'state',
                    title : '状态',
                    align : 'center',
                    width : 100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 0:
                                return "可用";
                            case 1:
                                return "不可用";
                        }
                    }
                },{
                    field : 'img_url',
                    title : '图片',
                    align : 'center',
                    width : 50,
                    formatter : function(
                        v, r, i) {
                        var a = "";
                        if (v != null){
                            a = r.img_url;
                            a = a.replace(
                                "\\",
                                "/");
                            return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                + 'onclick="advertisementManager.show('
                                + "'"
                                + r.id
                                + ","
                                + a
                                + ",1"
                                + "'"
                                + ');">查看</a>';
                        }else {
                            return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
                                + "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
                                + "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
                                + 'onclick="advertisementManager.show('
                                + "'"
                                + r.id
                                + ","
                                + a
                                + ",1"
                                + "'"
                                + ');">查看</a>';
                        }
                    }
                }]]
            })
        })
    },

    show : function(url) {
        var arr = new Array();
        arr = url.split(",");
        console.log(url);
        $('#ImgWindow').window('open');
        $('#imgId').val(arr[0]);
        $('#selectType').val(arr[2]);
        if (arr[1] != null && arr[1] != ""){
            $('#showImg').attr('src', arr[1]);
        }else {
            $('#showImg').attr('alt', '暂时还没有图片，请上传！');
        }
    },

    updateImg : function(){
        var row = $('#advertisementGid').datagrid('getSelected');
        if (!row) {
            eu.showMsg("请选择一行再进行操作");
            return;
        }

        $('#ImageSubWindow').window('open');
        $('#uploadForm').form('clear');
        $('#ImgId').val(row.id);
        $('#imgSrcId').attr('src',row.img_url);
    },

    submit : function(){
        var a = advertisementManager.updateImgUrl;

        var formElement = document.getElementById("uploadForm");
        var oReq = new XMLHttpRequest();
        $.messager.progress({
            title : '提示信息',
            msg : '请稍候...',
            interval : 2000
        });
        oReq.open("POST", a, true);
        oReq.send(new FormData(formElement));
        oReq.onreadystatechange = function() {
            if (oReq.readyState == 4) {
                console.log(oReq.status);
                if (oReq.status == 200) {
                    var b = oReq.responseText;
                    console.log(b);
                    if (b == "success") {
                        eu.showMsg("操作成功");
                        $.messager.progress('close');
                        $('#ImageSubWindow').window('close');
                        $('#advertisementGid').datagrid('reload');
                    } else {
                        $.messager.progress('close');
                        eu.showMsg("失败");
                    }
                } else {
                    $.messager.progress('close');
                    eu.showMsg("失败");
                }
            }
        }
    },
    
    search : function () {
        var title =  $("#searchValue1").val();
        var source = $("#searchValue2").combobox("getValue");
        $("#advertisementGid").datagrid('load',{title:title,source:source});
        $("#advertisementGid").datagrid('clearSelections');
    },
    
    add : function () {
        window.location.href = advertisementManager.addUrl;
    },

    edit : function () {
        var row = $("#advertisementGid").datagrid('getSelected');
        if (!row){
            eu.showMsg("请选择一行再进行操作");
        }
        window.location.href = advertisementManager.editUrl+"?id="+row.id;
    },

    del : function () {
        var row = $("#advertisementGid").datagrid('getSelected');
        if (!row){
            eu.showMsg("请选择一行再进行操作");
            return;
        }
        if(row.is_delete == 1){
        	eu.showMsg("默认存在的不可以删除");
            return;
        }
        $.messager.confirm("提示信息","您确定要将该数据删除吗？",function (r) {
            if (r) {
                $.post(advertisementManager.deleteUrl,{
                    id : row.id
                }).success(function (data) {
                    if (data.success){
                        eu.showMsg("操作成功");
                        $("#advertisementGid").datagrid('reload');
                    } else {
                        eu.showMsg(data);
                    }
                }).error(function (data) {
                    eu.showMsg("系统错误，请联系管理员！");
                })
            } else {
                $("#advertisementGid").datagrid('clearSelections');
            }
        })
    }
};
advertisementManager.init();