package pl.owsianka.lottask.entity.statistic;

import org.springframework.stereotype.Service;

@Service
public class StatisticDtoMapper {

   StatisticDto map(Statistic statistic){
      StatisticDto statisticDto = new StatisticDto();
      statisticDto.setId(statistic.getId());
      statisticDto.setDate(statistic.getDate());
      statisticDto.setIp(statistic.getIp());
      return statisticDto;
   }

   public Statistic map(StatisticDto statisticDto){
      Statistic statistic = new Statistic();
      statistic.setId(statisticDto.getId());
      statistic.setDate(statisticDto.getDate());
      statistic.setIp(statisticDto.getIp());
      return statistic;
   }

}
