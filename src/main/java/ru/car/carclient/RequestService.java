package ru.car.carclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.car.carclient.dto.ActivDto;
import ru.car.carclient.dto.InfoFromDbDto;

import com.jayway.jsonpath.JsonPath;
import ru.car.carclient.dto.SynchronizeDto;

import java.lang.reflect.Type;
import java.util.UUID;

@Slf4j
@Service
public class RequestService {
    @Autowired
    RSocketRequester requester;

    public Mono<ResponseEntity<Void>> saveUserInfo(UUID uuid, String json) {
        Integer money = JsonPath.read(json, "$.money");
        String country = (String) JsonPath.read(json, "$.country");

        SynchronizeDto synchronizeDto = new SynchronizeDto(uuid, money.longValue(), country);
        log.info("builded " + synchronizeDto.toString());
        return requester.route("synchronize")
                .data(synchronizeDto)
                .retrieveMono(new ParameterizedTypeReference<>() {
                    @Override
                    public Type getType() {
                        return Void.TYPE;
                    }
                });
    }

    public Mono<InfoFromDbDto> getInfoByUuid(UUID uuid) {
        return requester.route("info/{uuid}", uuid).retrieveMono(InfoFromDbDto.class);
    }

    public Mono<ResponseEntity<Void>> saveActivity(UUID uuid, Long activity) {
        ActivDto activDto = new ActivDto(uuid, activity);
        return requester.route("activity").data(activDto).retrieveMono(new ParameterizedTypeReference<>() {
            @Override
            public Type getType() {
                return Void.TYPE;
            }
        });
    }
}
