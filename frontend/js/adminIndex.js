window.onload = function () {
    let adminInfo = {
        path: "/admin/adminInfo",
        method: "GET",
    }
    requests(adminInfo).then((data) => {
        if (data.code === Admin_Info.code) {
            getAdminInfo();
        }
    });
}