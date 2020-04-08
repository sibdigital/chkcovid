package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibdigital.chkcovid.domain.DocPerson;

import java.util.List;

public interface DocPersonRepository extends JpaRepository<DocPerson, Integer> {
    List<DocPerson> findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCase(String inn, String lastname, String firstname);

    List<DocPerson> findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCaseAndPatronymicIgnoreCase(String inn, String lastname, String firstname, String patronymic);
}
