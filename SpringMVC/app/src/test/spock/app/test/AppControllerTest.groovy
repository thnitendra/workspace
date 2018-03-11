package app.test

import app.controller.AppController
import app.dto.CreateOrgRequest
import app.model.CustomUserBean
import app.model.Organization
import app.repo.OrganizationRepository
import app.service.OrgService
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.apache.http.HttpStatus
import org.junit.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver
import spock.lang.Specification

import static org.hamcrest.Matchers.hasKey
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8

import javax.servlet.http.HttpServletRequest

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
 * http://henningpetersen.com/post/18/testing-spring-mvc-controllers-with-spock
 *
 */
@ContextConfiguration("classpath:/test-context.xml")
class AppControllerTest extends Specification {

    MockMvc mockMvc

    @Autowired
    AppController controller

    @Autowired
    OrganizationRepository orgRepo

    @Autowired
    @Qualifier("org")
    Organization org

    @Autowired
    @Qualifier("user")
    CustomUserBean user

    @Autowired
    @Qualifier("createOrgReq")
    CreateOrgRequest createOrgReq


    HttpServletRequest webRequest

    def setup() {
        initSec()

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

        //selectedOrgRequest = new SelectedOrgRequest(org.orgName)
    }

    def cleanup() {
        orgRepo.each { it ->
            it.deleteAll()
        }
    }

    public def initSec() {
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER")); // Hardcoded role in app.service.AuthenticationService.java

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    def "getOrgs Service Test: /getOrgs (Using in-memory mongo)"() {
        given:
            List orgs = new LinkedList();
            orgs << org
            controller.orgService.save(org)

        when:
            List respOrgs = controller.orgService.getOrgs()

        then:
            Assert.assertEquals(orgs.size(), respOrgs.size());
            Assert.assertEquals(orgs.get(0).name, respOrgs.get(0).name);
    }

    def "getOrgs (REST API) Controller Test: /getOrgs"() {
        given:
            //def sessionAttributes = [("SESSION_ATTR") : org.name];
            def sessionAttributes = null;

        when:
            ResultActions saveResp = getResponse("post", "/org/save", createOrgReq, null)
        then:
            saveResp.andExpect(status().isOk())

        when:
            ResultActions response = getResponse("get", "/getOrgs", null, sessionAttributes)
            response.andDo(MockMvcResultHandlers.print())
            // Can be printed like this also. But if printed lke this then .anExpect(content()....) fails
            //MvcResult respResult = response.andReturn()
            //String content = respResult.getResponse().getContentAsString();
            //println content
        then:
            //ResultActions result = response.andExpect(status().isOk())
            response.andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                    .andExpect(jsonPath('$', hasSize(1))) // size of array
                    .andExpect(jsonPath('$.*', hasSize(1))) // count of members of object
                    .andExpect(jsonPath('$.[0]', hasKey("name")))
                    .andExpect(jsonPath('$.[0].name', is("OrgName")))
    }

    def "orgs (MVC) Controller Test: /orgs"() {
        given:
            List orgs = new LinkedList();
            orgs << org
            controller.orgService.save(org)

        when:
            def response = mockMvc.perform(get("/orgs").contentType(APPLICATION_JSON))//getResponse("/orgs")

        then:
            response.andExpect(status().isOk())
                    .andExpect(view().name("viewOrganizationList"))
                    .andExpect(model().attribute("content", "viewOrganizationList"))
                    .andExpect(model().attribute("state", "clean"))
    }

    private ResultActions getResponse(String method, def url, def request, def sessionAttributes) {
        MockHttpServletRequestBuilder reqBld = (method == "post") ? post(url) : get(url)

        reqBld = reqBld.contentType(APPLICATION_JSON)

        if(request != null) {
            reqBld.content( new ObjectMapper().writeValueAsString(request) )
        }

        if(sessionAttributes != null) {
            reqBld.sessionAttrs(sessionAttributes);
        }

        return mockMvc.perform(reqBld);

    }

}
