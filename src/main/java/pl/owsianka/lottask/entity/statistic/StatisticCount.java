package pl.owsianka.lottask.entity.statistic;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StatisticCount {
    private LocalDate date;
    private Long count;
}
