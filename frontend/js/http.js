/*
* 传参：object
* obj.path: 请求路径
* obj.method: 请求方法 默认get
* obj.data: 请求参数 默认为空
* */
function requests(obj) {
    let method;
    if (obj.method) {
        obj.method = obj.method.toUpperCase();
        method = obj.method;
    } else {
        method = "GET";
    }

    const urlencoded = new URLSearchParams();
    // get请求的data参数拼接到url后面，post等其他请求的data参数放到body
    if (obj.method === "GET") {
        if (obj.data) {
            // URL编码
            let ec = encodeURIComponent;
            // 获取data键名
            let queryParams = Object.keys(obj.data).map(k => `${ec(k)}=${ec(obj.data[k])}`).join('&');
            if (queryParams) obj.path += `?${queryParams}`;
        }
    } else {
        for (const key in obj.data) {
            urlencoded.append(key, obj.data[key]);
        }
    }

    const headers = new Headers();
    headers.append("Content-Type", "application/x-www-form-urlencoded");

    const requestOptions = {
        method: method,
        headers: headers,
        redirect: 'follow'
    };

    if (method !== "GET" && method !== "HEAD") {
        requestOptions.body = urlencoded
    }

    // 发送请求并返回 promise 对象，注意 fetch不会拦截400/500等异常请求️，只有网络不通时才会失败
    return fetch(baseUrl + obj.path, requestOptions)
        .then((response) => response.json())
        .then((data) => {
            return new Promise(function (resolve, reject) {
                const global_code = parseInt(data.code.toString().substring(0, 3));
                if (global_code !== 400 && global_code !== 500) {
                    //响应码不是400，500，返回数据
                    resolve(data)
                } else {
                    reject("请求失败：" + data.code.toString() + data.msg);
                }
            })
        })
        .catch((error) => {
            console.log('error', error);
        })
}

function student_requests(obj) {
    obj.path = "/student" + obj.path

    // 发送请求并返回 promise 对象，注意 fetch不会拦截400/500等异常请求️，只有网络不通时才会失败
    return requests(obj)
        .then((data) => {
            return new Promise(function (resolve, reject) {
                // TODO: 登录判断
                if (data.code === Student_Not_Login.code) {
                    // 重定向
                    setTimeout(() => {
                        location.href = "./login-register.html";
                    }, 1000)
                } else {
                    resolve(data)
                }
            })
        })
        .catch((error) => {
            console.log('error', error);
        });
}

function admin_requests(obj) {
    obj.path = "/admin" + obj.path

    // 发送请求并返回 promise 对象，注意 fetch不会拦截400/500等异常请求️，只有网络不通时才会失败
    return requests(obj)
        .then((data) => {
            return new Promise(function (resolve, reject) {
                // TODO: 登录判断
                if (data.code === Admin_Not_Login.code) {
                    // 重定向
                    setTimeout(() => {
                        location.href = "./login-register.html";
                    }, 1000)
                } else {
                    resolve(data)
                }
            })
        })
        .catch((error) => {
            console.log('error', error);
        });
}

function teacher_requests(obj) {
    obj.path = "/teacher" + obj.path

    // 发送请求并返回 promise 对象，注意 fetch不会拦截400/500等异常请求️，只有网络不通时才会失败
    return requests(obj)
        .then((data) => {
            return new Promise(function (resolve, reject) {
                // TODO: 登录判断
                if (data.code === Teacher_Not_Login.code) {
                    // 重定向
                    setTimeout(() => {
                        location.href = "./login-register.html";
                    }, 1000)
                } else {
                    resolve(data)
                }
            })
        })
        .catch((error) => {
            console.log('error', error);
        });
}

