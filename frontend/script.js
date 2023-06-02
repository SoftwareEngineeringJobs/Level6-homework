// 获取表单元素
const form = document.getElementById("register-form");
const id = document.getElementById("id");
const name = document.getElementById("name");
const gender = document.getElementById("gender");
const school = document.getElementById("school");
const password = document.getElementById("password");

// 添加表单提交事件监听器
form.addEventListener("submit", (event) => {
    // 阻止默认行为，如刷新页面
    event.preventDefault();

    // 创建一个对象，包含表单中的数据
    const data = {
        idCard: id.value,
        name: name.value,
        // 将性别的值转换为布尔类型
        gender: Boolean(Number(gender.value)),
        school: school.value,
        password: password.value,
    };

    // 创建一个选项对象，指定请求方法为POST，请求头为JSON格式，请求体为字符串化的数据对象
    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    };

    // 使用fetch函数发送POST请求到后端的/register路由，并获取响应
    fetch(studentUrl + "/register", options)
        .then((response) => response.json())
        .then((data) => {
            if (data.code === Register_Success.code) {
                // TODO: 注册成功并跳转
                alert(data.msg);
                // 注册成功
                setTimeout(() => {
                    location.href = "./login-register.html";
                }, 1000)
            } else {
                alert(data.msg);
            }
        })
        .catch((error) => {
            // 如果发生网络错误，显示错误
            alert(error);
        });
});
