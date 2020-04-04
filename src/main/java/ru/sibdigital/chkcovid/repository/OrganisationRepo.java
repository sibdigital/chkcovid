package ru.sibdigital.chkcovid.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.chkcovid.domain.Organisation;
import ru.sibdigital.chkcovid.domain.OrganisationPK;

import java.util.List;
import java.util.Optional;

public interface  OrganisationRepo extends  JpaRepository<Organisation, OrganisationPK>{

}
