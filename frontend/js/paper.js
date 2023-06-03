let getPaperInfo = {
    path: "/getPaperInfo",
    method: "GET",
}
let alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']

student_requests(getPaperInfo).then((data) => {
    if (data.code === Get_PaperInfo_Success.code) {
        // 获取成功 渲染试卷
        let arr = data.data;
        for (let i = 0; i < arr.length; i++) {
            // 获得DOM
            let questionId = arr[i].questionId;
            // 问题
            let label_i = document.querySelector("#qu_0_" + questionId + " > div.test_content_nr_tt > i")
            let span = document.querySelector("#qu_0_" + questionId + " > div.test_content_nr_tt > span")
            let font = document.querySelector("#qu_0_" + questionId + " > div.test_content_nr_tt > font")

            label_i.innerText = questionId
            span.innerText = "";

            // 写作和翻译
            if (questionId === 0 || questionId === 56) {
                // 只有题
                font.innerText = JSON.parse(arr[i].question).question
            } else {
                // 不为空
                let question = JSON.parse(arr[i].question);

                if (question.question !== undefined) {
                    font.innerText = question.question;
                } else {
                    font.innerText = "";
                }

                if (question.option !== undefined) {
                    let option = question.option
                    let label_option = document.querySelector("#qu_0_" + questionId + " > div.test_content_nr_main > ul")
                    label_option.innerHTML = "";
                    // 从A开始往下找
                    for (let j = 0; j < alphabet.length; j++) {
                        if (option[alphabet[j]] === undefined) {
                            break;
                        }
                        let num = j + 1;
                        label_option.innerHTML +=
                            '<li className="option">' +
                            '<input type="radio" className="radioOrCheck" name="answer' + questionId + '" value="' + alphabet[j] + '" id="0_answer_' + questionId + '_option_' + j + '"/>' +
                            '<label htmlFor="0_answer_' + questionId + '_option_' + num + '">' +
                            alphabet[j] + '.' +
                            '<p className="ue" style="display: inline;">' + option[alphabet[j]] + '</p>' +
                            '</label> </li>';
                    }
                }
            }
        }
    } else {
        alert(data.msg);
        // 跳转到另一个界面
        setTimeout(() => {
            location.href = "./index.html";
        }, 0)
    }
});


const UploadAnswer = document.getElementById("upload-answer");

UploadAnswer.addEventListener("submit", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();
    // 拿到所有题答案
    let writing = document.querySelector("#qu_0_0 > div.test_content_nr_main > label > textarea").value
    let translation = document.querySelector("#qu_0_56 > div.test_content_nr_main > label > textarea").value
    let choice = ""

    for (let i = 1; i < 56; i++) {
        let radio = document.getElementsByName("answer" + i)
        let answer = '_'
        for (let j = 0; j < radio.length; j++) {
            if (radio[j].checked) {
                answer = radio[j].value;
                break;
            }
        }
        choice += answer;
    }

    // 数据
    let uploadAnswer = {
        path: "/uploadAnswer",
        method: "POST",
        data: {
            translation: translation,
            writing: writing,
            choice: choice
        }
    }

    student_requests(uploadAnswer).then((data) => {
        if (data.code === Save_Answer_Success.code) {
            alert(data.msg);
            // 跳转到另一个界面
            setTimeout(() => {
                location.href = "./index.html";
            }, 0)
        } else {
            alert(data.msg);
            // 跳转到另一个界面
            setTimeout(() => {
                location.href = "./index.html";
            }, 0)
        }
    });

});
