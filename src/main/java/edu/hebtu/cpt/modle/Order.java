package edu.hebtu.cpt.modle;

import com.jfinal.plugin.activerecord.Model;
import org.apache.log4j.Logger;

/**
 * Created by liu on 16-12-12.
 */
public class Order extends Model<Order> {
    private static final Logger LOGGER = Logger.getLogger(Order.class);

    public static final Order orderDao = new Order();

    public Result acceptJob(String name, String token, int id) {
        Job job = Job.jobDao.findById(id);
        Order order = findFirst("select * from `order` where user=?and jobid =?", name, id);
        if (User.userDao.check(name, token) && null != job && null == order) {
            if (job.get("status").equals("1")) {
                return new Result().success(false).message("非法请求").datas("");
            } else {
                new Order().set("user", name).set("jobid", id).save();
                return new Result().success(true).message("发送请求成功").datas("");
            }
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Result cancleJob(String name, String token, int id) {
        Order order = findFirst("select * from `order` where id =?", id);
        if (order != null && order.get("user").equals(name) && User.userDao.check(name, token)) {
            order.set("status", "1").update();
            return new Result().success(true).message("发送请求成功").datas("");
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }

    public Result userList(String name, String token, String id, int page, int size) {
        Job job = Job.jobDao.findById(id);
        if (job != null && User.userDao.check(name, token)) {
            return new Result().success(true).message("查询成功").datas(paginate(page, size, "select * ", "from `order` where jobid = ?", id));
        } else {
            return new Result().success(false).message("非法请求").datas("");
        }
    }
}
