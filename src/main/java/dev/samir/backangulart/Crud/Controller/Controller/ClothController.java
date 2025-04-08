package dev.samir.backangulart.Crud.Controller.Controller;

import dev.samir.backangulart.Crud.Controller.Model.Cloth;
import dev.samir.backangulart.Crud.Controller.Service.ClothService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roupa")
public class ClothController {

    private ClothService clothService;

    public ClothController(ClothService clothService) {
        this.clothService = clothService;
    }

    @GetMapping("/show")
    public List<Cloth> getRoupa() {
        return clothService.showlist();
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Cloth> getRoupaById(@PathVariable Long id) {
        return clothService.showListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Cloth adicionarRoupa(@RequestBody Cloth cloth) {
        return clothService.create(cloth);
    }

    @PutMapping("/update/{id}")
    public Cloth editarRoupa(@PathVariable Long id, @RequestBody Cloth cloth) {
        return clothService.update(id, cloth);
    }

    @DeleteMapping("/delete")
    public void removerRoupa(@RequestBody Long id) {
        clothService.delete(id);
    }

}
