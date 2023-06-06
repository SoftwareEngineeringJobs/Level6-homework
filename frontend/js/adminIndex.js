window.onload = function () {
    let adminInfo = {
        path: "/admin/adminInfo",
        method: "GET",
    }
    requests(adminInfo).then((data) => {
        if (data.code === Admin_Info.code) {
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
        }
    });
}