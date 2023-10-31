onload = () => {
    $('#headerUsername').text($util.getItem('userInfo').username)
    // handleHeaderLoad()
    fetchProjectList()
}

let projectList = []

const fetchProjectList = () => {
    let params = getUrlParams(window.location.href);

    $.ajax({
        url: API_BASE_URL + '/admin/getAnswerByQuId',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            projectList = res.data
            $('#content').html('')
            // projectList.forEach(item => {
            var str = builderTable(projectList);
            $('#content').append(`
          <div class="list">
            <div class="list-header">
              <div>问卷列表</div>
           <!--     <div>
                <button type="button" class="btn btn-link" )">创建问卷</button>
                <button type="button" class="btn btn-link" )">查看</button>
                <button type="button" class="btn btn-link" )">编辑</button>
                <button type="button" class="btn btn-link" )">删除</button>
              </div>-->
            </div>

<!--            <div class="list-footer">-->
<!--              <div>暂无调查问卷或问卷已过期</div>-->
<!--            </div>-->
          </div>
        `)
            $('#content').append(str);
            // })


        }
    })
}


const builderTable = (questionnaireEntitys) => {
    if (questionnaireEntitys && questionnaireEntitys.length > 0) {
        var tableStr = "<table style='box-sizing: border-box;width: 100%;border-collapse: collapse;' >\n" +
            "  <tr>\n" +
            "    <th>问卷编号</th>\n" +
            "    <th>答卷人</th>\n" +
            "    <th>答卷时间</th>\n" +
            "    <th>操作</th>\n" +
            "  </tr>";
        questionnaireEntitys.forEach(el => {
            tableStr += "  <tr>\n" +
                "    <td>" + el.quId + "</td>\n" +
                "    <td>" + el.userName + "</td>\n" +
                "    <td>" + formatDateTime(el.createTime) + "</td>\n";
            tableStr += `<td> <button type="button" id="cancelEdit" class="btn btn-default" onclick="getAnswerByQuId('${el.userAnswerId}')">详情信息</button></td>`;
            tableStr += "  </tr>";
        })
        tableStr += "</table>";
        return tableStr;
    }
    return " <div class=\"list-footer\">  <div>暂无调查问卷或问卷已过期</div>          </div>"
}


const getAnswerByQuId = (userAnswerId) => {
    var item={};
    projectList.forEach(e=>{
       if( e.userAnswerId=userAnswerId){
           item=e;
       }
    })
    $util.setPageParam('content', item.content)
    location.href='/pages/showAns/showAns.html'
}
const onCreatePrject = () => {
    location.href = "/pages/createProject/index.html"
}

const onCreateQuestionnaire = (id, name) => {
    $util.setPageParam('CreateQuestionnaireId', id)
    $util.setPageParam('CreateQuestionnaireName', name)
    location.href = "/pages/createQuestionnaire/index.html"
}

const onSeeProject = (id) => {
    $util.setPageParam('seeProject', id)
    location.href = "/pages/seeProject/index.html"
}

const onEditProject = (id) => {
    let project = projectList.filter(item => item.id === id)[0]
    $util.setPageParam('editProject', project)
    location.href = "/pages/editProject/index.html"
}

const onDelProject = (pid) => {
    let state = confirm("确认删除该项目吗？")

    if (state) {
        let params = {
            id: pid
        }
        //alert(JSON.stringify(params))
        $.ajax({
            url: API_BASE_URL + '/deleteProjectById',
            type: "POST",
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json",
            success(res) {
                alert(res.message)
                fetchProjectList()
            }
        })
    }

}
