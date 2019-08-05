package cn.itcast.travel.utils;

import org.apache.commons.mail.HtmlEmail;

/**
 * 发送邮件工具类
 */
public final class MailUtil {


    /**
     * 发送邮件
     * 参数一:发送邮件给谁
     * 参数二:发送邮件的内容
     */
    public static void sendMail(String toEmail, String emailMsg) throws Exception {
        //创建HemlEmail对象，封装邮件发送参数
        HtmlEmail htmlEmail = new HtmlEmail();
        //你的邮件服务器的地址
        htmlEmail.setHostName("smtp.163.com");
        //注意：参数1--邮箱账号   参数2--邮件发送授权码
        htmlEmail.setAuthentication("17779609091@163.com", "hpt19950207");
        //设置发件人， 其中参数代表发件人邮件地址
        htmlEmail.setFrom("17779609091@163.com");
        //设置收件人，如果想添加多个收件人，将此语句多写几次即可。其中参数代表收件人邮件地址
        htmlEmail.addTo(toEmail);
        //设置邮件主题
        htmlEmail.setSubject("注册账号激活");
        //设置编码格式及正文内容
        htmlEmail.setCharset("gb2312");
        htmlEmail.setMsg(emailMsg);
        htmlEmail.send();//发送邮件


    }

    public static void main(String[] args) throws Exception {
        String toEmail = "17779609091@163.com";
        String emailMsg = "<h2>恭喜您！注册成功！</h2><a href='http://localhost:8080/userServlet?requestMethod=active'>请点击此处激活账号</a>";
        sendMail(toEmail,emailMsg);
        System.out.println("发送成功。。。");
    }
}









