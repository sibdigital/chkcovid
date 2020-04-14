package ru.sibdigital.chkcovid.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sibdigital.chkcovid.domain.DocDachaPerson;

public interface DocDachaPersonRepository extends CrudRepository<DocDachaPerson, Long> {
}
