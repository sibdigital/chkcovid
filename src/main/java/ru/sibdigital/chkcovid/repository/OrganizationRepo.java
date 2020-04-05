package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.chkcovid.domain.Organization;

import java.util.Collection;

@Repository
public interface OrganizationRepo extends  JpaRepository<Organization, Integer>{
    Collection<Organization> findAllByFirstname(String firstname);
    Collection<Organization> findAllByLastname(String lastname);
    Collection<Organization> findAllByPatronymic(String patronymic);
    Collection<Organization> findAllByInn(String inn);
}
