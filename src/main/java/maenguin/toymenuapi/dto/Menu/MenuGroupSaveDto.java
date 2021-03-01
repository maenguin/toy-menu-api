package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.Menu;
import maenguin.toymenuapi.domain.MenuGroup;
import maenguin.toymenuapi.domain.Store;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MenuGroupSaveDto {

    private Long id;

    private String name;

    private Integer priority;

    private List<MenuSaveDto> menuSaveList;

    public MenuGroup toEntity(final Store store) {

        MenuGroup menuGroup = MenuGroup.builder()
                .id(id)
                .name(name)
                .priority(priority)
                .store(store)
                .build();

        if (menuSaveList != null && menuSaveList.size() > 0) {
            menuSaveList.forEach(msd -> msd.toEntity(menuGroup));
        }

        return menuGroup;
    }
}
