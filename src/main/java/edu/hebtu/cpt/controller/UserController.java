package edu.hebtu.cpt.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import edu.hebtu.cpt.modle.User;
import org.apache.log4j.Logger;


public class UserController extends Controller {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @ActionKey("/regist")
    public void regist() {
        String name = getPara("name");
        String pswd = getPara("pswd");
        renderJson(User.userDao.regist(name, pswd).getMap());
    }

    @ActionKey("/login")
    public void login() {
        String name = getPara("name");
        String pswd = getPara("pswd");
        renderJson(User.userDao.login(name, pswd).getMap());
    }

    @ActionKey("/updateuser")
    public void updateuser() {
        String name = getPara("name");
        String token = getPara("token");
        String phone = getPara("phone");
        String grade = getPara("grade");
        String campus = getPara("campus");
        renderJson(User.userDao.updateuser(name, token, phone, grade, campus).getMap());
    }

    @ActionKey("/changepswd")
    public void changepswd() {
        String name = getPara("name");
        String token = getPara("token");
        String pswd = getPara("pswd");
        renderJson(User.userDao.changepswd(name, token, pswd).getMap());
    }

    @ActionKey("/getuserinfo")
    public void getUserInfo() {
        String name = getPara("name");
        String token = getPara("token");
        String username= getPara("username");
        renderJson(User.userDao.getUserInfo(name, token, username).getMap());
    }
}
