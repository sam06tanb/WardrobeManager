package dev.samir.backangulart.Crud.Controller;

import dev.samir.backangulart.Crud.Model.Cloth;
import dev.samir.backangulart.Crud.Service.ClothService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloth")
public class ClothController {

    private ClothService clothService;

    public ClothController(ClothService clothService) {
        this.clothService = clothService;
    }

    @GetMapping("/show")
    public List<Cloth> showList() {
        return clothService.showlist();
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Cloth> showListById(@PathVariable Long id) {
        return clothService.showListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Cloth create(@RequestBody Cloth cloth) {
        return clothService.create(cloth);
    }

    @PutMapping("/update/{id}")
    public Cloth update(@PathVariable Long id, @RequestBody Cloth cloth) {
        return clothService.update(id, cloth);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        clothService.delete(id);
    }

}
