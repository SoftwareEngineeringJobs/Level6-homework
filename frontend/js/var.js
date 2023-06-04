let baseUrl = 'https://www.baize.live/cet6'
let studentUrl = baseUrl + '/student'
let adminURL = baseUrl + '/admin'

class ResponseEnum {
    msg;
    code;

    constructor(code, msg) {
        this.msg = msg;
        this.code = code;
    }
}

// ============================================= 通用 ============================================= //
Hello_World = new ResponseEnum(100000, "你好, 世界! 你好, 朋友!");
TEST_TEST = new ResponseEnum(100001, "测试返回值");

// ============================================= AdminController ============================================= //
Admin_Login_Failure = new ResponseEnum(200000, "邮箱或密码错误");
Admin_Login_Success = new ResponseEnum(200001, "登录成功");
Signup_Failure = new ResponseEnum(200010, "注册失败");
Signup_Success = new ResponseEnum(200011, "注册成功");
Stu_Info = new ResponseEnum(200021, "考生信息");
Admin_Logout_Success = new ResponseEnum(200031, "退出登录成功");
Admin_Not_Login = new ResponseEnum(200040, "管理员没有登录");
Admin_Authority_Low = new ResponseEnum(200050, "管理员权限不足");
Exam_Info = new ResponseEnum(200061, "考试信息");
Publish_Exam_Failure = new ResponseEnum(200070, "发布考试信息失败");
Publish_Exam_Success = new ResponseEnum(200071, "发布考试信息成功");
Modify_Exam_Failure = new ResponseEnum(200080, "修改考试信息失败");
Modify_Exam_Success = new ResponseEnum(200081, "修改考试信息成功");
Paper_Info = new ResponseEnum(200091, "试卷信息");
Upload_Paper_Failure = new ResponseEnum(200100, "上传试卷信息失败");
Upload_Paper_Success = new ResponseEnum(200101, "上传试卷信息成功");
Modify_Paper_Failure = new ResponseEnum(200110, "上传试卷信息失败");
Modify_Paper_Success = new ResponseEnum(200111, "上传试卷信息成功");
Teacher_Info = new ResponseEnum(200121, "教师信息");
Add_Teacher_Failure = new ResponseEnum(200130, "添加教师失败");
Add_Teacher_Success = new ResponseEnum(200131, "添加教师成功");
Delete_Teacher_Failure = new ResponseEnum(200140, "删除教师失败");
Delete_Teacher_Success = new ResponseEnum(200141, "删除教师成功");
Reset_Passwd_Failure = new ResponseEnum(200150, "重置密码失败");
Reset_Passwd_Success = new ResponseEnum(200151, "重置密码成功");

// ============================================= StudentController ============================================ //
Student_Not_Login = new ResponseEnum(2001010, "学生没有登录");
Student_Login_Failure = new ResponseEnum(2001020, "邮箱或密码错误");
Student_Login_Success = new ResponseEnum(2001021, "登录成功");
Register_Failure = new ResponseEnum(2001030, "注册失败");
Register_Success = new ResponseEnum(2001031, "注册成功");
Student_Has_Registered = new ResponseEnum(2001032, "已经注册过");
Student_Logout_Success = new ResponseEnum(2001041, "退出登录成功");
Lookup_Exam_Success = new ResponseEnum(2001051, "查看场次成功");
Has_Registration = new ResponseEnum(2001050, "已经报过名了");
Registration_Success = new ResponseEnum(2001051, "报名成功");
Not_Registration = new ResponseEnum(2001061, "没有报名");
Test_Time_Not_Arrived = new ResponseEnum(2001062, "考试时间未到");
Not_Test_Time_Range = new ResponseEnum(2001062, "不在考试时间范围");
Get_PaperInfo_Success = new ResponseEnum(2001071, "获得试卷信息成功");
Save_Answer_Success = new ResponseEnum(2001081, "保存答案成功");
Not_Score_Time_Range = new ResponseEnum(2001090, "不在查分时间段");
Lookup_Score_Success = new ResponseEnum(2001091, "查询分数成功");
Get_Student_Info = new ResponseEnum(2001101, "获取用户信息成功");

// ============================================= TeacherController ============================================= //
Teacher_Not_Login = new ResponseEnum(2002010, "老师没有登录");
Teacher_Login_Failure = new ResponseEnum(2002020, "老师登录失败");
Teacher_Login_Success = new ResponseEnum(2002021, "老师登录成功");
Without_Paper = new ResponseEnum(2002030, "试卷判完了");
Get_Paper_Success = new ResponseEnum(2002031, "获得试卷成功");
Scoring_Success = new ResponseEnum(2002041, "打分成功");

// 客户端异常
Not_ThisFile = new ResponseEnum(400050, "没有这样的文件");
Param_Check = new ResponseEnum(400000, "参数有误, 请检查");
Param_Missing = new ResponseEnum(400010, "参数缺少, 请检查");
Method_Not_Supported = new ResponseEnum(400020, "请求方法不支持");
Not_IsMultipart = new ResponseEnum(400030, "不是一个Multipart 请求");
Request_Part_Missing = new ResponseEnum(400040, "请求体参数缺少, 请检查");
Res_Not_Found = new ResponseEnum(404010, "没有找到相关结果");
File_Type_Error = new ResponseEnum(400010, "文件类型不支持");
File_Is_Null = new ResponseEnum(400020, "文件为NULL");
FileName_Is_Null = new ResponseEnum(400030, "文件名为NULL");

// 服务器异常
SYSTEM_UNKNOWN = new ResponseEnum(500000, "系统繁忙");
Write_File_Failure = new ResponseEnum(500010, "写文件失败");
Create_Dir_Failure = new ResponseEnum(500020, "创建目录失败");
Delete_File_Failure = new ResponseEnum(500030, "删除文件失败");

