$(function() {
    let issue_category = echarts.init(document.getElementById('issue_category'));
    $.get('/issues/category', function(data) {
        issue_category.setOption({
            tooltip: {
                trigger: 'axis'
            },
            xAxis: [{
                type: 'category',
                data: ['输入验证和表示', 'API滥用', '安全性', '时间和状态', '错误', '代码质量', '封装', '环境']
            }],
            yAxis: [{
                type: 'value'
            }],
            calculable: true,
            series: [{
                name: '漏洞类型',
                type: 'bar',
                data: data
            }]
        })
    }, 'json');
    let issue_priority = echarts.init(document.getElementById('issue_priority'));
    $.get('/issues/priority', function(data) {
        console.log(data);
        issue_priority.resize({
            width: 800,
            height: 400
        });
        issue_priority.setOption({
            tooltip: {
                trigger: 'axis'
            },
            xAxis: [{
                type: 'category',
                data: ['Low', 'Medium', 'High', 'Critical']
            }],
            yAxis: [{
                type: 'value'
            }],
            calculable: true,
            series: [{
                name: '修正前',
                type: 'bar',
                data: data[0]
            },{
                name: '修正后',
                type: 'bar',
                data: data[1]
            }]
        })
    }, 'json');
});