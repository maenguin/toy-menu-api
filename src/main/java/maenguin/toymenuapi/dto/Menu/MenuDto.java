package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.Menu;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MenuDto {

    private Long menuId;

    private String name;

    private String groupName;

    private List<MenuBasicOptionDto> menuBasicOptionList;
    private List<MenuOptionGroupDto> menuOptionList;
    private Integer price;




    public MenuDto(Menu menu) {
        this.menuId = menu.getId();
        this.name = menu.getName();
        this.groupName = menu.getMenuGroup().getName();
        this.price = menu.getPrice();
        this.menuBasicOptionList = menu.getMenuBasicOptions().stream()
                .map(MenuBasicOptionDto::new)
                .collect(Collectors.toList());
        this.menuOptionList = menu.getMenuOptionGroups().stream()
                .map(MenuOptionGroupDto::new)
                .collect(Collectors.toList());
    }


}

