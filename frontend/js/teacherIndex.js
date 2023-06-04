window.onload = function () {
    let isLogin = {
        path: "/teacher/isLogin",
        method: "GET",
    }
    requests(isLogin).then((data) => {
        if (data.code === Teacher_Login_Success.code) {
            // 修改右上角
            let div = document.getElementById("teacher-login-div")
            div.innerHTML = "<button id='teacher-logout-btn' class='btn'>退出登录</button>"
            // 注册点击事件
            const teacher_logout_btn = document.getElementById("teacher-logout-btn");
            teacher_logout_btn.addEventListener("click", (event) => {
                // 阻止默认行为，如刷新页面
                event.preventDefault();

                // 数据
                let logout = {
                    path: "/logout",
                    method: "GET",
                }

                teacher_requests(logout).then((data) => {
                    if (data.code === Teacher_Logout_Success.code) {
                        swal(data.msg, data.msg + ' 等待跳转', 'success');
                        // 跳转到另一个界面
                        setTimeout(() => {
                            location.reload();
                        }, 1000)
                    } else {
                        swal('错误', '错误', 'error');
                    }
                });
            });
        } else {
            swal(data.msg, data.msg, 'error');

        }
    });
}