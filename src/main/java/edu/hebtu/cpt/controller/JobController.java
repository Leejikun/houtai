package edu.hebtu.cpt.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import edu.hebtu.cpt.modle.Job;
import edu.hebtu.cpt.modle.User;
import org.apache.log4j.Logger;


public class JobController extends Controller {
    private static final Logger LOGGER = Logger.getLogger(JobController.class);

    @ActionKey("/addjob")
    public void addJob() {
        String name = getPara("name");
        String token = getPara("token");
        Job job = getModel(Job.class);
        renderJson(Job.jobDao.add(name, token, job).getMap());
    }

    @ActionKey("/updatejob")
    public void updateJob() {
        String name = getPara("name");
        String token = getPara("token");
        Job job = getModel(Job.class);
        renderJson(Job.jobDao.update(name, token, job).getMap());
    }

    @ActionKey("/deljob")
    public void del() {
        String name = getPara("name");
        String token = getPara("token");
        String id = getPara("id");
        renderJson(Job.jobDao.del(name, token, id).getMap());
    }

    @ActionKey("/joblist")
    public void joblist() {
        int page = getParaToInt("page");
        int size = getParaToInt("size");
        renderJson(Job.jobDao.jobList(page, size).getMap());
    }

    @ActionKey("/userjoblist")
    public void userJobList() {
        String name = getPara("name");
        int page = getParaToInt("page");
        int size = getParaToInt("size");
        renderJson(Job.jobDao.userJobList(name, page, size).getMap());
    }


    @ActionKey("/search")
    public void search() {
        String name = getPara("name");
        String token = getPara("token");
        String pswd = getPara("pswd");
        renderJson(User.userDao.changepswd(name, token, pswd).getMap());
    }

}
