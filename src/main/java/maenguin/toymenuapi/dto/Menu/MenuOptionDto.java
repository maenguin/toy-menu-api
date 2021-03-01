package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.MenuOption;

@Getter
public class MenuOptionDto {

    private Long menuOptionItemId;
    private String name;
    private Integer price;

    public MenuOptionDto(MenuOption menuOption) {
        this.menuOptionItemId = menuOption.getId();
        this.name = menuOption.getName();
        this.price = menuOption.getPrice();
    }
}