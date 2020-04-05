package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibdigital.chkcovid.domain.Organisation;
import ru.sibdigital.chkcovid.domain.OrganisationPK;

import java.util.Collection;

public interface OrganisationRepo extends JpaRepository<Organisation, OrganisationPK> {
     Collection<Organisation> findAllByFirstname(String firstname);
     Collection<Organisation> findAllByLastname(String lastname);
     Collection<Organisation> findAllByPatronymic(String patronymic);
     Collection<Organisation> findAllByItn(String itn);
}
