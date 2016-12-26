package edu.hebtu.cpt.aaaa;

import com.google.gson.Gson;

/**
 * Created by lenovo on 2016/12/20.
 */
public class Mainn {
    public static void main(String[] args) {
        String resutjson = "{\"code\":0,\"datas\":{\"totalRow\":2,\"pageNumber\":2,\"lastPage\":true,\"firstPage\":false,\"totalPage\":2,\"pageSize\":1,\"list\":[{\"request\":null,\"address\":null,\"num\":null,\"stoptime\":null,\"type\":1,\"rijieyuejie\":null,\"money\":null,\"name\":\"幫忙送外賣\",\"company\":null,\"id\":3,\"time\":null,\"thing\":null,\"user\":\"liu\",\"status\":\"0\"}]},\"message\":\"查询成功\"}";

         Gson gson = new Gson();
         Result result = gson.fromJson(resutjson,Result.class);


         if(result.getCode()==0){
             Page page = gson.fromJson(result.getDatas().toString(),Page.class);
             Job job = gson.fromJson(page.getList().toString().replace("[","").replace("]",""),Job.class);
             System.out.println(job.getName());
         }
    }
}
