$(function () {
    let kingdomInfo = {
        text: '漏洞领域：'
    };
    let sinkInfo = {
        text:'主要信息',
        nodes:[
            {
                text:'源文件名称：'
            },
            {
                text:'源文件路径：'
            },
            {
                text:'起始行：'
            },
            {
                text:'源方法名称：'
            },
            {
                text:'源代码'
            }
        ]
    };
    let descriptionInfo = {
        text:'描述信息'
    };
    let patternInfo = {
        text:'漏洞模式名称',
    };
    let treeData = [kingdomInfo,sinkInfo,descriptionInfo,patternInfo];
    $("#tree").treeview({
        data:treeData,
        color:'#428bca',
        showBorder: false,
        expandIcon: 'glyphicon glyphicon-chevron-right',
        collapseIcon: 'glyphicon glyphicon-chevron-down',
        nodeIcon: 'glyphicon glyphicon-bookmark',
    });
});
function watch(issueId) {
    $.ajax({
        url:'/issue/info/'+issueId,
        success:function (data) {
            let kingdomInfo = {
                text: '漏洞领域：'+data.kingdom
            };
            let sinkInfo = {
                text:'主要信息',
                nodes:[
                    {
                        text:'源文件名称：'+data.fileName
                    },
                    {
                        text:'源文件路径：'+data.filePath
                    },
                    {
                        text:'起始行：'+data.startLine
                    },
                    {
                        text:'源方法名称：'+data.targetFunction
                    },
                    {
                        text:'源代码'
                    }
                ]
            };
            let descriptionInfo = {
                text:'漏洞描述信息',
                nodes: [{
                    text:data.description
                }]
            };
            let patternInfo = {
                text:data.patternName,
                nodes:[{
                        text:'含义'
                    },{
                        text:'建议'
                    },{
                        text:'提示'
                    }
                ]
            };
            let sourceInfo = {};
            if (data.issueSource != null){
                sourceInfo = {
                    text:'底部信息',
                    nodes:[
                        {
                            text:'源文件名称：'+data.issueSource.fileName
                        },
                        {
                            text:'源文件路径：'+data.issueSource.filePath
                        },
                        {
                            text:'起始行：'+data.issueSource.startLine
                        },
                        {
                            text:'源方法名称：'+data.issueSource.targetFunction
                        },
                        {
                            text:'源代码'
                        }
                    ]
                }
            }
            let treeData;
            if(sourceInfo.text !== undefined)
                treeData = [kingdomInfo,sinkInfo,descriptionInfo,sourceInfo,patternInfo];
            else treeData = [kingdomInfo,sinkInfo,descriptionInfo,patternInfo];
            $("#tree").treeview({
                data:treeData,
                color:'#428bca',
                showBorder: false,
                expandIcon: 'glyphicon glyphicon-chevron-right',
                collapseIcon: 'glyphicon glyphicon-chevron-down',
                nodeIcon: 'glyphicon glyphicon-bookmark',
                onNodeSelected:function(event,node){
                    if (node.text === '源代码')
                        $('#codeInfo').text(data.snippet);
                }
            });
        }
    })
}