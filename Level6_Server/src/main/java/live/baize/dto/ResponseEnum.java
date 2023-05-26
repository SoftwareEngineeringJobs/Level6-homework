package live.baize.dto;

import lombok.Getter;


@Getter
public class ResponseEnum {
    /**
     * 解释响应码
     * 200 * 01 0
     * 200  表示 成功
     * *    对应 不同的controller
     * 01   对应 不同的业务
     * 0/1  表示 业务执行成功与否
     * 400 客户端异常
     * 500 服务端异常
     */

    // ============================================= 通用 ============================================= //
    public static final ResponseEnum Hello_World = new ResponseEnum(100000, "你好, 世界! 你好, 朋友!");
    public static final ResponseEnum TEST_TEST = new ResponseEnum(100001, "测试返回值");

    // ============================================= AdminController ============================================= //
    public static final ResponseEnum Admin_Login_Failure = new ResponseEnum(200000, "邮箱或密码错误");
    public static final ResponseEnum Admin_Login_Success = new ResponseEnum(200001, "登录成功");
    public static final ResponseEnum Signup_Failure = new ResponseEnum(200010, "注册失败");
    public static final ResponseEnum Signup_Success = new ResponseEnum(200011, "注册成功");
    public static final ResponseEnum Stu_Info = new ResponseEnum(200021, "考生信息");
    public static final ResponseEnum Admin_Logout_Success = new ResponseEnum(200031, "退出登录成功");
    public static final ResponseEnum Admin_Not_Login = new ResponseEnum(200040, "管理员没有登录");
    public static final ResponseEnum Admin_Authority_Low = new ResponseEnum(200050, "管理员权限不足");
    public static final ResponseEnum Exam_Info = new ResponseEnum(200061, "考试信息");
    public static final ResponseEnum Publish_Exam_Failure = new ResponseEnum(200070, "发布考试信息失败");
    public static final ResponseEnum Publish_Exam_Success = new ResponseEnum(200071, "发布考试信息成功");
    public static final ResponseEnum Modify_Exam_Failure = new ResponseEnum(200080, "发布考试信息失败");
    public static final ResponseEnum Modify_Exam_Success = new ResponseEnum(200081, "发布考试信息成功");

    // ============================================= StudentController ============================================ //
    public static final ResponseEnum Student_Not_Login = new ResponseEnum(2001010, "学生没有登录");
    public static final ResponseEnum Student_Login_Failure = new ResponseEnum(2001020, "邮箱或密码错误");
    public static final ResponseEnum Student_Login_Success = new ResponseEnum(2001021, "登录成功");
    public static final ResponseEnum Register_Failure = new ResponseEnum(2001030, "注册失败");
    public static final ResponseEnum Register_Success = new ResponseEnum(2001031, "注册成功");
    public static final ResponseEnum Student_Has_Registered = new ResponseEnum(2001032, "已经注册过");
    public static final ResponseEnum Student_Logout_Success = new ResponseEnum(2001041, "退出登录成功");
    public static final ResponseEnum Lookup_Exam_Success = new ResponseEnum(2001051, "查看场次成功");
    public static final ResponseEnum Has_Registration = new ResponseEnum(2001050, "已经报过名了");
    public static final ResponseEnum Registration_Success = new ResponseEnum(2001051, "报名成功");
    public static final ResponseEnum Not_Registration = new ResponseEnum(2001061, "没有报名");
    public static final ResponseEnum Test_Time_Not_Arrived = new ResponseEnum(2001062, "考试时间未到");
    public static final ResponseEnum Get_PaperInfo_Success = new ResponseEnum(2001062, "获得试卷信息成功");

    // ============================================= TeacherController ============================================= //
    public static final ResponseEnum Teacher_Not_Login = new ResponseEnum(2002010, "老师没有登录");


    // 客户端异常
    public static final ResponseEnum Not_ThisFile = new ResponseEnum(400050, "没有这样的文件");
    public static final ResponseEnum Param_Check = new ResponseEnum(400000, "参数有误, 请检查");
    public static final ResponseEnum Param_Missing = new ResponseEnum(400010, "参数缺少, 请检查");
    public static final ResponseEnum Method_Not_Supported = new ResponseEnum(400020, "请求方法不支持");
    public static final ResponseEnum Not_IsMultipart = new ResponseEnum(400030, "不是一个Multipart 请求");
    public static final ResponseEnum Request_Part_Missing = new ResponseEnum(400040, "请求体参数缺少, 请检查");
    public static final ResponseEnum Res_Not_Found = new ResponseEnum(404010, "没有找到相关结果");

    public static final ResponseEnum File_Type_Error = new ResponseEnum(400010, "文件类型不支持");
    public static final ResponseEnum File_Is_Null = new ResponseEnum(400020, "文件为NULL");
    public static final ResponseEnum FileName_Is_Null = new ResponseEnum(400030, "文件名为NULL");

    // 服务器异常
    public static final ResponseEnum SYSTEM_UNKNOWN = new ResponseEnum(500000, "系统繁忙");
    public static final ResponseEnum Write_File_Failure = new ResponseEnum(500010, "写文件失败");
    public static final ResponseEnum Create_Dir_Failure = new ResponseEnum(500020, "创建目录失败");
    public static final ResponseEnum Delete_File_Failure = new ResponseEnum(500030, "删除文件失败");

    final String msg;
    final Integer code;

    ResponseEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

}
