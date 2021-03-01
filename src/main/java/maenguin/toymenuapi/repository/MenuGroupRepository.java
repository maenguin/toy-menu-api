package maenguin.toymenuapi.repository;

import maenguin.toymenuapi.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long> {

    @Query("select distinct mg from MenuGroup mg" +
            " join mg.store s on s.id = :storeId" +
            " left join fetch mg.menus m ")
    List<MenuGroup> findAllByStore(@Param("storeId") Long storeId);


}
