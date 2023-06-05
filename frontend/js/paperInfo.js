window.onload = function () {
    let paperInfo = {
        path: "/paperInfo",
        method: "GET",
    }
    admin_requests(paperInfo).then((data) => {
        if (data.code === Paper_Info.code) {
            // 修改右上角
            let div = document.getElementById("admin-login-div")
            div.innerHTML = "<button id='admin-logout-btn' class='btn'>退出登录</button>"
            // 注册点击事件
            const admin_logout_btn = document.getElementById("admin-logout-btn");
            admin_logout_btn.addEventListener("click", (event) => {
                // 阻止默认行为，如刷新页面
                event.preventDefault();

                // 数据
                let logout = {
                    path: "/logout",
                    method: "GET",
                }

                admin_requests(logout).then((data) => {
                    if (data.code === Admin_Logout_Success.code) {
                        swal(data.msg, data.msg + ' 等待跳转', 'success');
                        // 跳转到另一个界面
                        setTimeout(() => {
                            location.href = "./index.html";
                        }, 1000)
                    } else {
                        swal('错误', '错误', 'error');
                    }
                });
            });

            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let paper = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + paper.paperId + "</td>";   // ID不可更改
                tableStr += "<td contentEditable=\"true\">" + paper.questionId + "</td>";
                tableStr += "<td contentEditable=\"true\" style=\"width:100px;\">" + paper.question + "</td>";
                tableStr += "<td contentEditable=\"true\">" + paper.answer + "</td>";
                tableStr += "<td><button>Edit Question</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("paperTable").innerHTML += tableStr;
        }
    });
}

function search () {
    let paperId = document.getElementById("paperId");
    let questionId = document.getElementById("questionId");
    let paperInfo = {
        path: "/paperInfo",
        method: "GET",
        data: {
            paperId: parseInt(paperId.value),
            questionId: parseInt(questionId.value),
        }
    }
    if (paperId.value === "")
        delete paperInfo["data"]["paperId"];
    if (questionId.value === "")
        delete paperInfo["data"]["questionId"];
    let table = document.getElementById('paperTable');
    let rowNum = table.rows.length;
    // 删除原来的内容（第一行是搜索框保留）
    for (i = 1; i < rowNum; i++) {
        table.deleteRow(i);
        rowNum = rowNum - 1;
        i = i - 1;
    }
    admin_requests(paperInfo).then((data) => {
        // 显示查询到的教师信息
        if (data.code === Paper_Info.code) {
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let paper = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + paper.paperId + "</td>";   // ID不可更改
                tableStr += "<td contentEditable=\"true\">" + paper.questionId + "</td>";
                tableStr += "<td contentEditable=\"true\" style=\"width:100px;\">" + paper.question + "</td>";
                tableStr += "<td contentEditable=\"true\">" + paper.answer + "</td>";
                tableStr += "<td><button>Edit Question</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("paperTable").innerHTML += tableStr;
        }
        // 跳出弹窗提示未搜索到，并刷新界面
        else if (data.code === Res_Not_Found.code) {
            swal("查询失败", '没有找到相关结果！', 'warning');
            setTimeout(() => {
                location.href = "admin-paperInfo.html";
            }, 1000)
        }
    });
}