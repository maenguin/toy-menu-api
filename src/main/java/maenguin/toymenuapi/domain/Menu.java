package maenguin.toymenuapi.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name","price","menuGroup"})
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private int price;

    private int count;

    private boolean signature;

    private boolean visible;

    private Integer priority;

    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    @ElementCollection
    @CollectionTable(name = "menu_basic_option", joinColumns = @JoinColumn(name = "menu_id"))
    @BatchSize(size = 100)
    private List<MenuBasicOption> menuBasicOptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;


    @BatchSize(size = 100)
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOptionGroup> menuOptionGroups = new ArrayList<>();

    @Builder
    private Menu(String name, int price, int count, boolean signature, boolean visible, Integer priority, MenuStatus status) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.signature = signature;
        this.visible = visible;
        this.priority = priority;
        this.status = status;
    }

    public void changeMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
        menuGroup.getMenus().add(this);
    }

    public void update(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }


}
