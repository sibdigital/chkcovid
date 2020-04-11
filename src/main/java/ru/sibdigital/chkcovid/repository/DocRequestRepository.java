package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.chkcovid.domain.DocRequest;

import java.util.List;

@Repository
public interface DocRequestRepository extends JpaRepository<DocRequest, Long> {

    @Query(value = "SELECT dr.* FROM doc_request dr, cls_organization org WHERE  dr.id_organization = org.id " +
            " and (trim(org.inn) = trim(:inn)) " +
            " ORDER BY dr.time_review DESC limit 100",
            nativeQuery = true)
    List<DocRequest> findTop100ByInnOrShortName(@Param("inn") String inn);

}
