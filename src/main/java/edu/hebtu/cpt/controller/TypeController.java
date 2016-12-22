package edu.hebtu.cpt.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import edu.hebtu.cpt.modle.Type;
import org.apache.log4j.Logger;


public class TypeController extends Controller {
    private static final Logger LOGGER = Logger.getLogger(TypeController.class);

    @ActionKey("/typeadd")
    public void typeAdd() {
        String name = getPara("name");
        String token = getPara("token");
        String typeName = getPara("typename");
        renderJson(Type.typeDao.add(name, token, typeName).getMap());
    }

    @ActionKey("/typedel")
    public void typeDel() {
        String name = getPara("name");
        String token = getPara("token");
        String typeId = getPara("typeid");
        renderJson(Type.typeDao.del(name, token, typeId).getMap());
    }

    @ActionKey("/typelist")
    public void typeList() {
        int page = getParaToInt("page");
        int size = getParaToInt("size");
        renderJson(Type.typeDao.typeList(page, size).getMap());
    }
}
