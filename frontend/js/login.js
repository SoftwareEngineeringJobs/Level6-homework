// 获取表单元素
const Admin_Login = document.getElementById("Admin-Login");
const Admin_Email = document.getElementById("Admin-E-mail");
const Admin_Passwd = document.getElementById("Admin-Passwd");

const Student_Login = document.getElementById("Student-Login");
const Student_IDCard = document.getElementById("Student-IDCard");
const Student_Passwd = document.getElementById("Student-Passwd");

const Teacher_Login = document.getElementById("Teacher-Login");
const Teacher_Email = document.getElementById("Teacher-E-mail");
const Teacher_Passwd = document.getElementById("Teacher-Passwd");


// 学生登录
Student_Login.addEventListener("submit", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();

    let object = {
        path: "/login",
        method: "GET",
        data: {
            idCard: Student_IDCard.value,
            password: Student_Passwd.value,
        }
    }

    student_requests(object)
        .then((data) => {
            if (data.code === Student_Login_Success.code) {
                swal(data.msg, data.msg + ' 等待跳转', 'success');
                // 跳转到另一个界面
                setTimeout(() => {
                    location.href = "./index.html";
                }, 1000)
            } else if (data.code === Student_Login_Failure.code) {
                swal('错误', data.msg, 'error');
            }
        });

});

// 管理员登录
Admin_Login.addEventListener("submit", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();

    let object = {
        path: "/login",
        method: "GET",
        data: {
            email: Admin_Email.value,
            password: Admin_Passwd.value,
        }
    }

    admin_requests(object)
        .then((data) => {
            if (data.code === Admin_Login_Success.code) {
                swal(data.msg, data.msg + ' 等待跳转', 'success');
                // 跳转到另一个界面
                setTimeout(() => {
                    location.href = "./admin-pages/admin-index.html";
                }, 1000)
            } else if (data.code === Admin_Login_Failure.code) {
                swal('错误', data.msg, 'error');
            }
        });

});

// 老师登录
Teacher_Login.addEventListener("submit", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();

    let object = {
        path: "/login",
        method: "GET",
        data: {
            email: Teacher_Email.value,
            password: Teacher_Passwd.value,
        }
    }

    teacher_requests(object)
        .then((data) => {
            if (data.code === Teacher_Login_Success.code) {
                swal(data.msg, data.msg + ' 等待跳转', 'success');
                // 跳转到另一个界面
                setTimeout(() => {
                    location.href = "./teacher-pages/teacher-index.html";
                }, 1000)
            } else if (data.code === Teacher_Login_Failure.code) {
                swal('错误', data.msg, 'error');
            }
        });

});
