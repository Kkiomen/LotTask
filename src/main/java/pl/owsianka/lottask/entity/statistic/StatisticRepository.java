package pl.owsianka.lottask.entity.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query("SELECT new pl.owsianka.lottask.entity.statistic.StatisticCount(date,COUNT(date)) FROM Statistic GROUP BY date ORDER BY date ASC")
    List<StatisticCount> getStatisticCount();
}

