package ru.car.carclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ActivDto {
    private UUID uuid;
    private Long activity;
}
