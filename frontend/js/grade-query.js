window.onload = function () {
    let lookupScore = {
        path: "/lookupScore",
        method: "GET",
    }
    student_requests(lookupScore).then((data) => {
        if (data.code === Lookup_Score_Success.code) {
            // 修改右上角
            let div = document.getElementById("student-login-div")
            div.innerHTML = "<button id='student-logout-btn' class='btn'>退出登录</button>"
            // 注册点击事件
            const student_logout_btn = document.getElementById("student-logout-btn");
            student_logout_btn.addEventListener("click", (event) => {
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

            let chart = echarts.init(document.getElementById('chart'));
            let option = {
                xAxis: {
                    type: 'category',
                    data: ['写作&翻译', '听力', '阅读']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [data.data.scoreWrite, data.data.scoreListen, data.data.scoreRead],
                        type: 'bar'
                    }
                ]
            };
            chart.setOption(option);
            let label_h4 = document.getElementById('sum-score')
            label_h4.innerHTML = "您本次CET-6考试总成绩为: " + (data.data.scoreWrite + data.data.scoreListen + data.data.scoreRead)
        } else {
            swal('错误', '错误', 'error');
            setTimeout(() => {
                location.href = "./index.html";
            }, 1000);
        }
    });
}