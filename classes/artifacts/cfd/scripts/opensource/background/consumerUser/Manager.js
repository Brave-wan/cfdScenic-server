/**
 * Created by Administrator on 2016/10/19 0019.
 */
var consumerUserManager = {
    delUrl : "./background/consumerUserManage/updateState",
    init : function () {
        $(window).load(function () {
            $('#consumerUserGid').datagrid({
                url : "./background/consumerUserManage/getConsumerUserList",
                border : false,
                fit : true,
                singleSelect : true,
                idFiled : 'id',
                pagination : true,
                pageSize : 20,
                striped : true,
                fitColumns : true,
                columns : [[{
                    field : 'id',
                    title : 'id',
                    align : 'center',
                    width : 100,
                    hidden: true,
                },{
                    field : 'nickName',
                    title : '昵称',
                    align : 'center',
                    width : 100,
                },{
                    field : 'gender',
                    title : '性别',
                    align : 'center',
                    width : 100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 0:
                                return '男';
                            case 1:
                                return '女';
                        }
                    }
                }/*,{
                    field : 'isVirtual',
                    title : '虚拟账户',
                    align : 'center',
                    width : 100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case
                        }
                    }
                }*/,{
                    field : 'mobileNo',
                    title : '手机号',
                    align : 'center',
                    width : 100,
                },{
                    field : 'createTime',
                    title : '申请日期',
                    align:'center',
                    width:100,
                    formatter : function (
                        v, r, i) {
                        var date = new Date(v);
                        var year = date.getFullYear().toString();
                        var month = (date.getMonth() + 1);
                        var day = date.getDate().toString();
                        var hour = date.getHours().toString();
                        var minutes = date.getMinutes().toString();
                        var seconds = date.getSeconds().toString();
                        if (month < 10) {
                            month = "0" + month;
                        }
                        if (day < 10) {
                            day = "0" + day;
                        }
                        if (hour < 10) {
                            hour = "0" + hour;
                        }
                        if (minutes < 10) {
                            minutes = "0" + minutes;
                        }
                        if (seconds < 10) {
                            seconds = "0" + seconds;
                        }
                        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
                    }
                },{
                    field : 'state',
                    title : '状态',
                    align : 'center',
                    width : 100,
                    formatter : function (
                        v, r, i) {
                        switch (v) {
                            case 0 :
                                return '可用';
                            case 1 :
                                return '停用';
                        }
                    }
                }]]
            });
        });
    },

    search : function () {
        var nickName = $("#searchValue1").val();
        var mobileNo = $("#searchValue2").val();
        var state = $("#searchValue3").combobox("getValue");
        $('#consumerUserGid').datagrid('load',{nickName : nickName , mobileNo : mobileNo , state : state});
        $('#consumerUserGid').datagrid('clearSelections');
    },

    using : function () {
        var row = $('#consumerUserGid').datagrid('getSelected');
        if (!row) {
            eu.showMsg("请选择一行再进行操作");
            return;
        }
        $.messager.confirm('提示信息','您确定要将该用户设为可用吗？',function (r) {
            if (r) {
                $.post(consumerUserManager.delUrl,{
                    id : row.id,
                    state : 0
                }).success(function (data) {
                    if (data.success) {
                        eu.showMsg('操作成功！');
                        $('#consumerUserGid').datagrid('reload');
                    } else {
                        eu.showMsg(data);
                    }
                }).error(function () {
                    eu.showMsg('系统错误，请联系管理员！');
                })
            }
        })
    },

    del : function () {
        var row = $('#consumerUserGid').datagrid('getSelected');
        if (!row) {
            eu.showMsg("请选择一行再进行操作");
            return;
        }
        $.messager.confirm('提示信息','您确定要将该用户设为停用吗？',function (r) {
            if (r) {
                $.post(consumerUserManager.delUrl,{
                    id : row.id,
                    state : 1
                }).success(function (data) {
                    if (data.success) {
                        eu.showMsg('操作成功！');
                        $('#consumerUserGid').datagrid('reload');
                    } else {
                        eu.showMsg(data);
                    }
                }).error(function () {
                    eu.showMsg('系统错误，请联系管理员！');
                })
            }
        })
    }
};
consumerUserManager.init();