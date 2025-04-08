package dev.samir.backangulart.Crud.Service;

import dev.samir.backangulart.Crud.DtoMapper.ClothDto;
import dev.samir.backangulart.Crud.DtoMapper.ClothMapper;
import dev.samir.backangulart.Crud.Model.Cloth;
import dev.samir.backangulart.Crud.Repository.ClothRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClothService {

    private ClothRepository clothRepository;


    public ClothService(ClothRepository clothRepository) {
        this.clothRepository = clothRepository;
    }

    public List<ClothDto> showlist() {
        List<Cloth> cloth = clothRepository.findAll();
        return cloth.stream()
                .map(ClothMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClothDto showListById(Long id) {
        Optional<Cloth> showListById = clothRepository.findById(id);
        return showListById.map(ClothMapper::toDTO).orElse(null);
    }

    public ClothDto create(ClothDto clothDto) {
        Cloth cloth = ClothMapper.toEntity(clothDto);
        cloth = clothRepository.save(cloth);
        return ClothMapper.toDTO(cloth);
    }

    public ClothDto update(Long id, ClothDto clothDto) {
        Optional<Cloth> showListById = clothRepository.findById(id);
        if (showListById.isPresent()) {
            Cloth clothUpdated = ClothMapper.toEntity(clothDto);
            clothUpdated.setId(id);
            Cloth savedCloth = clothRepository.save(clothUpdated);
            return ClothMapper.toDTO(savedCloth);
        }
        return null;
    }

    public void delete(Long id) {
        clothRepository.deleteById(id);
    }
}
