window.onload = function () {
    // 查看学生信息
    let stuInfo = {
        path: "/stuInfo",
        method: "GET",
    }
    admin_requests(stuInfo).then((data) => {
        if (data.code === Stu_Info.code) {
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
                let stu = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + stu.stuId + "</td>";
                tableStr += "<td>" + stu.idCard + "</td>";
                tableStr += "<td>" + stu.name + "</td>";
                var gender;
                if (stu.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td>" + stu.school + "</td>";
                tableStr += "<td>" + stu.cet4 + "</td>";
                tableStr += "<td>" + (stu.cet6 === undefined ? "" : stu.cet6) + "</td>";
                tableStr += "<td><button>Reset Password</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("stuTable").innerHTML += tableStr;
        }
    });
}

function search () {
    let stuId = document.getElementById("stuId");
    let idCard = document.getElementById("idCard");
    let stuName = document.getElementById("name");
    let gender = document.getElementById("gender");
    let school = document.getElementById("school");
    let temp = null;
    if (gender.value === "女")
        temp = true;
    else if (gender.value === "男")
        temp = false;
    let stuInfo = {
        path: "/stuInfo",
        method: "GET",
        data: {
            stuId: parseInt(stuId.value),
            idCard: idCard.value,
            name: stuName.value,
            gender: temp,
            school: school.value
        }
    }
    if (stuId.value === "")
        delete stuInfo["data"]["stuId"];
    if (temp === null)
        delete stuInfo["data"]["gender"];
    let table = document.getElementById('stuTable');
    let rowNum = table.rows.length;
    // 删除原来的内容（第一行是搜索框保留）
    for (i = 1; i < rowNum; i++) {
        table.deleteRow(i);
        rowNum = rowNum - 1;
        i = i - 1;
    }
    admin_requests(stuInfo).then((data) => {
        // 显示查询到的学生信息
        if (data.code === Stu_Info.code) {
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let stu = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + stu.stuId + "</td>";
                tableStr += "<td>" + stu.idCard + "</td>";
                tableStr += "<td>" + stu.name + "</td>";
                var gender;
                if (stu.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td>" + stu.school + "</td>";
                tableStr += "<td>" + stu.cet4 + "</td>";
                tableStr += "<td>" + (stu.cet6 === undefined ? "" : stu.cet6) + "</td>";
                tableStr += "<td><button>Reset Password</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("stuTable").innerHTML += tableStr;
        }
        // 跳出弹窗提示未搜索到，并刷新界面
        else if (data.code === Res_Not_Found.code) {
            swal("查询失败", '没有找到相关结果！', 'warning');
            setTimeout(() => {
                location.href = "admin-stuInfo.html";
            }, 1000)
        }
    });
}