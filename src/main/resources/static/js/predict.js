function predict(){
    $("#resultTable").bootstrapTable({
        url: "/predict", //请求地址
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
            title: '漏洞模式',
            field: 'patternName',
            sortable: true,
        }, {
            title: '优先级',
            field: 'priority',
            sortable: true
        }, {
            title: '文件名称',
            field: 'fileName',
            sortable: true,
        }, {
            title: '正报概率',
            field: 'likelihood',
            sortable: true,
        }, {
            title: '预测结果',
            field: 'state',
            sortable: true,
        }, {
            field: 'operate',
            title: '操作',
            formatter: operateFormatter //自定义方法，添加操作按钮
        }],
    })
}

function operateFormatter(value,row,index){
    return "<a href='#' title='更新' onclick='update(\"" + row.issueId + "\",\"" + row.state + "\")'><i class='fa fa-edit'></i> </a>"
}

function update(issueId,state){
    let f;
    if (state === 'True') f = 1;
    else f = 0;
    $.ajax({
        url:'/updateState/'+issueId+"/"+f,
        method:'get',
        dataType:'json',
        success:function (msg){
            if(msg===1) layer.msg("修改成功");
        }
    })
}