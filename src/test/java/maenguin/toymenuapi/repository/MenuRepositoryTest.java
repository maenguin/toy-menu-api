package maenguin.toymenuapi.repository;

import maenguin.toymenuapi.domain.*;
import maenguin.toymenuapi.dto.Menu.MenuFlatDto;
import maenguin.toymenuapi.repository.query.MenuQueryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
class MenuRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MenuGroupRepository menuGroupRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    MenuQueryRepository menuQueryRepository;

    @BeforeEach
    private void BeforeEach() {
        Store pizzaShop = new Store("피자샵");
        em.persist(pizzaShop);

        MenuGroup group1 = MenuGroup.builder()
                .name("오리지널 피자")
                .priority(0)
                .store(pizzaShop)
                .build();
        MenuGroup group2 = MenuGroup.builder()
                .name("스페셜 피자")
                .priority(1)
                .store(pizzaShop)
                .build();
        MenuGroup group3 = MenuGroup.builder()
                .name("사이드 메뉴")
                .priority(2)
                .store(pizzaShop)
                .build();
        MenuGroup group4 = MenuGroup.builder()
                .name("음료")
                .priority(3)
                .store(pizzaShop)
                .build();
        em.persist(group1);
        em.persist(group2);
        em.persist(group3);
        em.persist(group4);

        Menu pizza1 = Menu.builder()
                .name("고구마 피자")
                .status(MenuStatus.ONSALE)
                .count(10)
                .price(16900)
                .build();
        pizza1.changeMenuGroup(group1);
        pizza1.getMenuBasicOptions().add(new MenuBasicOption("S",12900));
        pizza1.getMenuBasicOptions().add(new MenuBasicOption("R",14900));
        pizza1.getMenuBasicOptions().add(new MenuBasicOption("L",16900));


        Menu pizza2 = Menu.builder()
                .name("페페로니 피자")
                .status(MenuStatus.ONSALE)
                .count(10)
                .price(16900)
                .build();
        pizza2.changeMenuGroup(group1);



        em.persist(pizza1);
        em.persist(pizza2);

        MenuOptionGroup option1 = MenuOptionGroup.builder()
                .name("추가 토핑")
                .maxSelectCount(4)
                .minSelectCount(    0)
                .build();
        option1.changeMenu(pizza1);

        MenuOptionGroup option2 = MenuOptionGroup.builder()
                .name("도우")
                .maxSelectCount(1)
                .minSelectCount(1)
                .build();
        option2.changeMenu(pizza1);

        em.persist(option1);
        em.persist(option2);

        MenuOption topping1 = MenuOption.builder()
                .name("치즈")
                .price(1000)
                .build();
        topping1.changeMenuOption(option1);

        MenuOption topping2 = MenuOption.builder()
                .name("페페로니")
                .price(1000)
                .build();
        topping2.changeMenuOption(option1);

        MenuOption dough1 = MenuOption.builder()
                .name("빵끝에 소보루")
                .price(5000)
                .build();
        dough1.changeMenuOption(option2);

        MenuOption dough2 = MenuOption.builder()
                .name("빵끝에 치즈링")
                .price(3000)
                .build();
        dough2.changeMenuOption(option2);
        em.persist(topping1);
        em.persist(topping2);
        em.persist(dough1);
        em.persist(dough2);

        em.flush();
        em.clear();

    }

    @Test
    @Commit
    public void 메뉴_조회() {

        Optional<Menu> optional = menuRepository.findById(3L);
        if (optional.isPresent()) {
            Menu findMenu = optional.get();
            System.out.println("findMenu = " + findMenu);
            for (MenuBasicOption menuBasicOption : findMenu.getMenuBasicOptions()) {
                System.out.println("menuBasicOption = " + menuBasicOption);
            }
            for (MenuOptionGroup menuOptionGroup : findMenu.getMenuOptionGroups()) {
                System.out.println("menuOptionGroup = " + menuOptionGroup);
                for (MenuOption menuOption : menuOptionGroup.getMenuOptions()) {
                    System.out.println("menuOption = " + menuOption);
                }
            }

            findMenu.getMenuBasicOptions().remove(0);
        }


    }


    @Test
    @Commit
    public void 쿼리_메뉴_조회() {
        List<MenuGroup> allMenuByStore = menuQueryRepository.findAllMenuByStore(1L);

        for (MenuGroup menuGroup : allMenuByStore) {
            System.out.println("menuGroup = " + menuGroup);
            for (Menu menu : menuGroup.getMenus()) {
                System.out.println("menu = " + menu);
                for (MenuOptionGroup menuOptionGroup : menu.getMenuOptionGroups()) {
                    System.out.println("menuOptionGroup = " + menuOptionGroup);
                    for (MenuOption menuOption : menuOptionGroup.getMenuOptions()) {
                        System.out.println("menuOption = " + menuOption);
                    }
                }
            }
        }
    }

    @Test
    @Commit
    public void 쿼리_플랫_메뉴_조회() {
        List<MenuFlatDto> allMenuByStore = menuQueryRepository.findAllFlatMenuByStore(1L);

        for (MenuFlatDto menuFlatDto : allMenuByStore) {
            System.out.println("menuFlatDto = " + menuFlatDto);
        }
    }

    @Test
    @Commit
    public void 쿼리_메뉴_조회_메뉴프로젝션() {
        List<Menu> allMenuByStore = menuQueryRepository.findAllMenuRightByStore(1L);

        for (Menu menu : allMenuByStore) {
            System.out.println("menu = " + menu);
        }
    }

}