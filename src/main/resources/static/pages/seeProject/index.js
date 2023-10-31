onload = () => {
    $('#headerDivB').text('项目详情')

    let projectId = $util.getPageParam('seeProject')
    console.log(projectId, 'projectId')
    fetchProjectInfo(projectId)
}
var share = (id) => {
    var host = window.location.origin+"/pages/share/share.html?id="+id;
    // const textElement = document.getElementById("textToCopy");
    // const textToCopy = textElement.value;
    navigator.clipboard.writeText(host)
        .then(() => {
            alert("分享地址已复制成功！");
        })
        .catch((error) => {
            console.error("分享地址已复制失败:", error);
        });
}
const fetchProjectInfo = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data[0]
            if (info.questionnaireEntitys && info.questionnaireEntitys.length > 0) {
                var tableStr = "";
                info.questionnaireEntitys.forEach((el, index) => {
                  var creationDate=formatDateTime(el.creationDate);
                  var endTime=formatDateTime(el.endTime);
                  tableStr += `
                             <tr>
                                <td>${index+1}</td>
                                 <td>${el.questionName}</td>
                                 <td>${creationDate}</td>
                                 <td>${endTime}</td>
                                 <td>${el.answerTotal}</td>
                                 <td><button type='button' onclick="share('${el.id}')" class='btn btn-link btn-red'>复制分享口令</button></td>
                            </tr>
                        `;
                })
                $('#mainTable').html(tableStr)
            }
            console.log(info, 'res')
            $('#projectName').text(info.projectName)
            $('#createTime').text(formatDateTime(info.creationDate))
            $('#projectDescription').text(info.projectContent)
            $('#personInCharge').text(info.lastUpdatedBy)
        }
    })
}