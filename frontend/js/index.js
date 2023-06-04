let object = {
    path: "/student/getStudentInfo",
    method: "GET",
}

requests(object)
    .then((data) => {
        if (data.code === Get_Student_Info.code) {
            let div = document.querySelector("body > div > div.section.header > div.header-bottom-section > div > div > div > div.header-menu.d-none.d-lg-block > ul > div")
            div.innerHTML = "姓名: " + data.data.name + " 学校: " + data.data.school + "  "
            div.innerHTML += "<button id='logout-btn' class='btn'>退出登录</button>"

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

