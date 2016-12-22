package edu.hebtu.cpt.modle;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

import java.util.UUID;


public class User extends Model<User> {

    private static final Logger LOGGER = Logger.getLogger(User.class);

    public static final User userDao = new User();

    public Result regist(String name, String pswd) {
        try {
            new User().set("name", name).set("pswd", pswd).set("token", UUID.randomUUID().toString()).save();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new Result().success(false).message("注册失败").datas(e.getMessage());
        }
        return new Result().success(true).message("注册成功").datas(findById(name));
    }

    public Result login(String name, String pswd) {
        User user = new User();
        try {
            user = findByIdLoadColumns(name, "pswd");
            if (!user.getStr("pswd").equals(pswd)) {
                return new Result().success(false).message("用户名密码不对").datas(null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new Result().success(false).message("登陆失败").datas(e.getMessage());
        }
        return new Result().success(true).message("登陆成功").datas(findById(name));
    }

    public Result updateuser(String name, String token, String phone, String grade, String campus) {
        if (check(name, token)) {
            User user = findById(name);
            if (null != phone) {
                user.set("phone", phone);
            }
            if (null != grade) {
                user.set("grade", grade);
            }
            if (null != campus) {
                user.set("campus", campus);
            }
            user.update();
            return new Result().success(true).message("修改成功").datas(findById(name));
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Result changepswd(String name, String token, String pswd) {
        if (check(name, token)) {
            findById(name).set("pswd", pswd).update();
            return new Result().success(true).message("修改成功").datas(findById(name));
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Boolean check(String name, String token) {
        User user = new User();
        try {
            user = findByIdLoadColumns(name, "token");
            if (!user.getStr("token").equals(token)) {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    public Result getUserInfo(String name, String token, String username) {
        if (check(name, token)) {
            return new Result().success(true).message("查詢成功")
                    .datas(findById(username).set("pswd", "*****").set("token", "******"));
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }
}
