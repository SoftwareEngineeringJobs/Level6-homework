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
                            location.href = "./index.html";
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
                tableStr += "<td><button>Edit Exam</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("examTable").innerHTML += tableStr;
        }
    });
}