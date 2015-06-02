package com.ryanair.cheapflights.domain.command;

import com.ryanair.cheapflights.entity.FlightInfo;
import com.ryanair.cheapflights.repository.FlightInfoRepository;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ryanair.cheapflights.util.ObservableUtil.fromCollection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetFlightInfoTest {
    @Test
    public void returnsFlightResults() {
        final String flightNumber = "202";

        List<FlightInfo> expectedResults = new ArrayList<>();
        expectedResults.add(
                new FlightInfo()
                        .setFlightNumber(flightNumber));

        FlightInfoRepository repository = mock(FlightInfoRepository.class);
        when(repository.getFlightInfo(flightNumber)).thenReturn(fromCollection(expectedResults));

        GetFlightInfo getFlightInfo = new GetFlightInfo(repository);
        List<FlightInfo> results = getFlightInfo
                .execute(flightNumber)
                .toBlocking()
                .first();

        assertThat(results.size()).isEqualTo(expectedResults.size());
        for (int ri = 0; ri < results.size(); ri++) {
            assertThat(results.get(ri).getFlightNumber())
                    .isEqualTo(expectedResults.get(ri).getFlightNumber());
        }
    }
}