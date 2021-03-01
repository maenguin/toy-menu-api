package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.MenuBasicOption;

@Getter
public class MenuBasicOptionDto {

    private String name;
    private Integer price;

    public MenuBasicOptionDto(MenuBasicOption menuBasicOption) {
        this.name = menuBasicOption.getName();
        this.price = menuBasicOption.getPrice();
    }
}
