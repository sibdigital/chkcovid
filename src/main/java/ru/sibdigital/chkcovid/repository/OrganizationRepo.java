package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.chkcovid.domain.Organization;
import ru.sibdigital.chkcovid.domain.OrganizationPK;

@Repository
public interface OrganizationRepo extends  JpaRepository<Organization, OrganizationPK>{

}
