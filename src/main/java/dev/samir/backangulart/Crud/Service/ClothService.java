package dev.samir.backangulart.Crud.Service;

import dev.samir.backangulart.Crud.Model.Cloth;
import dev.samir.backangulart.Crud.Repository.ClothRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClothService {

    private ClothRepository clothRepository;

    public ClothService(ClothRepository clothRepository) {
        this.clothRepository = clothRepository;
    }

    public List<Cloth> showlist() {
        return clothRepository.findAll();
    }

    public Optional<Cloth> showListById(Long id) {
        return clothRepository.findById(id);
    }

    public Cloth create(Cloth clothEntity) {
        return clothRepository.save(clothEntity);
    }

    public Cloth update(Long id, Cloth clothEntity) {
        return clothRepository.findById(id).map(cloth -> {
            cloth.setName(clothEntity.getName());
            cloth.setSize(clothEntity.getSize());
            cloth.setColor(clothEntity.getColor());
            return clothRepository.save(cloth);
        }).orElseThrow(() -> new RuntimeException("Did not found any cloth"));
    }

    public void delete(Long id) {
        clothRepository.deleteById(id);
    }
}
