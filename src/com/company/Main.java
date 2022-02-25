package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "song");
        map.put("age", 18);
        map.put("sex", "man");
        String s = parseTemplate("My name is ${name}, i am ${age} years old! ${sayHai}", map);
        System.out.println(s);
    }

    /**
     * 模板解析
     *
     * @param template   模板字符串 如：My name is ${name}, i am ${age} years old! ${sayHai}
     * @param properties map接口的集合，如： {name: "azi", age: 18, sex: "man"}
     * @return: 解析后的字符串，如： My name is azi, i am 18 years old!
     * @author: 若非
     * @date: 2021/8/19 3:03
     */
    public static String parseTemplate(String template, Map properties) {
        if (template == null || template.isEmpty() || properties == null) {
            return template;
        }
        String r = "\\$\\{([^\\}]+)\\}";
        Pattern pattern = Pattern.compile(r);
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            String group = matcher.group();
            Object o = properties.get(group.replaceAll(r, "$1"));
            if (o != null) {
                template = template.replace(group, String.valueOf(o));
            } else {
                template = template.replace(group, "");
            }
        }
        return template;
    }
}
