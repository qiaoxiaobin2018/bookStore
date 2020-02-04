package test;

import org.junit.Test;

import java.text.MessageFormat;

public class Demo1 {
    @Test
    public void fun1(){
        /*
        * 测试占位符
        * 模板： 包含了占位符的字符串就是模板 ，例如： {0}或{1}错误！
        * 参数： 有多少占位符，给多少参数
        * */
        String s = MessageFormat.format("{0}或{1}错误！","用户名", "密码");
        System.out.println(s);
    }
    @Test
    public void fun2(){
        String s = "true";
        String ss = "true";
        System.out.println(s == ss);
    }

}
