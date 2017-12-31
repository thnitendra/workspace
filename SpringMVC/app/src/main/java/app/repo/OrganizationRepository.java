package app.repo;

import app.model.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, String> {

    List<Organization> findAll();

    Organization findById(String id);

    Organization findByName(String name);

    Organization findTop1ByType(String type);

}
