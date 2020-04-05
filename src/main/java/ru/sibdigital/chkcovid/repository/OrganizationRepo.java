package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.chkcovid.domain.Organization;

import java.util.List;

@Repository
public interface OrganizationRepo extends  JpaRepository<Organization, Integer> {

    List<Organization> findAllByInnAndLastnameAndFirstname(String inn, String lastname, String firstname);
    List<Organization> findAllByInnAndLastnameAndFirstnameAndPatronymic(String inn, String lastname, String firstname, String patronymic);

}
