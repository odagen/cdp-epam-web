package com.epam.cdp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {

    // Returns view name
    @RequestMapping(method = RequestMethod.GET, path = "view")
    public String viewName() {
        return "test";
    }

    // Returns view with modelMap
    @RequestMapping(method = RequestMethod.GET, path = "modelmap")
    public String viewAndModelMap(ModelMap map) {
        map.addAttribute("message1", "Hello from view with modelMap!");
        map.addAttribute("message2", "Spring MVC");

        return "modelMap";
    }

    // Returns view with modelMap
    @RequestMapping(method = RequestMethod.GET, path = "modelview")
    public ModelAndView viewAndModelMap() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modelandview");
        modelAndView.setStatus(HttpStatus.ACCEPTED);

        Map<String, Object> map = new HashMap<>();
        map.put("message1", "Hello from modelAndView");
        map.put("message2", "Spring MVC");

        modelAndView.addAllObjects(map);

        return modelAndView;
    }

}
