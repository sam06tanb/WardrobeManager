package dev.samir.backangulart.api.controller;

import dev.samir.backangulart.api.dto.ClothResponseDto;
import dev.samir.backangulart.api.dto.CreateClothRequestDto;
import dev.samir.backangulart.api.dto.mapper.ClothDtoMapper;
import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.samir.backangulart.application.Service.ClothService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clothes")
public class ClothController {

    private final ClothService clothService;
    private final ClothDtoMapper dtoMapper;

    public ClothController(ClothService clothService, ClothDtoMapper dtoMapper) {
        this.clothService = clothService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<ClothResponseDto> add(@Valid @RequestBody CreateClothRequestDto clothDto) {
        Cloth cloth = dtoMapper.toDomain(clothDto);
        Cloth created = clothService.create(cloth);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toResponseDto(created));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ClothResponseDto> update(@PathVariable Long id,@Valid @RequestBody CreateClothRequestDto clothDto) {
        return clothService.update(id, dtoMapper.toDomain(clothDto))
                .map(dtoMapper::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clothService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/show")
    public ResponseEntity<List<ClothResponseDto>> show() {
        List<ClothResponseDto> clothes = clothService.listAll().stream()
                .map(dtoMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clothes);
    }

    @GetMapping("show/{id}")
    public ResponseEntity<ClothResponseDto> findById(@PathVariable Long id) {
        Cloth cloth = clothService.findById(id);
        return ResponseEntity.ok(dtoMapper.toResponseDto(cloth));
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<ClothResponseDto>> getBySize(@PathVariable EnumCloth size) {
        List<ClothResponseDto> list = clothService.findBySize(size)
                .stream()
                .map(dtoMapper::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

}

