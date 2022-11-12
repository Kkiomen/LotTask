package pl.owsianka.lottask.service;

import org.springframework.stereotype.Service;
import pl.owsianka.lottask.entity.statistic.*;

import java.util.List;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticDtoMapper statisticDtoMapper;

    public StatisticService(StatisticRepository statisticRepository, StatisticDtoMapper statisticDtoMapper) {
        this.statisticRepository = statisticRepository;
        this.statisticDtoMapper = statisticDtoMapper;
    }

    public Statistic saveVisit(StatisticDto statisticDto){
        Statistic statistic = this.statisticDtoMapper.map(statisticDto);
        return this.statisticRepository.save(statistic);
    }

    public List<StatisticCount> getStatisticCount(){
        List<StatisticCount> result = this.statisticRepository.getStatisticCount();
        return result;
    }



}
