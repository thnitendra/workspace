package app.service;

import app.model.Organization;
import app.repo.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nitendra.thakur on 2017/12/31.
 */

@Service
public class OrgService {
    @Autowired
    OrganizationRepository organizationRepo;

    public List<Organization> getOrgs() {
        //log.debug("Retrieving app.model.Organization list for {}", kv(USER_KEYWORD, AppUserHelper.getUserName()));
        return (List<Organization>) organizationRepo.findAll();
    }
    public void save(Organization org) {
        organizationRepo.save(org);
    }
}
