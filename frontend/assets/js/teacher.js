const myHeaders = new Headers();
myHeaders.append("User-Agent", "Apifox/1.0.0 (https://apifox.com)");

let getPaperAnswer = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
};
let examID = document.getElementById("PaperID");
const essay = document.getElementById("essay-answer");
const essay_answer = '作文题试卷情况';

essay.innerHTML = essay_answer;


fetch("https://www.baize.live/cet6/teacher/getPaperInfo?examId=<examId>", getPaperAnswer)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));