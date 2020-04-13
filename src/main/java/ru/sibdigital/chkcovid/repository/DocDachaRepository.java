package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sibdigital.chkcovid.domain.DocDacha;

import java.util.List;

public interface DocDachaRepository extends JpaRepository<DocDacha, Long> {
    @Query(value = "SELECT dd.* FROM doc_dacha dd, doc_dacha_addr addr WHERE  dd.id = addr.id_doc_dacha " +
            " and trim(addr.district) = trim(:district) " +
            " and trim(addr.address) like %:address% " +
            " and trim(dd.firstname) = trim(:firstname) " +
            " and trim(dd.lastname) = trim(:lastname) " +
            " ORDER BY dd.time_review DESC limit 100",
            nativeQuery = true)
    List<DocDacha> findByFirstnameAndLastnameAndDocDachaAddrs(@Param("lastname")String lastname,
                                                              @Param("firstname")String firstname,
                                                              @Param("district")String district,
                                                              @Param("address")String address);

    @Query(value = "SELECT dd.* FROM doc_dacha dd, doc_dacha_addr addr WHERE  dd.id = addr.id_doc_dacha " +
            " and trim(addr.district) = trim(:district) " +
            " and trim(addr.address) like %:address% " +
            " and trim(dd.firstname) = trim(:firstname) " +
            " and trim(dd.lastname) = trim(:lastname) " +
            " and trim(dd.patronymic) = trim(:patronymic) " +
            " ORDER BY dd.time_review DESC limit 100",
            nativeQuery = true)
    List<DocDacha> findByFirstnameAndLastnameAAndPatronymicAndDocDachaAddrs(@Param("lastname")String lastname,
                                                                            @Param("firstname")String firstname,
                                                                            @Param("patronymic")String patronymic,
                                                                            @Param("district")String district,
                                                                            @Param("address")String address);
}
