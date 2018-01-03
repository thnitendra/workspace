package app.controller; /**
 * Created by nitendra.thakur on 2017/12/31.
 */

import app.common.AppConfigs;
import app.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Slf4j(topic = "APP_LOGGER")
@Controller
public class AppController {
    @Autowired
    AppConfigs prop;

    @Autowired
    OrgService consumerApiReqService;

//    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    //@ResponseBody
//    public String viewOrganizations(
//            @RequestParam(required=false) String type,
//            Model model) {
//        model.addAttribute("content", "viewOrganizationList");
//        model.addAttribute("operation", operation);
//        model.addAttribute(FEATURE_ID, ORGANIZATION_MANAGEMENT);
//        return "viewOrganizationList";
//    }

//    @RequestMapping(value = "/users", method = RequestMethod.GET)
    //@ResponseBody
//    public String viewOrganizations(
//            @PathVariable(value = "type") String type,
//            Model model) {
//        model.addAttribute("content", "viewOrganizationList");
//        model.addAttribute("operation", operation);
//        model.addAttribute(FEATURE_ID, ORGANIZATION_MANAGEMENT);
//        return "viewOrganizationList";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        log.debug("index() is executed!");

        model.put("title", "APP-Title");
        model.put("msg", "APP-Msg");

        return "index";
    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        log.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", "App-Title");
        model.addObject("msg", "App-Msg");

        return model;

    }


}
