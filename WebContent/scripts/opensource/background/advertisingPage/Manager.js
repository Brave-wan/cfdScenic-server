var advertisingPageManager = {
    addUrl : "./background/advertisingPage/toAddPage",
    deleteUrl : "./background/advertisingPage/toDelete",
    editUrl : "./background/advertisingPage/toEditPage",
    init : function () {
        $(window).load(function () {
            $("#advertisingPageGid").datagrid({
                url:'./background/advertisingPage/getAdvertisingPage.json',
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
                    field : 'name',
                    title : '名称',
                    align : 'center',width:100
                },{
                    field : 'type',
                    title : '类型',
                    align : 'center',width:100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 1 :
                                return "观鸟景点";
                            case 2 :
                                return "湿地保护";
                            case 3 :
                                return "招商信息";
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
        var row = $('#advertisingPageGid').datagrid('getSelected');
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
        var a = advertisingPageManager.updateImgUrl;

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
                        $('#advertisingPageGid').datagrid('reload');
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
        $("#advertisingPageGid").datagrid('load',{name:title,type:source});
        $("#advertisingPageGid").datagrid('clearSelections');
    },
    
    add : function () {
        window.location.href = advertisingPageManager.addUrl;
    },

    edit : function () {
        var row = $("#advertisingPageGid").datagrid('getSelected');
        if (!row){
            eu.showMsg("请选择一行再进行操作");
        }
        window.location.href = advertisingPageManager.editUrl+"?id="+row.id;
    },

    del : function () {
        var row = $("#advertisingPageGid").datagrid('getSelected');
        if (!row){
            eu.showMsg("请选择一行再进行操作");
            return;
        }
        $.messager.confirm("提示信息","您确定要将该数据删除吗？",function (r) {
            if (r) {
                $.post(advertisingPageManager.deleteUrl,{
                    id : row.id
                }).success(function (data) {
                    if (data.success){
                        eu.showMsg("操作成功");
                        $("#advertisingPageGid").datagrid('reload');
                    } else {
                        eu.showMsg(data);
                    }
                }).error(function (data) {
                    eu.showMsg("系统错误，请联系管理员！");
                })
            } else {
                $("#advertisingPageGid").datagrid('clearSelections');
            }
        })
    }
};
advertisingPageManager.init();