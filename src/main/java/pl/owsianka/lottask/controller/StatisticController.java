package pl.owsianka.lottask.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.owsianka.lottask.entity.statistic.Statistic;
import pl.owsianka.lottask.entity.statistic.StatisticCount;
import pl.owsianka.lottask.entity.statistic.StatisticDto;
import pl.owsianka.lottask.service.StatisticService;

import java.util.List;


@RestController
public class StatisticController {

    private StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Statistic createNewVisit(@RequestBody StatisticDto statisticDto){

        if(statisticDto.getDate() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date is required");

        if(statisticDto.getIp() == null || statisticDto.getIp().equals(""))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IP is required");

        return this.statisticService.saveVisit(statisticDto);
    }

    @GetMapping("statistics")
    public List<StatisticCount> getStatistics(){
        return this.statisticService.getStatisticCount();
    }


}
