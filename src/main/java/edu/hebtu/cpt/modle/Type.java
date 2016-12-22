package edu.hebtu.cpt.modle;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

public class Type extends Model<Type> {
    private static final Logger LOGGER = Logger.getLogger(Type.class);

    public static final Type typeDao = new Type();

    public Result add(String name, String token, String typeName) {

        PropKit.use("sys_config.txt");
        String adminName = PropKit.get("adminName", "admin");

        if (name.equals(adminName) && User.userDao.check(name, token)) {
            new Type().set("name", typeName).save();
        } else {
            return new Result().success(false).message("需要管理员权限").datas(null);
        }
        return new Result().success(true).message("添加成功").datas(find("select * from type"));
    }

    public Result del(String name, String token, String typeId) {
        PropKit.use("sys_config.txt");
        String adminName = PropKit.get("adminName", "admin");

        if (name.equals(adminName) && User.userDao.check(name, token)) {
            if (deleteById(typeId)) {
                return new Result().success(true).message("删除成功").datas(find("select * from type"));
            } else {
                return new Result().success(false).message("删除失败，无该ID").datas(find("select * from type"));
            }
        } else {
            return new Result().success(false).message("需要管理员权限").datas(null);
        }
    }

    public Result typeList(int page, int size) {
        return new Result().success(true).message("查询成功").datas(paginate(page, size, "select * ", "from type"));
    }
}
