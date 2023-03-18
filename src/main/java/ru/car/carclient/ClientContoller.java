package ru.car.carclient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.car.carclient.dto.InfoFromDbDto;

import java.util.UUID;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ClientContoller {
    // http://localhost:8080/webjars/swagger-ui/index.html#/
    @Autowired
    RequestService requestService;

    @PostMapping("{uuid}")
    public Mono<ResponseEntity<Void>> synchronizeInfo(@PathVariable UUID uuid, @RequestParam String json) {
        return requestService.saveUserInfo(uuid, json);
    }

    @GetMapping("/{uuid}")
    public Mono<InfoFromDbDto> getInfoByUuid(@PathVariable UUID uuid) {
        return requestService.getInfoByUuid(uuid).log();
    }

    @PostMapping("/activity/{uuid}")
    public Mono<ResponseEntity<Void>> saveActivity(@PathVariable UUID uuid, @RequestParam Long activity) {
        return requestService.saveActivity(uuid, activity);
    }
}
