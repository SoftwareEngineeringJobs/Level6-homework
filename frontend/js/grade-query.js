window.onload = function () {
    let lookupScore = {
        path: "/lookupScore",
        method: "GET",
    }
    student_requests(lookupScore).then((data) => {
        if (data.code === Lookup_Score_Success.code) {
            getStudentInfo();

            let chart = echarts.init(document.getElementById('chart'));
            let option = {
                xAxis: {
                    type: 'category',
                    data: ['写作&翻译', '听力', '阅读']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [data.data.scoreWrite, data.data.scoreListen, data.data.scoreRead],
                        type: 'bar'
                    }
                ]
            };
            chart.setOption(option);
            let label_h4 = document.getElementById('sum-score')
            label_h4.innerHTML = "您本次CET-6考试总成绩为: " + (data.data.scoreWrite + data.data.scoreListen + data.data.scoreRead)
        } else {
            swal('查询失败', '您还没有成绩！', 'warning');
            setTimeout(() => {
                location.href = "./index.html";
            }, 1000);
        }
    });
}