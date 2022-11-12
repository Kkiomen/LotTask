package pl.owsianka.lottask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.owsianka.lottask.entity.statistic.StatisticCount;
import pl.owsianka.lottask.entity.statistic.StatisticDto;
import pl.owsianka.lottask.service.StatisticService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(StatisticController.class)
@AutoConfigureMockMvc
class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticService statisticService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Should return the HTTP status code CREATED (201)")
    public void shouldReturnCreatedStatusWhenCreatesNewVisitWithAllParametrs() throws Exception {
        StatisticDto statisticDto = StatisticDto.builder()
                .ip("212.34.52.103")
                .date(LocalDate.parse("2020-10-19"))
                .build();

        ResultActions response = mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statisticDto)));

        response.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return the HTTP status code BadRequest (400) without date in body")
    public void shouldReturnRequestStatusWhenCreatesNewVisitWithoutDateParametr() throws Exception {
        StatisticDto statisticDto = StatisticDto.builder()
                .ip("212.34.52.103")
                .build();

        ResultActions response = mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statisticDto)));

        response.andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return the HTTP status code BadRequest (400) without ip in body")
    public void shouldReturnBadRequestStatusWhenCreatesNewVisitWithoutIpParametr() throws Exception {
        StatisticDto statisticDto = StatisticDto.builder()
                .date(LocalDate.parse("2020-10-19"))
                .build();

        ResultActions response = mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statisticDto)));

        response.andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return the HTTP status code BadRequest (400) without all parametrs in body")
    public void shouldReturnBadRequestStatusWhenCreatesNewVisitWithoutAllParametrs() throws Exception {
        StatisticDto statisticDto = new StatisticDto();

        ResultActions response = mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statisticDto)));

        response.andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return the HTTP status code OK with get statistics list")
    void shouldReturnStatisticList() throws Exception {
        List<StatisticCount> statisticCountList = new ArrayList<>();
        statisticCountList.add(StatisticCount.builder()
                .date(LocalDate.parse("2020-10-19"))
                .count(3L)
                .build());
        statisticCountList.add(StatisticCount.builder()
                .date(LocalDate.parse("2020-10-20"))
                .count(5L)
                .build());
        statisticCountList.add(StatisticCount.builder()
                .date(LocalDate.parse("2020-10-21"))
                .count(8L)
                .build());

        given(statisticService.getStatisticCount()).willReturn(statisticCountList);

        ResultActions response = mockMvc.perform(get("/statistics"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(statisticCountList.size())));
    }

    @Test
    @DisplayName("Should return the HTTP status code OK with get empty statistics list")
    void shouldReturnEmptyStatisticList() throws Exception {
        List<StatisticCount> statisticCountList = new ArrayList<>();

        given(statisticService.getStatisticCount()).willReturn(statisticCountList);

        ResultActions response = mockMvc.perform(get("/statistics"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(statisticCountList.size())));
    }

    @Test
    @DisplayName("Should return statistics as JSON")
    void shouldReturnStatisticsAsJSON() throws Exception {
        ResultActions response = mockMvc.perform(get("/statistics"));

        response.andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}