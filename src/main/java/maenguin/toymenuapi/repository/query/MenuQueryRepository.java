package maenguin.toymenuapi.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import maenguin.toymenuapi.domain.*;
import maenguin.toymenuapi.dto.Menu.MenuFlatDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static maenguin.toymenuapi.domain.QMenu.*;
import static maenguin.toymenuapi.domain.QMenuGroup.*;
import static maenguin.toymenuapi.domain.QMenuOption.*;
import static maenguin.toymenuapi.domain.QMenuOptionGroup.*;
import static maenguin.toymenuapi.domain.QStore.*;

@Repository
public class MenuQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MenuQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<MenuGroup> findAllMenuByStore(Long storeId) {
        return  queryFactory
                .select(menuGroup).distinct()
                .from(menuGroup)
                .join(menuGroup.store, store).on(store.id.eq(storeId))
                .leftJoin(menuGroup.menus, menu).fetchJoin()
                .fetch();
    }

    public List<MenuFlatDto> findAllFlatMenuByStore(Long storeId) {
        return  queryFactory
                .select(Projections.constructor(MenuFlatDto.class,menuGroup.name, menu.name, menuOptionGroup.name))
                .from(menuGroup)
                .join(menuGroup.store, store).on(store.id.eq(storeId))
                .leftJoin(menuGroup.menus, menu)
                .leftJoin(menu.menuOptionGroups, menuOptionGroup)
                .fetch();
    }

    public List<Menu> findAllMenuRightByStore(Long storeId) {
        return  queryFactory
                .select(menu).distinct()
                .from(menu)
                .join(menuGroup.store, store).on(store.id.eq(storeId))
                .leftJoin(menuGroup.menus, menu)
                .fetch();
    }

}
