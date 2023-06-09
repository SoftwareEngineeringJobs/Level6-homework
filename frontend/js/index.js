function getStudentInfo() {
    let object = {
        path: "/student/getStudentInfo",
        method: "GET",
    }

    requests(object).then((data) => {
        if (data.code === Get_Person_Info.code) {
            document.getElementById("login-btn").remove();
            let div = document.querySelector("body > div > div.section.header > div.header-bottom-section > div > div > div > div.header-menu.d-none.d-lg-block > ul");
            div.innerHTML += "<li><a href=\"#\" class='btn' style=\"margin-top: -10px; margin-left: 40px;color: #FFFFFF;\">" + data.data.name + "</a>\n" +
                " <ul class=\"sub-menu\">\n" +
                " <li><a href=\"resetPasswd.html\">修改密码</a></li>\n" +
                " <li><a id='logout-btn'>退出登录</a></li>\n" +
                " </ul>\n" +
                " </li>";

            // 注册点击事件
            const logout_btn = document.getElementById("logout-btn");

            logout_btn.addEventListener("click", (event) => {
                // 阻止默认行为，如刷新页面
                event.preventDefault();

                // 数据
                let logout = {
                    path: "/logout",
                    method: "GET",
                }

                student_requests(logout).then((data) => {
                    if (data.code === Student_Logout_Success.code) {
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

        }
    });
}

function getTeacherInfo() {
    let object = {
        path: "/teacher/getTeacherInfo",
        method: "GET",
    }

    requests(object).then((data) => {
        if (data.code === Get_Person_Info.code) {
            document.getElementById("login-btn").remove();
            let div = document.querySelector("body > div > div.section.header > div.header-bottom-section > div > div > div > div.header-menu.d-none.d-lg-block > ul");
            div.innerHTML += "<li><a href=\"#\" id='name-btn' class='btn' style=\"margin-top: -10px; margin-left: 40px;color: #FFFFFF;\">" + data.data.name + "</a>\n" +
                " <ul class=\"sub-menu\">\n" +
                " <li><a href=\"teacher-resetPasswd.html\">修改密码</a></li>\n" +
                " <li><a id='logout-btn'>退出登录</a></li>\n" +
                " </ul>\n" +
                " </li>";

            // 注册点击事件
            const logout_btn = document.getElementById("logout-btn");

            logout_btn.addEventListener("click", (event) => {
                // 阻止默认行为，如刷新页面
                event.preventDefault();

                // 数据
                let logout = {
                    path: "/logout",
                    method: "GET",
                }

                student_requests(logout).then((data) => {
                    if (data.code === Teacher_Logout_Success.code) {
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

        }
    });
}

function getAdminInfo() {
    let object = {
        path: "/admin/getAdminInfo",
        method: "GET",
    }

    requests(object).then((data) => {
        if (data.code === Get_Person_Info.code) {
            document.getElementById("login-btn").remove();
            let div = document.querySelector("body > div > div.section.header > div.header-bottom-section > div > div > div > div.header-menu.d-none.d-lg-block > ul");
            div.innerHTML += "<li><a href=\"#\" class='btn' style=\"margin-top: -10px; margin-left: 40px;color: #FFFFFF;\">" + data.data.name + "</a>\n" +
                " <ul class=\"sub-menu\">\n" +
                " <li><a href=\"admin-resetPasswd.html\">修改密码</a></li>\n" +
                " <li><a id='logout-btn'>退出登录</a></li>\n" +
                " </ul>\n" +
                " </li>";

            // 注册点击事件
            const logout_btn = document.getElementById("logout-btn");

            logout_btn.addEventListener("click", (event) => {
                // 阻止默认行为，如刷新页面
                event.preventDefault();

                // 数据
                let logout = {
                    path: "/logout",
                    method: "GET",
                }

                student_requests(logout).then((data) => {
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

        }
    });
}
