package live.baize.utils;

import live.baize.entity.Admin;
import live.baize.entity.Student;
import live.baize.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class SessionUtil {

    private final String Student_Cookie_Name = "Student_Cookie_Name";
    private final String Admin = "Admin";
    private final String Teacher = "Teacher";
    private final String Student = "Student";

    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    // ================================== 学生持久化登录 ================================== //

    /**
     * 添加cookies
     */
    public void setCookies(String email, String password) {
        email = Base64.getEncoder().encodeToString(email.getBytes());
        password = Base64.getEncoder().encodeToString(password.getBytes());
        Cookie cookie = new Cookie(Student_Cookie_Name, email + "#" + password);
        cookie.setMaxAge(2592000);
        response.addCookie(cookie);
    }

    /**
     * 删除cookies
     */
    public void delCookies() {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 从Cookies中拿到 Student
     */
    public Student getStudentFromCookies() {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        Student student = null;
        for (Cookie cookie : cookies) {
            if (Student_Cookie_Name.equals(cookie.getName())) {
                String[] params = cookie.getValue().split("#");
                student = new Student()
//                        .setEmail(new String(Base64.getDecoder().decode(params[0]), StandardCharsets.UTF_8))
                        .setPassword(new String(Base64.getDecoder().decode(params[1]), StandardCharsets.UTF_8));
            }
        }
        return student;
    }

    // ================================== 学生登录 ================================== //

    /**
     * 设置 Student 到Session中
     */
    public void setStudentToSession(Student student) {
        request.getSession().setAttribute(Student, student);
    }

    /**
     * 从Session中 拿到 Student
     */
    public Student getStudentFromSession() {
        return (Student) request.getSession().getAttribute(Student);
    }

    /**
     * 从Session中 删除 Student
     */
    public void delStudentFromSession() {
        request.getSession().setAttribute(Student, null);
    }

    // ================================== 管理员登录 ================================== //

    /**
     * 设置Admin到Session中
     */
    public void setAdminToSession(Admin admin) {
        request.getSession().setAttribute(Admin, admin);
    }

    /**
     * 从Session中 拿到admin
     */
    public Admin getAdminFromSession() {
        return (Admin) request.getSession().getAttribute(Admin);
    }

    /**
     * 从Session中 删除admin
     */
    public void delAdminFromSession() {
        request.getSession().setAttribute(Admin, null);
    }

    // ================================== 教师登录 ================================== //

    /**
     * 设置 Teacher 到Session中
     */
    public void setTeacherToSession(Teacher teacher) {
        request.getSession().setAttribute(Teacher, teacher);
    }

    /**
     * 从Session中 拿到 Teacher
     */
    public Teacher getTeacherFromSession() {
        return (Teacher) request.getSession().getAttribute(Teacher);
    }

    /**
     * 从Session中 删除 Teacher
     */
    public void delTeacherFromSession() {
        request.getSession().setAttribute(Teacher, null);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
