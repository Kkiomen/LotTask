package pl.owsianka.lottask.entity.statistic;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticDto {
    private Long id;

    private LocalDate date;

    private String ip;
}
