package app.test

import app.controller.AppController
import app.dto.CreateOrgRequest
import app.model.CustomUserBean
import app.model.Organization
import app.repo.OrganizationRepository
import app.service.OrgService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8

import javax.servlet.http.HttpServletRequest

/**
 * Created by nitendra.thakur on 2018/01/08.
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


    OrgService orgService

    ObjectMapper mapper = new ObjectMapper()
    def sessionAttributes

    HttpServletRequest webRequest

    def setup() {
        initSec()

        orgService = Stub(OrgService)
        controller.orgService = orgService
        mockMvc = init(controller)

        sessionAttributes = [("SESSION_ATTR") : org.name];

        //selectedOrgRequest = new SelectedOrgRequest(org.orgName)
    }

    def cleanup() {
        orgRepo.each { it ->
            it.deleteAll()
        }
    }

    // FIXME. Is it required even after wirign thru xmls. May be due to resource folder conflict in both main and test sources
    public def init(def controller) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/jsp/pages/");
        viewResolver.setSuffix(".jsp");

        def mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
        return mockMvc
    }

    public def initSec() {
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER")); // Hardcoded role in app.service.AuthenticationService.java

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private def getResponse(def url) {
        return mockMvc.perform(get(url).contentType(APPLICATION_JSON)).andReturn().response
    }

    private def getResponse(def url, def request) {
        def json = mapper.writeValueAsString(request)
        return mockMvc.perform(get(url).contentType(APPLICATION_JSON).content(json))
    }

    private def postResponse(def url, def request, def sessionAttributes) {
        def json = mapper.writeValueAsString(request)
        mockMvc.perform(post(url).contentType(APPLICATION_JSON).content(json).sessionAttrs(sessionAttributes))
    }

    def "retrieveAllOrganizations: /getOrgs"() {
        given:
            orgService.getOrgs() >> {
                List orgs = new LinkedList();
                orgs << org
                return orgs
            }

        when:
            def response = getResponse("/getOrgs")

        then:
            response.andExpect(status == 200)
                //.andExpect(view().name("viewOrganizationList"))
                //.andExpect(model().attribute("content", "viewOrganizationList"))
                //.andExpect(model().attribute("featureId", "organizations-management"))
    }
}
