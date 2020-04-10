package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.chkcovid.domain.ClsDepartment;
import ru.sibdigital.chkcovid.domain.ClsOrganization;
import ru.sibdigital.chkcovid.domain.DocRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DocRequestRepo extends JpaRepository<DocRequest, Long> {

    @Query(value = "SELECT dr.* FROM doc_request dr, cls_organization org WHERE  dr.id_organization = org.id " +
            " and (trim(org.inn) like %:innOrShortName% or lower(trim(org.short_name)) like %:innOrShortName%  ) " +
            " ORDER BY dr.time_review DESC limit 100",
            nativeQuery = true)
    List<DocRequest> findTop100ByInnOrShortName(@Param("innOrShortName") String innOrShortName);

}
