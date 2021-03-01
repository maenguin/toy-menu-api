package maenguin.toymenuapi.repository;

import maenguin.toymenuapi.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findByName(String name);

    @Query("select m from Menu m " +
            " join fetch m.menuGroup mg" +
            " left join fetch m.menuOptionGroups mo" +
            " where m.id = :id")
    Optional<Menu> findById(@Param("id") Long id);


}
