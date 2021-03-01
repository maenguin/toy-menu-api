package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.MenuGroup;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MenuGroupDto {

    private Long id;
    private String name;
    private Integer priority;
    private List<MenuDto> menuList;

    public MenuGroupDto(Long id, String name, List<MenuDto> menuList) {
        this.id = id;
        this.name = name;
        this.menuList = menuList;
    }

    public MenuGroupDto(MenuGroup menuGroup) {
        this.id = menuGroup.getId();
        this.name = menuGroup.getName();
        this.priority = menuGroup.getPriority();
        this.menuList = menuGroup.getMenus().stream()
                .map(MenuDto::new)
                .collect(Collectors.toList());

    }
}
