package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import maenguin.toymenuapi.domain.Menu;
import maenguin.toymenuapi.domain.MenuGroup;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class MenuSaveDto {

    private String name;

    private int price;

    private int count;

    private boolean signature;

    private boolean visible;

    private Integer priority;


    private List<MenuOptionGroupSaveDto> menuOptionGroupSaveList;

    public Menu toEntity(MenuGroup menuGroup) {
        Menu menu = Menu.builder()
                .name(name)
                .price(price)
                .count(count)
                .signature(signature)
                .visible(visible)
                .priority(priority)
                .build();
        menu.changeMenuGroup(menuGroup);

        if (menuOptionGroupSaveList != null && menuOptionGroupSaveList.size() > 0) {
            menuOptionGroupSaveList.forEach(mog -> mog.toEntity(menu));
        }


        return menu;
    }
}
