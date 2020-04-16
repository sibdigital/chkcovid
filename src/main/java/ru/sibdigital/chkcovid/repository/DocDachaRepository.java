package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sibdigital.chkcovid.domain.DocDacha;

import java.time.LocalDate;
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

    @Query(value = "select ddv.*\n" +
            "from ( select * from\n" +
            "      doc_dacha as dd\n" +
            "    where date_trunc('day', dd.valid_date) >= :validDate \n" +
            ") as ddv\n" +
            "inner join(\n" +
            "    select ddp.id_doc_dacha\n" +
            "    from doc_dacha_person as ddp\n" +
            "    where (upper(trim(ddp.firstname)), upper(trim(ddp.lastname)), upper(trim(ddp.patronymic)))\n" +
            "        = (upper(trim(:firstname)),    upper(trim(:lastname)),    upper(trim(:patronymic)))\n" +
            ") as ddp\n" +
            "on ddv.id = ddp.id_doc_dacha" +
            " order by ddv.valid_date;\n",
            nativeQuery = true)
    List<DocDacha> findByFirstnameAndLastnameAndPatronymicAndValidDate(@Param("lastname")String lastname,
                                                                       @Param("firstname")String firstname,
                                                                       @Param("patronymic")String patronymic,
                                                                       @Param("validDate") LocalDate validDate);
    @Query(value = "select ddv.*\n" +
            "from ( select * from\n" +
            "      doc_dacha as dd\n" +
            "   where date_trunc('day', dd.valid_date) >= :validDate \n" +
            ") as ddv\n" +
            "inner join(\n" +
            "    select ddp.id_doc_dacha\n" +
            "    from doc_dacha_person as ddp\n" +
            "    where (upper(trim(ddp.firstname)), upper(trim(ddp.lastname)))\n" +
            "        = (upper(trim(:firstname)),    upper(trim(:lastname)))\n" +
            ") as ddp\n" +
            "on ddv.id = ddp.id_doc_dacha" +
            " order by ddv.valid_date;\n",
            nativeQuery = true)
    List<DocDacha> findByFirstnameAndLastnameAndValidDate(@Param("lastname")String lastname,
                                                          @Param("firstname")String firstname,
                                                          @Param("validDate") LocalDate validDate);
}
