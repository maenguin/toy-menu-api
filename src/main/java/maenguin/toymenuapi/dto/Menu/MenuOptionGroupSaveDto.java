package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.Menu;
import maenguin.toymenuapi.domain.MenuOptionGroup;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MenuOptionGroupSaveDto {

    private String name;

    private Integer maxSelectCount;

    private Integer minSelectCount;

    private boolean visible;

    private Integer priority;

    private List<MenuOptionSaveDto> menuOptionSaveList;

    public MenuOptionGroup toEntity(Menu menu) {

        MenuOptionGroup menuOptionGroup = MenuOptionGroup.builder()
                .name(name)
                .maxSelectCount(maxSelectCount)
                .minSelectCount(minSelectCount)
                .visible(visible)
                .priority(priority)
                .build();
        menuOptionGroup.changeMenu(menu);

        if (menuOptionSaveList != null && menuOptionSaveList.size() > 0) {
            menuOptionSaveList.stream()
                    .map(mo -> mo.toEntity(menuOptionGroup))
                    .collect(Collectors.toList());
        }

        return menuOptionGroup;
    }
}
