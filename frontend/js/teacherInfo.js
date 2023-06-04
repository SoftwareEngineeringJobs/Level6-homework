window.onload = function () {
    let teacherInfo = {
        path: "/teacherInfo",
        method: "GET",
    }
    admin_requests(teacherInfo).then((data) => {
        if (data.code === Teacher_Info.code) {
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
                let teacher = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + teacher.teacherId + "</td>";
                tableStr += "<td>" + teacher.email + "</td>";
                tableStr += "<td>" + teacher.name + "</td>";
                let gender;
                if (teacher.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td><button>Reset Password</button><button style=\"margin-left:20px\">Delete Teacher</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("teacherTable").innerHTML += tableStr;
        }
    });
}