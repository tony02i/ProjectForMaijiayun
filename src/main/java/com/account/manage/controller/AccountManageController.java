package com.account.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.account.manage.bean.DatatablesResult;
import com.account.manage.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fyunli on 16/4/1.
 */
@Controller
public class AccountManageController {

    @Value("${application.message:message}")
    private String message = "message";

    @Autowired
    private UserRepository userRepository;



    @RequestMapping("/save")
    @ResponseBody
    public Object save(User user) {
        // {"success":true,"data":{"id":"","name":"","pid":""}}成功 {"success":false}失败

        Map<String, Object> jsonMap = new HashMap<String, Object>();

        // 调用service层的方法保存许可
        try {
            this.userRepository.create(user);
            jsonMap.put("success", true);

        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        return jsonMap;
    }
    @RequestMapping("/update")
    @ResponseBody
    public Object update(User user) {
        // {"success":true,"data":{"id":"","name":"","pid":""}}成功 {"success":false}失败

        Map<String, Object> jsonMap = new HashMap<String, Object>();

        // 调用service层的方法保存许可
        try {
            this.userRepository.update(user);
            jsonMap.put("success", true);

        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        return jsonMap;
    }


    @RequestMapping("/index")
    public String index(ModelMap model) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        model.put("formaTime", formatter.format(new Date()));
        model.put("time", new Date());
        return "index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public DatatablesResult getList(ModelMap model) {
        DatatablesResult data = new DatatablesResult();
        List<List<Object>> list = new ArrayList<List<Object>>();
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            ArrayList<Object> listOfUser = new ArrayList<Object>();
            String checkBox = " <input type='checkbox' name='id' onclick='controlTHeadCheckbox();' value='"
                    + user.getId() + "'/>";

            listOfUser.add(checkBox);
            listOfUser.add(user.getId());
            listOfUser.add(user.getName());
            int i = user.getType();
            switch (i) {
            case 1:
                listOfUser.add("普通用户");
                break;
            case 2:
                listOfUser.add("会员");
                break;
            case 3:
                listOfUser.add("超级用户");
                break;
            default:
                listOfUser.add("未知类型");
                break;

            }
            int j = user.getState();
            switch (j) {
            case 0:
                listOfUser.add("已封存");
                break;
            case 1:
                listOfUser.add("已启用");

                break;
            default:
                listOfUser.add("未知状态");
                break;
            }
            listOfUser.add(user.getCreateTime());
            listOfUser.add(user.getLastLoginTime());
            listOfUser.add(user.getNote());
            list.add(listOfUser);
        }

        data.setData(list);
        return data;

    }
    
    @RequestMapping("/search")
    @ResponseBody
    public List search(ModelMap model,String id,String name,String time,String type) {
        List<List<Object>> list = new ArrayList<List<Object>>();
        List<User> users;
        try {
            users = this.userRepository.findUserByExample(id, name, Integer.valueOf(type), time);
        
        for (User user : users) {
            ArrayList<Object> listOfUser = new ArrayList<Object>();
            String checkBox = " <input type='checkbox' name='id' onclick='controlTHeadCheckbox();' value='"
                    + user.getId() + "'/>";

            listOfUser.add(checkBox);
            listOfUser.add(user.getId());
            listOfUser.add(user.getName());
            int i = user.getType();
            switch (i) {
            case 1:
                listOfUser.add("普通用户");
                break;
            case 2:
                listOfUser.add("会员");
                break;
            case 3:
                listOfUser.add("超级用户");
                break;
            default:
                listOfUser.add("未知类型");
                break;

            }
            int j = user.getState();
            switch (j) {
            case 0:
                listOfUser.add("已封存");
                break;
            case 1:
                listOfUser.add("已启用");

                break;
            default:
                listOfUser.add("未知状态");
                break;
            }
            
            listOfUser.add(user.getCreateTime());
            listOfUser.add(user.getLastLoginTime());
            listOfUser.add(user.getNote());
            list.add(listOfUser);
        }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        
        return list;

    }
    
    @RequestMapping("/getbyid")
    @ResponseBody
    public Object getById(String id) {
        // {"success":true}不重复 校验通过 {"success":false}
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // 根据许可名称和父许可id获取许可对象
        try {
            User user = this.userRepository.findUserById(id);
            if (user == null) {
                // 不重复 校验通过
                jsonMap.put("success", true);
            } else {
                // 重复 校验失败
                jsonMap.put("success", false);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            jsonMap.put("success", true);
        }
        return jsonMap;
    }
    @RequestMapping("/editgetbyid")
    @ResponseBody
    public Object editGetById(String id,String oldid) {
        // {"success":true}不重复 校验通过 {"success":false}
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // 根据许可名称和父许可id获取许可对象
        try {
           
            User user = this.userRepository.findUserById(id);
            if (user == null||oldid.equals(user.getId())) {
                // 不重复 校验通过
                jsonMap.put("success", true);
            } else {
                // 重复 校验失败
                jsonMap.put("success", false);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            jsonMap.put("success", true);
        }
        return jsonMap;
    }



    @RequestMapping("/new")
    public String accountCreate(ModelMap model) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        model.put("formaTime", formatter.format(new Date()));
        model.put("time", new Date());
        model.put("message", message);
        return "new";
    }

    @RequestMapping("/edit")
    public String accountEdit(ModelMap model, String id) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        model.put("formaTime", formatter.format(new Date()));
        model.put("time", new Date());
        model.put("time", new Date());
        model.put("message", message);
        User user = this.userRepository.findUserById(id);
        model.put("id", user.getId());
        model.put("name", user.getName());
        model.put("type", user.getType());
        model.put("state", user.getState());
        model.put("note", user.getNote());
        model.put("oldid", user.getId());
       

        return "edit";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String[] ids) {
        // {"success":true}成功 {"success":false}失败
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            this.userRepository.delete(ids);
            jsonMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        return jsonMap;
    }

    @RequestMapping("/on")
    @ResponseBody
    public Object on(String[] ids) {
        // {"success":true}成功 {"success":false}失败
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            this.userRepository.on(ids);
            jsonMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        return jsonMap;
    }

    @RequestMapping("/off")
    @ResponseBody
    public Object off(String[] ids) {
        // {"success":true}成功 {"success":false}失败
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            this.userRepository.off(ids);
            jsonMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        return jsonMap;
    }
}
