package maenguin.toymenuapi.repository;

import maenguin.toymenuapi.domain.MenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long> {


    @Query("select mog from MenuOptionGroup mog" +
            " join fetch mog.menuOptions mo " +
            " where mog.id = :id")
    Optional<MenuOptionGroup> findByIdWithMenuOption(@Param("id") Long id);
}
