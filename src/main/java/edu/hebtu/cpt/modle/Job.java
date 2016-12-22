package edu.hebtu.cpt.modle;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

/**
 * Created by liu on 16-12-12.
 */
public class Job extends Model<Job> {
    private static final Logger LOGGER = Logger.getLogger(Job.class);

    public static final Job jobDao = new Job();

    public Result add(String name, String token, Job job) {

        if (User.userDao.check(name, token)) {
            job.set("status", "0").set("user", name).save();
            return new Result().success(true).message("添加成功").datas(job.set("id", job.getInt("id")));
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Result update(String name, String token, Job job) {

        if (User.userDao.check(name, token) && job.get("user").equals(name)) {
            job.update();
            return new Result().success(true).message("添加成功").datas(job.set("id", job.getInt("id")));
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Result del(String name, String token, String id) {
        PropKit.use("sys_config.txt");
        String adminName = PropKit.get("adminName", "admin");
        Job job = findById(id);
        if (User.userDao.check(name, token) && null != job) {
            if (name.equals(adminName) || name.equals(job.getStr("user"))) {
                job.set("status", "1").update();
                return new Result().success(true).message("删除成功").datas("");
            } else {
                return new Result().success(false).message("非法请求").datas("");
            }
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Result jobList(int page, int size) {
        return new Result().success(true).message("查询成功").datas(paginate(page, size, "select * ", "from job where status = 0"));
    }

    public Result userJobList(String name, int page, int size) {
        return new Result().success(true).message("查询成功").datas(paginate(page, size, "select * ", "from job where user= ?", name));
    }
}
