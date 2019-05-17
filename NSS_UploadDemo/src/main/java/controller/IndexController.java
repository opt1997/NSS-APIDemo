package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 *
 * @author nss
 */
//@RestController
@Controller
@RequestMapping("/home")
public class IndexController {
    // @ResponseBody
    @RequestMapping("/index")
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("token",req.getParameter("token"));
        return "index";
    }

    @RequestMapping(value ="/index2")
    public String index2(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("token",req.getParameter("token"));
        return "index2";
    }
}
