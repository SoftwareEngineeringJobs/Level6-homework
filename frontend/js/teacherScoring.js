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
            getTeacherInfo();
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
