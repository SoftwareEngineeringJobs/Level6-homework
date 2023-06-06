window.onload = function () {
    let adminInfo = {
        path: "/adminInfo",
        method: "GET",
    }
    admin_requests(adminInfo).then((data) => {
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
                            location.href = "./index.html";
                        }, 1000)
                    } else {
                        swal('错误', '错误', 'error');
                    }
                });
            });

            // 修改学生信息
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let admin = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + admin.adminId + "</td>";
                tableStr += "<td>" + admin.email + "</td>";
                tableStr += "<td>" + admin.name + "</td>";
                var gender;
                if (admin.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td>" + admin.authority + "</td>";
                tableStr += "<td><button onclick='resetAdmin(" + admin.adminId + ");'>Reset Password</button><button style=\"margin-left:20px\" onclick='deleteAdmin(" + admin.adminId + ");'>Delete Admin</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("adminTable").innerHTML += tableStr;
        }
    });
}

function search () {
    let adminId = document.getElementById("adminId");
    let email = document.getElementById("email");
    let adminName = document.getElementById("name");
    let gender = document.getElementById("gender");
    let authority = document.getElementById("authority");
    let temp = null;
    if (gender.value === "女")
        temp = true;
    else if (gender.value === "男")
        temp = false;
    let adminInfo = {
        path: "/adminInfo",
        method: "GET",
        data: {
            adminId: parseInt(adminId.value),
            email: email.value,
            name: adminName.value,
            gender: temp,
            authority: parseInt(authority.value),
        }
    }
    if (adminId.value === "")
        delete adminInfo["data"]["adminId"];
    if (temp === null)
        delete adminInfo["data"]["gender"];
    if (authority.value === "")
        delete adminInfo["data"]["authority"];
    let table = document.getElementById('adminTable');
    let rowNum = table.rows.length;
    // 删除原来的内容（第一行是搜索框保留）
    for (i = 1; i < rowNum; i++) {
        table.deleteRow(i);
        rowNum = rowNum - 1;
        i = i - 1;
    }
    admin_requests(adminInfo).then((data) => {
        // 显示查询到的教师信息
        if (data.code === Admin_Info.code) {
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let admin = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + admin.adminId + "</td>";
                tableStr += "<td>" + admin.email + "</td>";
                tableStr += "<td>" + admin.name + "</td>";
                let gender;
                if (admin.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td>" + admin.authority + "</td>";
                tableStr += "<td><button onclick='resetAdmin(" + admin.adminId + ");'>Reset Password</button><button style=\"margin-left:20px\" onclick='deleteAdmin(" + admin.adminId + ");'>Delete Admin</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("adminTable").innerHTML += tableStr;
        }
        // 跳出弹窗提示未搜索到，并刷新界面
        else if (data.code === Res_Not_Found.code) {
            swal("查询失败", '没有找到相关结果！', 'warning');
            setTimeout(() => {
                location.href = "admin-all.html";
            }, 1000)
        }
    });
}

function resetAdmin(resetId) {
    let result = {
        path: "/resetAdmin",
        method: "GET",
        data: {
            adminId: parseInt(resetId),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Reset_Passwd_Success.code) {
            swal('重整密码成功', '重置密码成功！', 'success');
        } else if (data.code === Reset_Passwd_Failure.code) {
            swal('重整密码失败', '重置密码失败！', 'warning');
        }
    });
}

function addAdmin() {
    let email = document.getElementById("email");
    let teacherName = document.getElementById("name");
    let gender = document.getElementById("gender");
    let authority = document.getElementById("authority");
    let temp = null;
    if (gender.value === "女")
        temp = true;
    else if (gender.value === "男")
        temp = false;
    if (email.value === "" || teacherName.value === "" || temp === null || authority.value === "") {
        swal('新增管理员失败', '请检查是否正确输入所有管理员信息！', 'error');
        return;
    }
    else if (parseInt(authority.value) < 1) {
        swal('新增管理员失败', '请检查是否正确输入所有管理员信息！', 'error');
        return;
    }
    let result = {
        path: "/signup",
        method: "POST",
        data: {
            email: email.value,
            name: teacherName.value,
            gender: temp,
            authority: parseInt(authority.value),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Signup_Success.code) {
            swal('新增管理员成功', '请尽快提醒管理员修改默认密码！', 'success');
            setTimeout(() => {
                location.href = "admin-all.html";
            }, 1000)
        }
        else if (data.code === Signup_Failure.code) {
            swal('新增管理员失败', '请检查是否正确输入所有管理员信息！', 'error');
        }
        else if (data.code === Admin_Authority_Low) {
            swal('新增管理员失败', '当前管理员权限不足！', 'error');
        }
    });
}

function deleteAdmin(deleteId) {
    let result = {
        path: "/deleteAdmin",
        method: "GET",
        data: {
            adminId: parseInt(deleteId),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Delete_Admin_Success.code) {
            swal('删除教师成功', '删除教师成功！', 'success');
            setTimeout(() => {
                location.href = "admin-all.html";
            }, 1000)
        }
        else if (data.code === Delete_Admin_Failure.code) {
            swal('删除教师失败', '删除教师失败！', 'error');
        }
    });
}