package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.MenuOptionGroup;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MenuOptionGroupDto {

    private Long menuOptionId;
    private String name;
    private Integer maxSelectCount;
    private Integer minSelectCount;

    private List<MenuOptionDto> menuOptionList;

    public MenuOptionGroupDto(MenuOptionGroup menuOptionGroup) {

        this.menuOptionId = menuOptionGroup.getId();
        this.name = menuOptionGroup.getName();
        this.maxSelectCount = menuOptionGroup.getMaxSelectCount();
        this.minSelectCount = menuOptionGroup.getMinSelectCount();
        this.menuOptionList = menuOptionGroup.getMenuOptions().stream()
                .map(MenuOptionDto::new)
                .collect(Collectors.toList());

    }
}