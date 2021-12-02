$(function () {
    let f_violation_pie = echarts.init(document.getElementById('f_violation_pie'));
    $.get('/f_violations/type', function (data) {
        f_violation_pie.setOption({
            tooltip:{
              trigger:'item',
              formatter:'{a} <br/>{b} : {c} ({d}%)'
            },
            legend:{
                orient:'vertical',
                x:'left',
                data:["BAD_PRACTICE","STYLE","MALICIOUS_CODE","CORRECTNESS","MT_CORRECTNESS","PERFORMANCE","I18N","SECURITY","EXPERRIMENTAL"]
            },
            calculable:true,
            series : [
                {
                    name: '漏洞类型',
                    type: 'pie',    // 设置图表类型为饼图
                    radius: '80%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                    x:'right',
                    data:data
                }
            ]
        })
    }, 'json');
    let f_violation_bar = echarts.init(document.getElementById('f_violation_bar'));
    $.get('/f_violations/priority', function (data) {
        f_violation_bar.resize({
           width:800,
           height:400
        });
        f_violation_bar.setOption({
            tooltip : {
                trigger: 'axis'
            },
            xAxis:[{
                type:'category',
                data:['1','2','3','>=4']
            }],
            yAxis:[{
               type:'value'
            }],
            calculable:true,
            series : [
                {
                    name: '优先级',
                    type: 'bar',
                    data:data
                }
            ]
        })
    }, 'json');
});