package ru.sibdigital.chkcovid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibdigital.chkcovid.domain.RegStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegStatisticRepository extends JpaRepository<RegStatistic, Integer> {
//    @Query(nativeQuery = true, value = "INSERT INTO reg_statistic (lastname, firstname, patronymic, inn, results) VALUES (:lastname, :firstname,: patronymic, :inn, :results)")
//    void createNewRegStatistic(@Param("inn")String inn, @Param("lastname")String lastname, @Param("firstname")String firstname,  @Param("patronymic")String patronymic);
}