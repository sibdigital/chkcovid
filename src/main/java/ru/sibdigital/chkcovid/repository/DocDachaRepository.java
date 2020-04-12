package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibdigital.chkcovid.domain.DocDacha;

public interface DocDachaRepository extends JpaRepository<DocDacha, Long> {
}
