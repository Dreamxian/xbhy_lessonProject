package com.zhongyuxiang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongyuxiang.constants.SysConstant;
import com.zhongyuxiang.entity.User;
import com.zhongyuxiang.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @auth zyx
 * @date 2020/6/22
 * @Description
 */
@WebServlet("/weChat/*")
public class WeChatServlet extends BaseServlet {

    private UserService userService=new UserService();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties prop=new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        String appid=prop.getProperty("wx.AppID");
        String redirect_uri=prop.getProperty("wx.redirect_uri");

        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code"+
                "&appid=" + appid +
                "&redirect_uri=" + URLEncoder.encode(redirect_uri) +
                "&scope=snsapi_login";

        response.sendRedirect(url);
    }

    //授权回调
    public void callBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();

        //获取微信 信息
        Properties prop=new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appid=prop.getProperty("wx.AppID");
        String secret=prop.getProperty("wx.AppSecret");
        String code=request.getParameter("code");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +
                "&secret=" + secret +
                "&code=" +code+
                "&grant_type=authorization_code";
        //获取AccessToken、openId等数据
        JSONObject obj=auth(url);

        //获取微信用户信息
        String infoUrl="https://api.weixin.qq.com/sns/userinfo"+
                "?access_token="+obj.get("access_token")+
                "&openid="+obj.get("openid");

        JSONObject userObj=auth(infoUrl);
        String openId=userObj.get("openid").toString();
        User user=userService.getUserWXOpenId(openId);
        if (user==null){
            //首次登陆，进行注册
            user =new User();
            // 用户的头像
            user.setPic(userObj.getString("headimgurl"));
            // 性别
            String sex=userObj.getString("sex");
            user.setSex(sex==null?2:Integer.valueOf(sex));
            // 用户的昵称
            user.setRealName(userObj.getString("nickname"));
            // 随机用户名(11位随机字符串)
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));

            user.setWxOpenid(userObj.getString("openid"));
            userService.addUser(user);
        }
        //不是首次登陆，跳转至首页
        //登录信息放入session中
        session.setAttribute(SysConstant.SESSION_LOGIN, user);
        session.setMaxInactiveInterval(30 * 60);
        response.sendRedirect("/jsp/common/main.jsp");

    }

    private JSONObject auth(String url) {

        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(url);

            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();       // 获取响应数据(json)

            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));

                return JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
