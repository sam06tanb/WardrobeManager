package dev.samir.backangulart.api.controller;

import dev.samir.backangulart.api.dto.ClothDto;
import dev.samir.backangulart.api.dto.mapper.ClothDtoMapper;
import dev.samir.backangulart.domain.model.Cloth;
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
    public ResponseEntity<ClothDto> add(@RequestBody ClothDto clothDto) {
        Cloth cloth = dtoMapper.toDomain(clothDto);
        Cloth created = clothService.create(cloth);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dtoMapper.toDto(created));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ClothDto> update(@PathVariable Long id, @RequestBody ClothDto clothDto) {
        Cloth updatedCloth = dtoMapper.toDomain(clothDto);
        return clothService.update(id, updatedCloth)
                .map(dtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clothService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/show")
    public ResponseEntity<List<ClothDto>> show() {
        List<ClothDto> clothes = clothService.listAll().stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clothes);
    }

    @GetMapping("show/{id}")
    public ResponseEntity<ClothDto> showById(@PathVariable Long id) {
        return clothService.findById(id)
                .map(dtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

