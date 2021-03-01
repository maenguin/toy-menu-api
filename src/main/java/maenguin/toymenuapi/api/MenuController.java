package maenguin.toymenuapi.api;

import lombok.*;
import maenguin.toymenuapi.dto.Menu.MenuDto;
import maenguin.toymenuapi.dto.Menu.MenuGroupDto;
import maenguin.toymenuapi.dto.Menu.MenuGroupSaveDto;
import maenguin.toymenuapi.dto.Menu.MenuSaveDto;
import maenguin.toymenuapi.repository.MenuRepository;
import maenguin.toymenuapi.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/api/v1/store/menu-group/menu/{menuId}")
    public ApiResponse<MenuDto> findMenuById(@PathVariable("menuId") Long menuId) {
        return new ApiResponse<>(menuService.findMenuById(menuId));
    }

    @GetMapping("/api/v1/store/{storeId}/menu-group")
    public ApiResponse<List<MenuGroupDto>> findAllMenuByStore(@PathVariable("storeId") Long storeId) {
        return ApiResponse.of(menuService.findAllByStore(storeId));
    }

    @PostMapping("/api/v1/store/{storeId}/menu-group")
    public void saveMenuGroup(@PathVariable("storeId") Long storeId, @RequestBody MenuGroupSaveDto menuGroupSaveDto) {
        menuService.saveMenuGroup(storeId, menuGroupSaveDto);
    }
    @PostMapping("/api/v1/store/menuGroup/{menuGroupId}/menu")
    public void saveMenu(@PathVariable("menuGroupId") Long menuGroupId, @RequestBody MenuSaveDto menuSaveDto) {
        menuService.saveMenu(menuGroupId, menuSaveDto);
    }

    @PutMapping("/api/v1/store/menuGroup/{menuGroupId}")
    public void updateMenuGroup(@PathVariable("menuGroupId") Long menuGroupId, @RequestBody MenuGroupSaveDto menuGroupSaveDto) {
        menuService.updateMenuGroup(menuGroupId, menuGroupSaveDto);
    }
    @PutMapping("/api/v1/store/menuGroup/menu/{menuId}")
    public void updateMenu(@PathVariable("menuId") Long menuId,@RequestBody MenuSaveDto menuSaveDto) {
        menuService.updateMenu(menuId, menuSaveDto);
    }



    @DeleteMapping("/api/v1/store/menuGroup/{menuGroupId}")
    public void deleteMenuGroup(@PathVariable("menuGroupId") Long menuGroupId) {
        menuService.deleteMenuGroup(menuGroupId);
    }
    @DeleteMapping("/api/v1/store/menuGroup/menu/{menuId}")
    public void deleteMenu(@PathVariable("menuId") Long menuId) {
        menuService.deleteMenu(menuId);
    }
    @DeleteMapping("/api/v1/store/menuGroup/menu/menuOptionGroup/{menuOptionGroupId}")
    public void deleteMenuOptionGroup(@PathVariable("menuOptionGroupId") Long menuOptionGroupId) {
        menuService.deleteMenuGroup(menuOptionGroupId);
    }




    @Data
    static class ApiResponse<T>{
        private T data;

        private ApiResponse(T data) {
            this.data = data;
        }

        public static ApiResponse of (Object data) {
            ApiResponse<Object> response = new ApiResponse<>(data);
            return response;
        }
    }
}