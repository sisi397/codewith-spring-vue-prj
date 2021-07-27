package com.codewith.codewith.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//public class controller {
//
//    path.resolve(__dirname, '../main/webapp/front/index.html')
//
//    @GetMapping(value = "/vue/{path:.+}")
//    public String frontAccess(@PathVariable("path") String path) throws Exception {
//
//        String returnUrl = "";
//        try {
//            returnUrl = "/index";
//        } catch (Exception e) {
//            logger.error("[frontAccess] Exception :", e);
//        }
//        return returnUrl;
//    }
//
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//
//        internalResourceViewResolver.setPrefix("/front");
//        internalResourceViewResolver.setSuffix(".html");
//        internalResourceViewResolver.setCache(false);
//        internalResourceViewResolver.setOrder(2);
//
//        return internalResourceViewResolver;
//    }
//
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/front/**").addResourceLocations("/front/").setCachePeriod(0);
//    }
//
//}

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class controller implements ErrorController {
    @GetMapping("/error")
    public String redirectRoot(){
        return "index.html";
    }

    public String getErrorPath(){
        return "/error";
    }

    @GetMapping("/loginPage")
    public String dispLogin(){
        System.out.println("dd");
        return "login.html";
    }
    @GetMapping("/loginfail")
    public String dispLogin2(){
        System.out.println("ff");
        return "login.html";
    }
}