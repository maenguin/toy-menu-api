package maenguin.toymenuapi.service;

import lombok.RequiredArgsConstructor;
import maenguin.toymenuapi.domain.Menu;
import maenguin.toymenuapi.domain.MenuGroup;
import maenguin.toymenuapi.domain.MenuOptionGroup;
import maenguin.toymenuapi.domain.Store;
import maenguin.toymenuapi.dto.Menu.*;
import maenguin.toymenuapi.repository.MenuGroupRepository;
import maenguin.toymenuapi.repository.MenuRepository;
import maenguin.toymenuapi.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final EntityManager em;
    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;
    private final StoreRepository storeRepository;


    public MenuDto findMenuById(Long menuId) {
        Menu findMenu = menuRepository.findById(menuId).orElseThrow(EntityNotFoundException::new);
        return new MenuDto(findMenu);
    }

    public List<MenuGroupDto> findAllByStore(Long storeId) {
        return menuGroupRepository.findAllByStore(storeId).stream()
                .map(MenuGroupDto::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void saveMenuGroup(Long storeId, MenuGroupSaveDto menuGroupSaveDto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(EntityNotFoundException::new);
        menuGroupRepository.save(menuGroupSaveDto.toEntity(store));
    }
    @Transactional
    public void saveMenu(Long menuGroupId, MenuSaveDto menuSaveDto) {
        MenuGroup menuGroup = menuGroupRepository.findById(menuGroupId)
                .orElseThrow(EntityNotFoundException::new);
        menuRepository.save(menuSaveDto.toEntity(menuGroup));
    }

    @Transactional
    public void deleteMenuGroup(Long menuGroupId) {
        menuGroupRepository.deleteById(menuGroupId);
    }
    @Transactional
    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    @Transactional
    public void deleteMenuOptionGroup(Long menuOptionGroupId) {
        MenuOptionGroup menuOptionGroup = em.find(MenuOptionGroup.class, menuOptionGroupId);
    }

    @Transactional
    public void updateMenuGroup(Long menuGroupId, MenuGroupSaveDto menuGroupSaveDto) {

        menuGroupRepository.findById(menuGroupId)
                        .orElseThrow(EntityNotFoundException::new)
                        .update(menuGroupSaveDto.getName(),menuGroupSaveDto.getPriority());
    }

    @Transactional
    public void updateMenuGroups(MenuGroupSaveListDto menuGroupSaveListDto) {

        List<MenuGroupSaveDto> menuGroupSaveDtos = menuGroupSaveListDto.getMenuGroupSaveDtos();
        if (menuGroupSaveDtos != null && menuGroupSaveDtos.size() > 0) {
            menuGroupSaveDtos.forEach(m -> updateMenuGroup(m.getId(), m));
        }
    }


    @Transactional
    public void updateMenu(Long menuId, MenuSaveDto menuSaveDto) {

        menuRepository.findById(menuId)
                .orElseThrow(EntityNotFoundException::new)
                .update(menuSaveDto.getName(),menuSaveDto.getPrice(), menuSaveDto.getCount());
    }











}
