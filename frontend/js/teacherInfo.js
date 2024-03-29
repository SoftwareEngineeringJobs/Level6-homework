window.onload = function () {
    let teacherInfo = {
        path: "/teacherInfo",
        method: "GET",
    }
    admin_requests(teacherInfo).then((data) => {
        if (data.code === Teacher_Info.code) {
            getAdminInfo();

            // 修改教师信息
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let teacher = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + teacher.teacherId + "</td>";
                tableStr += "<td>" + teacher.email + "</td>";
                tableStr += "<td>" + teacher.name + "</td>";
                let gender;
                if (teacher.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td><button onclick='resetTeacher(" + teacher.teacherId + ");'>Reset Password</button><button style=\"margin-left:20px\" onclick=\"deleteTeacher(" + teacher.teacherId + ");\">Delete Teacher</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("teacherTable").innerHTML += tableStr;
        }
    });
}

function search () {
    let teacherId = document.getElementById("teacherId");
    let email = document.getElementById("email");
    let teacherName = document.getElementById("name");
    let gender = document.getElementById("gender");
    let temp = null;
    if (gender.value === "女")
        temp = true;
    else if (gender.value === "男")
        temp = false;
    let teacherInfo = {
        path: "/teacherInfo",
        method: "GET",
        data: {
            teacherId: parseInt(teacherId.value),
            email: email.value,
            name: teacherName.value,
            gender: temp,
        }
    }
    if (teacherId.value === "")
        delete teacherInfo["data"]["teacherId"];
    if (temp === null)
        delete teacherInfo["data"]["gender"];
    let table = document.getElementById('teacherTable');
    let rowNum = table.rows.length;
    // 删除原来的内容（第一行是搜索框保留）
    for (i = 1; i < rowNum; i++) {
        table.deleteRow(i);
        rowNum = rowNum - 1;
        i = i - 1;
    }
    admin_requests(teacherInfo).then((data) => {
        // 显示查询到的教师信息
        if (data.code === Teacher_Info.code) {
            let tableStr = "";
            for (let i = 0; i < data.data.length; i++) {
                let teacher = data.data[i];
                tableStr += "<tr>";
                tableStr += "<td>" + teacher.teacherId + "</td>";
                tableStr += "<td>" + teacher.email + "</td>";
                tableStr += "<td>" + teacher.name + "</td>";
                let gender;
                if (teacher.gender)
                    gender = "女";
                else
                    gender = "男";
                tableStr += "<td>" + gender + "</td>";
                tableStr += "<td><button onclick='resetTeacher(" + teacher.teacherId + ");'>Reset Password</button><button style=\"margin-left:20px\" onclick=\"deleteTeacher(" + teacher.teacherId + ");\">Delete Teacher</button></td>"
                tableStr += "</tr>";
            }
            // 将拼接的字符串放进tbody里
            document.getElementById("teacherTable").innerHTML += tableStr;
        }
        // 跳出弹窗提示未搜索到，并刷新界面
        else if (data.code === Res_Not_Found.code) {
            swal("查询失败", '没有找到相关结果！', 'warning');
        }
    });
}

function resetTeacher(resetId) {
    let result = {
        path: "/resetTeacher",
        method: "GET",
        data: {
            teacherId: parseInt(resetId),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Reset_Passwd_Success.code) {
            swal('重整密码成功', '重置密码成功！', 'success');
        }
        else if (data.code === Reset_Passwd_Failure.code) {
            swal('重整密码失败', '重置密码失败！', 'warning');
        }
    });
}

function addTeacher() {
    let email = document.getElementById("email");
    let teacherName = document.getElementById("name");
    let gender = document.getElementById("gender");
    let temp = null;
    if (gender.value === "女")
        temp = true;
    else if (gender.value === "男")
        temp = false;
    if (email.value === "" || teacherName.value === "" || temp === null) {
        swal('新增教师失败', '请检查是否正确输入所有教师信息！', 'error');
        return;
    }
    let result = {
        path: "/addTeacher",
        method: "POST",
        data: {
            email: email.value,
            name: teacherName.value,
            gender: temp,
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Add_Teacher_Success.code) {
            swal('新增教师成功', '请尽快提醒教师修改默认密码！', 'success');
            setTimeout(() => {
                location.href = "admin-teacherInfo.html";
            }, 1000)
        }
        else if (data.code === Add_Teacher_Failure.code) {
            swal('新增教师失败', '请检查是否正确输入所有教师信息！', 'error');
        }
    });
}

function deleteTeacher(deleteId) {
    let result = {
        path: "/deleteTeacher",
        method: "GET",
        data: {
            teacherId: parseInt(deleteId),
        }
    }
    admin_requests(result).then((data) => {
        if (data.code === Delete_Teacher_Success.code) {
            swal('删除教师成功', '删除教师成功！', 'success');
            setTimeout(() => {
                location.href = "admin-teacherInfo.html";
            }, 1000)
        }
        else if (data.code === Delete_Teacher_Failure.code) {
            swal('删除教师失败', '删除教师失败！', 'error');
        }
    });
}