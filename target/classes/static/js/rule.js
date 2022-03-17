$(function () {
    $("#ruleTable").bootstrapTable({
        url: "/rules", //请求地址
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页
        pagination: true, //是否分页
        sidePagination: 'client', //server:服务器端分页|client：前端分页
        pageSize: 5, //单页记录数
        pageList: [1, 5, 10], //可选择单页记录数
        showRefresh: true, //刷新按钮
        columns: [{
            title: '#',
            field: 'id',
            sortable: true,
            formatter: function(value, row, index) {
                return index + 1;
            }
        }, {
            title: '规则名称',
            field: 'ruleName',
            sortable: true
        }, {
            title: '创建时间',
            field: 'createTime',
            sortable: true,
            formatter: dateFormatter
        }, {
            title: '是否启用',
            field: 'state',
            sortable: true,
            formatter: strFormatter
        }, {
            field: 'operate',
            title: '操作',
            formatter: RuleOperateFormatter //自定义方法，添加操作按钮
        }]
    })
});
function watchRule(id) {
    $.ajax({
        url: '/rule/' + id,
        type: 'get',
        success: function(msg) {
            $("#ruleId").val(id);
            $("#e_ruleName").val(msg.ruleName);
            $("#e_patternName").val(msg.patternName);
            $("#e_kingdom").val(msg.kingdom);
            $("#e_priority").val(msg.priority);
            $("#e_fileName").val(msg.fileName);
            $("#e_functionName").val(msg.functionName);
        }
    })
}
/**
 * @return {string}
 */
function RuleOperateFormatter(value, row, index) {
    if (row.state === 1) return "<a href='#' title='编辑' data-toggle='modal' data-target='#editModal' onclick='watchRule(\"" + row.ruleId + "\")'><i class='fa fa-edit'></i></a>" + "  " + "<a href='#' title='禁用' onclick='update(\""+row.ruleId+"\",0)'><i class='fa fa-times'></i></a>";
    else return "<a href='#' title='编辑' data-toggle='modal' data-target='#editModal' onclick='watchRule(\"" + row.ruleId + "\")'><i class='fa fa-edit'></i></a>" + "  " + "<a href='#' title='启用' onclick='update(\""+row.ruleId+"\",1)'><i class='fa fa-check'></i></a>";
}
function strFormatter(value, row, index) {
    if (value === 0) return "否";
    else return "是";
}
/**
 * @return {string}
 */
function dateFormatter(date) {
    let dateTime = new Date(date);
    let year = dateTime.getFullYear();
    let month = dateTime.getMonth()+1;
    let day = dateTime.getDate();
    let hour = dateTime.getHours();
    let minute = dateTime.getMinutes();
    let second = dateTime.getSeconds();

    if (month<10) month = '0'+month;
    if (day<10) day = '0'+day;
    if (hour<10) hour = '0'+hour;
    if (minute<10) minute = '0'+minute;
    if (second<10) second = '0'+second;

    return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}
function add() {
    let ruleName = $('#ruleName').val();
    if (ruleName === ''){
        layer.msg("请输入规则名称");
    }
    else{
        $.ajax({
            url: '/addRule',
            type: 'post',
            data: {
                'ruleName': ruleName,
                'patternName': $("#patternName").val(),
                'kingdom': $("#kingdom").val(),
                'priority': $("#priority").val(),
                'fileName': $("#fileName").val(),
                'functionName': $("#functionName").val(),
            },
            success: function(msg) {
                if (msg === 1) {
                    $("#addModal").modal('hide');
                    $("#ruleTable").bootstrapTable("refresh");
                }
                else layer.msg("编辑失败");
            }
        })
    }

}
function edit() {
    let ruleName = $('#e_ruleName').val();
    if (ruleName === ''){
        layer.msg("请输入规则名称");
    }
    else {
        $.ajax({
            url: '/editRule',
            type: 'post',
            data: {
                'ruleId': $("#ruleId").val(),
                'ruleName': $("#e_ruleName").val(),
                'patternName': $("#e_patternName").val(),
                'kingdom': $("#e_kingdom").val(),
                'priority': $("#e_priority").val(),
                'fileName': $("#e_fileName").val(),
                'functionName': $("#e_functionName").val(),
            },
            success: function (msg) {
                if (msg === 1) {
                    $("#editModal").modal('hide');
                    $("#ruleTable").bootstrapTable("refresh");
                }
                else layer.msg("编辑失败");
            }
        })
    }
}
function update(ruleId,flag){
    $.ajax({
        url:'/update/rule/'+ruleId+'/'+flag,
        success:function(msg){
            if (msg === 1){
                $("#ruleTable").bootstrapTable("refresh");
                $("#issueTable").bootstrapTable("refresh");
            }
            else layer.msg("更新失败");
        }
    })
}