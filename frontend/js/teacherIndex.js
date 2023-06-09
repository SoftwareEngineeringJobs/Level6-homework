window.onload = function () {
    let isLogin = {
        path: "/teacher/isLogin",
        method: "GET",
    }
    requests(isLogin).then((data) => {
        if (data.code === Teacher_Login_Success.code) {
            getTeacherInfo();
        }
    });
}