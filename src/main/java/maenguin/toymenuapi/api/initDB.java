package maenguin.toymenuapi.api;

import lombok.RequiredArgsConstructor;
import maenguin.toymenuapi.domain.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@Profile("local")
@RequiredArgsConstructor
public class initDB {

    private final initDBService initDBService;

    @PostConstruct
    public void init() {
        initDBService.init();
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class initDBService {


        private final EntityManager em;

        public void init(){
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
                    .minSelectCount(0)
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
            topping1.changeMenuOptionGroup(option1);

            MenuOption topping2 = MenuOption.builder()
                    .name("페페로니")
                    .price(1000)
                    .build();
            topping2.changeMenuOptionGroup(option1);

            MenuOption dough1 = MenuOption.builder()
                    .name("빵끝에 소보루")
                    .price(5000)
                    .build();
            dough1.changeMenuOptionGroup(option2);

            MenuOption dough2 = MenuOption.builder()
                    .name("빵끝에 치즈링")
                    .price(3000)
                    .build();
            dough2.changeMenuOptionGroup(option2);
            em.persist(topping1);
            em.persist(topping2);
            em.persist(dough1);
            em.persist(dough2);
        }



    }




}
