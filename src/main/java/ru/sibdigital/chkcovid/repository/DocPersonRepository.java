package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sibdigital.chkcovid.domain.DocPerson;

import java.util.List;

public interface DocPersonRepository extends JpaRepository<DocPerson, Integer> {
/*
    List<DocPerson> findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCase(String inn, String lastname, String firstname);

    List<DocPerson> findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCaseAndPatronymicIgnoreCase(String inn, String lastname, String firstname, String patronymic);
*/
    @Query(nativeQuery = true, value = "SELECT person.* FROM v_doc_person_and_org_info person WHERE " +
        " upper(trim(person.inn)) = upper(trim(:inn))" +
        " and upper(trim(person.lastname)) = upper(trim(:lastname))" +
        " and upper(trim(person.firstname)) = upper(trim(:firstname)) " +
        " ORDER BY person.id_request DESC LIMIT 1 ")
    List<DocPerson> findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCase(@Param("inn")String inn, @Param("lastname")String lastname, @Param("firstname")String firstname);

    @Query(nativeQuery = true, value = "SELECT person.* FROM v_doc_person_and_org_info person WHERE " +
            " upper(trim(person.inn)) = upper(trim(:inn))" +
            " and upper(trim(person.lastname)) = upper(trim(:lastname))" +
            " and upper(trim(person.firstname)) = upper(trim(:firstname)) " +
            " and upper(trim(person.patronymic)) = upper(trim(:patronymic)) " +
            "ORDER BY person.id_request DESC LIMIT 1")
    List<DocPerson> findDistinctByInnAndLastnameIgnoreCaseAndFirstnameIgnoreCaseAndPatronymicIgnoreCase(@Param("inn")String inn, @Param("lastname")String lastname, @Param("firstname")String firstname,  @Param("patronymic")String patronymic);

}
