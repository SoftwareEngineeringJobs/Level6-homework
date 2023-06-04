let examID = document.getElementById("examID");
let writing = document.getElementById("essay-answer");
let translate = document.getElementById("translate-answer");
let registrationId = 0;

function getPaperInfo_fun() {
    let getPaperInfo = {
        path: "/getPaperInfo",
        method: "GET",
        data: {
            examId: examID.value === "" ? 1 : examID.value
        }
    }
    teacher_requests(getPaperInfo)
        .then((data) => {
            if (data.code === Get_Paper_Success.code) {
                registrationId = data.data.registrationId
                writing.innerText = data.data.writing;
                translate.innerText = data.data.translation;
            } else if (data.code === Without_Paper.code) {
                registrationId = 0;
                writing.innerText = "";
                translate.innerText = "";
                swal(data.msg, data.msg, 'success');
            } else {
                swal(data.msg, data.msg, 'error');
            }
        });
}

window.onload = function () {
    let isLogin = {
        path: "/isLogin",
        method: "GET",
    }

    teacher_requests(isLogin).then((data) => {
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
        }
    });

    getPaperInfo_fun();
}

let Submit_Score = document.getElementById("Submit-Score");
Submit_Score.addEventListener("click", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();

    let Translate_Score = document.getElementById("Translate-Score");
    let Essay_Score = document.getElementById("Essay-Score");

    // 数据
    let scoring = {
        path: "/scoring",
        method: "GET",
        data: {
            registrationId: registrationId,
            scores: parseInt(Translate_Score.value) + parseInt(Essay_Score.value)
        }
    }

    teacher_requests(scoring).then((data) => {
        if (data.code === Scoring_Success.code) {
            swal(data.msg, data.msg, 'success');
            Essay_Score.value = 0;
            Translate_Score.value = 0;
            getPaperInfo_fun();
        } else {
            swal('错误', '错误', 'error');
        }
    });
});

let selectExam = document.getElementById("select-Exam");
selectExam.addEventListener("click", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();

    getPaperInfo_fun();
});
