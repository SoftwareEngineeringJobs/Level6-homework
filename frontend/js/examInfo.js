window.onload = function () {
    let examInfo = {
        path: "/examInfo",
        method: "GET",
    }
    admin_requests(examInfo).then((data) => {
        if (data.code === Exam_Info.code) {
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
                            location.href = "../index.html";
                        }, 1000)
                    } else {
                        swal('错误', '错误', 'error');
                    }
                });
            });

            // 修改学生信息
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let exam = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + exam.examId + "</td>";   // ID不可更改
                tableStr += "<td contentEditable=\"true\">" + exam.registerTime + "</td>";
                tableStr += "<td contentEditable=\"true\">" + exam.testTime + "</td>";
                tableStr += "<td contentEditable=\"true\">" + exam.scoreTime + "</td>";
                tableStr += "<td contentEditable=\"true\">" + exam.paperA + "</td>";
                tableStr += "<td contentEditable=\"true\">" + exam.paperB + "</td>";
                tableStr += "<td contentEditable=\"true\">" + exam.paperC + "</td>";
                tableStr += "<td><button onclick='modifyExam(" + (i+1) + ")'>Edit Exam</button></td>";
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("examTable").innerHTML += tableStr;
        }
    });
}

function modifyExam(rowId) {
    let table = document.getElementById("examTable");
    let row = table.rows[rowId];
    let result = {
        path: "/modifyExam",
        method: "POST",
        data: {
            examId: parseInt(row.cells[0].innerHTML),
            registerTime: row.cells[1].innerHTML,
            testTime: row.cells[2].innerHTML,
            scoreTime: row.cells[3].innerHTML,
            paperA: parseInt(row.cells[4].innerHTML),
            paperB: parseInt(row.cells[5].innerHTML),
            paperC: parseInt(row.cells[6].innerHTML),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Modify_Exam_Success.code) {
            swal('更改考试信息成功', '更改考试信息成功！', 'success');
            setTimeout(() => {
                location.href = "admin-examInfo.html";
            }, 1000)
        }
        else if (data.code === Modify_Exam_Failure.code) {
            swal('更改考试信息失败', '更改考试信息失败！', 'error');
        }
    });
}

function dateFormat(originTime){
    if (originTime.indexOf('T') != -1) {
        originTime += ':00'
        originTime = originTime.replace('T',' ')
        return originTime;
    }
}

function publishExam() {
    let registerTime = document.getElementById("registerTime");
    let testTime = document.getElementById("testTime");
    let scoreTime = document.getElementById("scoreTime");
    let paperA = document.getElementById("paperA");
    let paperB = document.getElementById("paperB");
    let paperC = document.getElementById("paperC");
    if (paperA === "" || paperB === "" || paperC === "") {
        swal('发布考试信息失败', '考试信息不能为空！', 'error');
        return;
    }
    let result = {
        path: "/publishExam",
        method: "POST",
        data: {
            registerTime: dateFormat(registerTime.value),
            testTime: dateFormat(testTime.value),
            scoreTime: dateFormat(scoreTime.value),
            paperA: parseInt(paperA.value),
            paperB: parseInt(paperB.value),
            paperC: parseInt(paperC.value),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Publish_Exam_Success.code) {
            swal('发布考试信息成功', '发布考试信息成功！', 'success');
            setTimeout(() => {
                location.href = "admin-examInfo.html";
            }, 1000)
        }
        else if (data.code === Publish_Exam_Failure.code) {
            swal('发布考试信息失败', '发布考试信息失败！', 'error');
        }
    });
}