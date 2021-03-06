package spring.controller;

import ch.qos.logback.core.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@Log
public class MvcController {
    @GetMapping("/to-baidu")
    public ModelAndView toBaidu() {
        ModelAndView modelAndView = new ModelAndView("redirect:https://www.baidu.com");
        return modelAndView;
    }

    @GetMapping(path = "/method")
    @RequiresPermissions(value = {"normal:read"})
    @ResponseBody
    public String method(WebRequest webRequest, ServletRequest request) {
        log.info("i am host:" + webRequest.getHeader("host"));
        log.info("i am request characterEncoding" + request.getCharacterEncoding());
        return "method";
    }

    @GetMapping("/set-cookie")
    public ResponseEntity<String> setCookie() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Cookie", "name=youyou");
        ResponseEntity<String> entity = new ResponseEntity<>("i am  cookie", headers, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/get-cookie")
    @ResponseBody
    public String getCookie(@CookieValue("name") String name) {
        log.info("get cookie name " + name);
        return "get-cookie";
    }


    /**
     * 只有当emitter complete的时候，才会返回
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/events")
    public ResponseBodyEmitter handle() throws IOException {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        emitter.send("hello");
        emitter.send("hello again");
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                emitter.complete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return emitter;
    }

    /**
     * 设置了跨域
     * {@link spring.mvcconfig.WebConfig}
     *
     * @return1
     */
    @PostMapping("/cors")
    @ResponseBody
    public String corsRequest() {
        return "i am cors";
    }

    @PostMapping("/xmlobject")
    @ResponseBody
    public XmlObject getXmlObject() {
        XmlObject xmlObject = new XmlObject();
        xmlObject.setName("heihei");
        xmlObject.setSex("nan");
        return xmlObject;
    }

    @GetMapping("/async-request")
    @ResponseBody
    public DeferredResult<String> asyncRequest() {
        DeferredResult<String> deferredResult = new DeferredResult<>();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deferredResult.setResult("i am async result");
        }).start();
        return deferredResult;
    }

    @GetMapping("/async-callable")
    @ResponseBody
    public Callable<String> asyncCallable() {
        return () -> "i am async callable";

    }

    @Setter
    @Getter
    class XmlObject {
        private String name;

        private String sex;
    }
}
