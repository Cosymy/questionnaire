onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
}


var createQuestion=()=>{
  var projectId=$util.getPageParam('CreateQuestionnaireId');
  var questionName=$('#surveyName').val();
  var questionContent=$('#surveyDescription').val();
  var startTime=$('#startTime input').val();
  var endTime=$('#endTime input').val();
  if(!projectId) return alert("项目ID不能为空");
  if(!questionName) return alert("调查名称不能为空");
  if(!questionContent) return alert("调查说明不能为空");
  if(!startTime) return alert("开始时间不能为空");
  if(!endTime) return alert("结束时间不能为空");
  $util.setItem("questObj",{'projectId':projectId,'questionName':questionName,'questionContent':questionContent,'startTime':startTime,
    'endTime':endTime
  })
  location.href = '/pages/designQuestionnaire/index.html';
}
