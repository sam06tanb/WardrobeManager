package dev.samir.backangulart.Crud.Controller.Repository;

import dev.samir.backangulart.Crud.Controller.Model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
