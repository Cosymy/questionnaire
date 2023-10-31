onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  handleHeaderLoad()
  fetchProjectList()
}

let projectList = []

const fetchProjectList = () => {
  let params = {
    createdBy: $util.getItem('userInfo').username,
    projectName: $('#projectName').val()
  }
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      projectList = res.data
      $('#content').html('')

      res.data.map(item => {
        var str=builderTable(item.questionnaireEntitys);
        $('#content').append(`
          <div class="list">
            <div class="list-header">
              <div>${item.projectName}</div>
              <div>
                <button type="button" class="btn btn-link" onclick="onCreateQuestionnaire('${item.id}','${item.projectName}')">创建问卷</button>
                <button type="button" class="btn btn-link" onclick="onSeeProject('${item.id}')">查看</button>
                <button type="button" class="btn btn-link" onclick="onEditProject('${item.id}')">编辑</button>
                <button type="button" class="btn btn-link" onclick="onDelProject('${item.id}')">删除</button>
              </div>
            </div>`+str+`
<!--            <div class="list-footer">-->
<!--              <div>暂无调查问卷或问卷已过期</div>-->
<!--            </div>-->
          </div>
        `)
      })


    }
  })
}
const builderTable=(questionnaireEntitys)=>{
  if(questionnaireEntitys && questionnaireEntitys.length>0){
    var tableStr="<table style='box-sizing: border-box;width: 100%;border-collapse: collapse;' >\n" +
        "  <tr>\n" +
        "    <th>调查名称</th>\n" +
        "    <th>调查说明</th>\n" +
        "    <th>开始时间</th>\n" +
        "    <th>结束时间</th>\n" +
        "    <th>答卷人数</th>\n" +
        "    <th>操作</th>\n" +
        "  </tr>";
    questionnaireEntitys.forEach(el=>{
      tableStr+="  <tr>\n" +
          "    <td>"+el.questionName+"</td>\n" +
          "    <td>"+el.questionContent+"</td>\n" +
          "    <td>"+formatDateTime(el.startTime)+"</td>\n" +
          "    <td>"+formatDateTime(el.endTime)+"</td>\n" +
          "    <td>"+el.answerTotal+"</td>\n" ;
      tableStr+=`<td> <button type="button" id="cancelEdit" class="btn btn-default" onclick="getAnswerByQuId('${el.id}')">查看答卷列表</button></td>`;
      tableStr+= "  </tr>";
    })
    tableStr+="</table>";
    return tableStr;
  }
  return  " <div class=\"list-footer\">  <div>暂无调查问卷或问卷已过期</div>          </div>"
}


const getAnswerByQuId=(id)=>{
  location.href = "/pages/qustList/qusetList.html?quId="+id;
}
const onCreatePrject = () => {
  location.href = "/pages/createProject/index.html"
}

const onCreateQuestionnaire = (id,name) => {
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
      id:pid
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
