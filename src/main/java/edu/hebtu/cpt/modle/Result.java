package edu.hebtu.cpt.modle;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Map<String, Object> map = new HashMap<String, Object>();

    public Result message(String message) {
        map.put("message", message);
        return this;
    }

    public Result datas(Object object) {
        map.put("datas", object);
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Result success(Boolean result) {
        if (result) {
            map.put("code", 0);
        } else {
            map.put("code", 1);
        }
        return this;
    }
}
