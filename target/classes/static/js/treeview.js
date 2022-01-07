$(function () {
    let kingdomInfo = {
        text: '漏洞领域：'
    };
    let sinkInfo = {
        text:'主要信息'
    };
    let descriptionInfo = {
        text:'问题描述'
    };
    let patternInfo = {
        text:'漏洞模式名称'
    };
    let knowledgeInfo = {
        text:'知识条目'
    };
    let treeData1 = [sinkInfo];
    let treeData2 = [kingdomInfo,descriptionInfo,patternInfo,knowledgeInfo];
    $("#evidenceTree").treeview({
        data:treeData1,
        color:'#428bca',
        showBorder: false,
        expandIcon: 'glyphicon glyphicon-chevron-right',
        collapseIcon: 'glyphicon glyphicon-chevron-down',
        nodeIcon: 'glyphicon glyphicon-bookmark',
    });
    $("#patternTree").treeview({
        data:treeData2,
        color:'#428bca',
        showBorder: false,
        expandIcon: 'glyphicon glyphicon-chevron-right',
        collapseIcon: 'glyphicon glyphicon-chevron-down',
        nodeIcon: 'glyphicon glyphicon-bookmark',
    });
});
function watchIssue(issueId) {
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
                        text:'源文件路径：'+data.filePath
                    },
                    {
                        text:data.fileName+":"+data.startLine+" - "+data.targetFunction
                    }
                ]
            };
            let descriptionInfo = {
                text:'问题描述',
                nodes: [{
                    text:data.description
                }]
            };
            let patternInfo = {
                text:'漏洞模式：'+data.patternName,
                nodes:[{
                        text:'说明'
                    },{
                        text:'修复建议'
                    },{
                        text:'小提示'
                    }
                ]
            };
            let sourceInfo = {};
            if (data.issueSource != null){
                sourceInfo = {
                    text:'底部信息',
                    nodes:[
                        {
                            text:'源文件路径：'+data.issueSource.filePath
                        },
                        {
                            text:data.fileName+":"+data.issueSource.startLine+" - "+data.issueSource.targetFunction
                        }
                    ]
                }
            }
            let nodeList = [];
            let knowledgeData = data.knowledgeList;
            for(let i=0;i<knowledgeData.length;++i){
                let node = {
                    text:knowledgeData[i].knowledgeName,
                    nodes:[{
                        text:knowledgeData[i].content
                    }]
                };
                nodeList.push(node);
            }
            let knowledgeInfo = {
                text:'知识条目',
                nodes: nodeList
            };
            console.log(knowledgeInfo);
            let treeData1,treeData2;
            if(sourceInfo.text !== undefined)
                treeData1 = [sinkInfo,sourceInfo];
            else treeData1 = [sinkInfo];
            treeData2 = [kingdomInfo,descriptionInfo,patternInfo,knowledgeInfo];
            $("#evidenceTree").treeview({
                data:treeData1,
                color:'#428bca',
                showBorder: false,
                expandIcon: 'glyphicon glyphicon-chevron-right',
                collapseIcon: 'glyphicon glyphicon-chevron-down',
                nodeIcon: 'glyphicon glyphicon-bookmark',
                onNodeSelected:function(event,node){
                    if (node.text === '主要信息')
                        $('#codeInfo').text(data.snippet);
                    if (node.text === '底部信息')
                        $("#codeInfo").text(data.issueSource.snippet);
                }
            });
            $("#patternTree").treeview({
                data:treeData2,
                color:'#428bca',
                showBorder: false,
                expandIcon: 'glyphicon glyphicon-chevron-right',
                collapseIcon: 'glyphicon glyphicon-chevron-down',
                nodeIcon: 'glyphicon glyphicon-bookmark',
                onNodeSelected:function(event,node){
                    if (node.text === '说明')
                        $('#longtext').text(data.explanation);
                    if (node.text === '修复建议')
                        $('#longtext').text(data.recommendation);
                    if (node.text === '小提示')
                        $('#longtext').text(data.tip);
                }
            })
        }
    })
}