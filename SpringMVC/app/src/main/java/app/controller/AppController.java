package app.controller; /**
 * Created by nitendra.thakur on 2017/12/31.
 */

import app.common.AppConfigs;
import app.common.RequestFilter;
import app.dto.CreateOrgRequest;
import app.model.Organization;
import app.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j(topic = "APP_LOGGER")
@Controller
public class AppController {
    @Autowired
    AppConfigs prop;

    @Autowired
    OrgService orgService;

    @RequestMapping(value = "/orgs", method = RequestMethod.GET)
    //@ResponseBody
    public String viewOrganizations(
            @RequestParam(required=false) String type,
            Model model) {
        model.addAttribute("content", "viewOrganizationList");
        model.addAttribute("state", "clean");
        return "viewOrganizationList";
    }

    @RequestMapping(value = "/getOrgs", method = RequestMethod.GET)
    public ResponseEntity<Object> retrieveAllOrganizations() {
        return ResponseEntity.ok(orgService.getOrgs());
    }

    @RequestMapping(value = "/orgs/{name}", method = RequestMethod.GET)
    public ModelAndView viewOrganizations(@PathVariable(value = "name") String name) {
        ModelAndView model = new ModelAndView();
        model.setViewName("viewOrganizationList");
        model.addObject("org", orgService.getOrgs().get(0));
        return model;
    }

    @RequestMapping(value = "/org/create", method = GET)
    public String createOrganization(Model model) {
        model.addAttribute("content", "createOrg");
        return "createOrganization";
    }

    @RequestMapping(value = "/org/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> saveOrganization(@Valid @RequestBody CreateOrgRequest orgReq, BindingResult result) {
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                log.error(error.getField() + " - " + error.getDefaultMessage());
            }
            throw new RuntimeException(result.getAllErrors().toString());
        }

        Organization org = new Organization();
        org.setName(orgReq.getName());
        org.setType(orgReq.getType());
        org.setCreateTime(new Date().getTime());
        org.setCreatedBy(RequestFilter.getUser().getUsername());
        orgService.save(org);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        log.debug("index() is executed!");

        model.put("title", "APP-Title");
        model.put("msg", "APP-Msg");

        return "index";
    }

    @RequestMapping(value = "/login", method = GET)
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        log.debug("login page");
        if (error != null && Integer.parseInt(error) == 1) {
            model.addAttribute("error", "Login Fail");
        }
        return "login";
        //return "redirect:/perform_login";
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
