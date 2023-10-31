var startAnswer=function(){
    var params = getUrlParams(window.location.href);
    params.content= $('#problem').html();
    $.ajax({
        url: API_BASE_URL + '/admin/startAnswer',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            alert("提交成功")
            $('#startAnswerBtn').hide();
        }
    })
}

onload = () => {
    var params = getUrlParams(window.location.href);
    $.ajax({
        url: API_BASE_URL + '/admin/getQustById',
        type: 'POST',
        data: JSON.stringify({id:params.quId}),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            var qus = JSON.parse(res.data.question)
            console.log(qus)
            //单选
            if (qus.item1 && qus.item1.length > 0) {
                qus.item1.forEach(le => {
                    $('#problem').append(`
                        <div class="question" id="question1" data-type="1" data-problemIndex="1">
                          <div class="top">
                            <span class="question-title" id="questionTitle">${le.title}</span>
                            <span class="must-answer" id="mustAnswer">必答题</span>
                          </div>
                          <div class="bottom">
                            
                      `)
                    le.child.forEach(ee => {
                        $('#problem').append(`
                            <div style="display: flex; align-items: center; margin-bottom: 3px;">
                              <label class="radio-inline">
                                <input type="radio" name="chooseTerm">${ee}
                              </label>
                            </div>
                        `);
                    })
                })
                $('#problem').append(`  </div>   </div>  `)
            }
            $('#problem').append(` <div style="padding: 2em 0"></div>`)
            //多选
            if (qus.item2 && qus.item2.length > 0) {
                qus.item2.forEach(le => {
                    $('#problem').append(`
                      <div class="question" id="question1"  data-type="1" data-problemIndex="1">
                        <div class="top">
                          <span class="question-title" id="questionTitle">${le.title}</span>
                          <span class="must-answer" id="mustAnswer">必答题</span>
                        </div>
                        <div class="bottom">
                         
                    `)
                    le.child.forEach(ee => {
                        $('#problem').append(`
                           <div style="display: flex; align-items: center; margin-bottom: 3px;">
                            <label class="checkbox-inline">
                              <input type="checkbox" name="chooseTerm">${ee}
                            </label>
                          </div>
                        `);
                    })
                    $('#problem').append(`  </div>   </div>  `)
                })
                $('#problem').append(` <div style="padding-bottom: 2em "></div>`)
            }

            //填空
            if (qus.item3 && qus.item3.length > 0) {
                qus.item3.forEach(le => {
                    $('#problem').append(`
                      <div class="question" id="question1" data-type="1" data-problemIndex="1">
                        <div class="top">
                          <span class="question-title" id="questionTitle">${le.title}</span>
                          <span class="must-answer" id="mustAnswer">必答题</span>
                        </div>
                        <div class="bottom">
                          <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
                      </div>
                    `)
                })
                $('#problem').append(` <div style="padding-bottom: 2em "></div>`)
            }
            // 矩阵
            if (qus.item4 && qus.item4.length > 0) {
                qus.item4.forEach(le => {
                    $('#problem').append(`
                      <div class="question" id="question1" data-type="1" data-problemIndex="1">
                        <div class="top">
                          <span class="question-title" id="questionTitle">${le.title}</span>
                          <span class="must-answer" id="mustAnswer">必答题</span>
                        </div>
                        <div class="bottom">
                          <table class="table">
                            ${le.table}
                           </table>
                        </div>
                      </div>`)
                })
                $('#problem').append(` <div style="padding-bottom: 2em "></div>`)
            }
            //量表

            if (qus.item5 && qus.item5.length > 0) {
                qus.item5.forEach(le => {

                      $('#problem').append(`
                      <div class="question" id="question1" data-type="1" data-problemIndex="1">
                        <div class="top">
                          <span class="question-title" id="questionTitle">${le.title}</span>
                          <span class="must-answer" id="mustAnswer">必答题</span>
                        </div>
                        ${le.table}
                         </div>
                       `)
//
//                     $('#problem').append(`
// <!--                          <div>很不满意</div>-->
//                       </div>
//                     `)

                })

            }

        }
    })
    //
    //   $('#problem').append(`
    //   <div class="question" id="question1" data-type="1" data-problemIndex="1">
    //     <div class="top">
    //       <span class="question-title" id="questionTitle">1.单选题</span>
    //       <span class="must-answer" id="mustAnswer">必答题</span>
    //     </div>
    //     <div class="bottom">
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="radio-inline">
    //           <input type="radio" name="chooseTerm">选项1
    //         </label>
    //       </div>
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="radio-inline">
    //           <input type="radio" name="chooseTerm">选项2
    //         </label>
    //       </div>
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="radio-inline">
    //           <input type="radio" name="chooseTerm">选项3
    //         </label>
    //       </div>
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="radio-inline">
    //           <input type="radio" name="chooseTerm">选项4
    //         </label>
    //       </div>
    //     </div>
    //   </div>
    // `)
    //   $('#problem').append(`
    //   <div class="question" id="question1" data-type="1" data-problemIndex="1">
    //     <div class="top">
    //       <span class="question-title" id="questionTitle">2.多选题</span>
    //       <span class="must-answer" id="mustAnswer">必答题</span>
    //     </div>
    //     <div class="bottom">
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="checkbox-inline">
    //           <input type="checkbox" name="chooseTerm">选项1
    //         </label>
    //       </div>
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="checkbox-inline">
    //           <input type="checkbox" name="chooseTerm">选项2
    //         </label>
    //       </div>
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="checkbox-inline">
    //           <input type="checkbox" name="chooseTerm">选项3
    //         </label>
    //       </div>
    //       <div style="display: flex; align-items: center; margin-bottom: 3px;">
    //         <label class="checkbox-inline">
    //           <input type="checkbox" name="chooseTerm">选项4
    //         </label>
    //       </div>
    //     </div>
    //   </div>
    // `)
    //   $('#problem').append(`
    //   <div class="question" id="question1" data-type="1" data-problemIndex="1">
    //     <div class="top">
    //       <span class="question-title" id="questionTitle">3.填空题</span>
    //       <span class="must-answer" id="mustAnswer">必答题</span>
    //     </div>
    //     <div class="bottom">
    //       <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
    //   </div>
    // `)
    //   $('#problem').append(`
    //   <div class="question" id="question1" data-type="1" data-problemIndex="1">
    //     <div class="top">
    //       <span class="question-title" id="questionTitle">4.矩阵题</span>
    //       <span class="must-answer" id="mustAnswer">必答题</span>
    //     </div>
    //     <div class="bottom">
    //       <table class="table">
    //         <thead>
    //           <tr>
    //             <th></th>
    //             <th>选项1</th>
    //             <th>选项2</th>
    //             <th>选项3</th>
    //           </tr>
    //         </thead>
    //         <tbody>
    //           <tr>
    //             <td>标题1</td>
    //             <td><input type="radio" name="chooseTerm1" /></td>
    //             <td><input type="radio" name="chooseTerm1" /></td>
    //             <td><input type="radio" name="chooseTerm1" /></td>
    //           </tr>
    //           <tr>
    //             <td>标题2</td>
    //             <td><input type="radio" name="chooseTerm2" /></td>
    //             <td><input type="radio" name="chooseTerm2" /></td>
    //             <td><input type="radio" name="chooseTerm2" /></td>
    //           </tr>
    //         </tbody>
    //       </table>
    //     </div>
    //   </div>
    // `)
    //   $('#problem').append(`
    //   <div class="question" id="question1" data-type="1" data-problemIndex="1">
    //     <div class="top">
    //       <span class="question-title" id="questionTitle">5.量表题</span>
    //       <span class="must-answer" id="mustAnswer">必答题</span>
    //     </div>
    //     <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
    //       <div>很满意</div>
    //       <div>
    //         <label class="radio-inline">
    //           <input type="radio" name="fraction" />5
    //         </label>
    //       </div>
    //       <div>
    //         <label class="radio-inline">
    //           <input type="radio" name="fraction" />4
    //         </label>
    //       </div>
    //       <div>
    //         <label class="radio-inline">
    //           <input type="radio" name="fraction" />3
    //         </label>
    //       </div>
    //       <div>
    //         <label class="radio-inline">
    //           <input type="radio" name="fraction" />2
    //         </label>
    //       </div>
    //       <div>
    //         <label class="radio-inline">
    //           <input type="radio" name="fraction" />1
    //         </label>
    //       </div>
    //       <div>很不满意</div>
    //     </div>
    //   </div>
    // `)


}
