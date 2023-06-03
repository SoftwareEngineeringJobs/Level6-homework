let object = {
    path: "/getStudentInfo",
    method: "GET",
}

student_requests(object)
    .then((data) => {
        if (data.code === Get_Student_Info.code) {
            let div = document.querySelector("body > div > div.section.header > div.header-bottom-section > div > div > div > div.header-menu.d-none.d-lg-block > ul > div")
            div.innerHTML = "姓名: " + data.data.name + "<br> 学校: " + data.data.school
        }
    });