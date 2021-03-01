package maenguin.toymenuapi.dto.Menu;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(of = {"menuGroupName","menuName","menuOptionGroupName"})
public class MenuFlatDto {

    private String menuGroupName;
    private String menuName;
    private String menuOptionGroupName;

    public MenuFlatDto(String menuGroupName, String menuName, String menuOptionGroupName) {
        this.menuGroupName = menuGroupName;
        this.menuName = menuName;
        this.menuOptionGroupName = menuOptionGroupName;
    }
}
