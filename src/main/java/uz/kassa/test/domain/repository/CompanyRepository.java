package uz.kassa.test.domain.repository;

import org.springframework.stereotype.Repository;
import uz.kassa.test.domain.entity.Company;
import uz.kassa.test.domain.repository.base.BaseRepository;

@Repository
public interface CompanyRepository extends BaseRepository<Company> {
}
