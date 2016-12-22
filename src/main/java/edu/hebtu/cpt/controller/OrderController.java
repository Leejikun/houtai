package edu.hebtu.cpt.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import edu.hebtu.cpt.modle.Order;
import org.apache.log4j.Logger;


public class OrderController extends Controller {
    private static final Logger LOGGER = Logger.getLogger(OrderController.class);

    @ActionKey("/acceptjob")
    public void acceptJob() {
        String name = getPara("name");
        String token = getPara("token");
        int id = getParaToInt("id");
        renderJson(Order.orderDao.acceptJob(name, token, id).getMap());
    }

    @ActionKey("/cancleorder")
    public void cancleOrder() {
        String name = getPara("name");
        String token = getPara("token");
        int id = getParaToInt("id");
        renderJson(Order.orderDao.cancleJob(name, token, id));
    }

    @ActionKey("/userlist")
    public void userList() {
        String name = getPara("name");
        String token = getPara("token");
        String id = getPara("id");
        int page = getParaToInt("page");
        int size = getParaToInt("size");
        renderJson(Order.orderDao.userList(name, token, id, page, size).getMap());
    }

}
