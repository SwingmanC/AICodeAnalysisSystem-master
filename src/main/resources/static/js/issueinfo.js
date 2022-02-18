$(function () {
    let sinkInfo = {
        text:'主要信息'
    };
    let knowledgeInfo = {
        text:'知识条目'
    };
    let treeData1 = [sinkInfo];
    let treeData2 = [knowledgeInfo];
    $("#evidenceTree").treeview({
        data:treeData1,
        color:'#428bca',
        showBorder: false,
        expandIcon: 'glyphicon glyphicon-chevron-right',
        collapseIcon: 'glyphicon glyphicon-chevron-down',
        nodeIcon: 'glyphicon glyphicon-bookmark',
    });
    $("#knowledgeTree").treeview({
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
            $('#patternIdDiv').text(data.patternId);
            $('#descriptionArea').text(data.description);
            $('#patternNameLabel').text(data.patternName);
            $('#explanationArea').text(data.explanation);
            $('#recommendationArea').text(data.recommendation);
            $('#tipArea').text(data.tip);
            let sinkInfo = {
                text:'主要信息',
                nodes:[
                    {
                        text:'漏洞领域：'+data.kingdom
                    },
                    {
                        text:'源文件路径：'+data.filePath
                    },
                    {
                        text:data.fileName+":"+data.startLine+" - "+data.targetFunction
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
            let treeData1,treeData2;
            if(sourceInfo.text !== undefined)
                treeData1 = [sinkInfo,sourceInfo];
            else treeData1 = [sinkInfo];
            treeData2 = [knowledgeInfo];
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
            $("#knowledgeTree").treeview({
                data:treeData2,
                color:'#428bca',
                showBorder: false,
                expandIcon: 'glyphicon glyphicon-chevron-right',
                collapseIcon: 'glyphicon glyphicon-chevron-down',
                nodeIcon: 'glyphicon glyphicon-bookmark'
            })
        }
    })
}