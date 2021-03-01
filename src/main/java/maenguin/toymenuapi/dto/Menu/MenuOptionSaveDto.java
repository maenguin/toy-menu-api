package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.MenuOption;
import maenguin.toymenuapi.domain.MenuOptionGroup;

@Getter
public class MenuOptionSaveDto {

    private String name;

    private int price;

    private int count;

    private boolean visible;

    private Integer priority;

    public MenuOption toEntity(MenuOptionGroup menuOptionGroup) {
        MenuOption menuOption = MenuOption.builder()
                .name(name)
                .price(price)
                .visible(visible)
                .priority(priority)
                .build();
        menuOption.changeMenuOptionGroup(menuOptionGroup);

        return menuOption;
    }

}
