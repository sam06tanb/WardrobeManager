package dev.samir.backangulart.Crud.Controller;

import dev.samir.backangulart.Crud.DtoMapper.ClothDto;
import dev.samir.backangulart.Crud.Service.ClothService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ClothDto>>  showList() {
        List<ClothDto> cloths = clothService.showlist();
        return ResponseEntity.ok().body(cloths) ;
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> showListById(@PathVariable Long id) {
        ClothDto clothById = clothService.showListById(id);
        if (clothById != null) {
            return ResponseEntity.ok(clothById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cloth with id " + id + " not found");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> create(@RequestBody ClothDto clothDto) {
        ClothDto createCloth = clothService.create(clothDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cloth created successfully: " + createCloth.name());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClothDto clothDto) {

        ClothDto updateCloth = clothService.update(id, clothDto);

        if (updateCloth != null) {
            String msg = "Cloth updated successfully: "
                    + updateCloth.name() +
                    " | Size: " + updateCloth.size() +
                    " | Color: " + updateCloth.color();
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cloth with id " + id + " not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (clothService.showListById(id) != null) {
            clothService.delete(id);
            return ResponseEntity.ok("Cloth deleted successfully!" + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cloth with id " + id + " not found");
        }
    }

}
