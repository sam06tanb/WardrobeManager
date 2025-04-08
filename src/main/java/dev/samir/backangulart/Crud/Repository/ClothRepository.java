package dev.samir.backangulart.Crud.Repository;

import dev.samir.backangulart.Crud.Model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
