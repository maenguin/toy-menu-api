package maenguin.toymenuapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"name"})
public class MenuOption {

    @Id
    @GeneratedValue
    @Column(name = "menu_option_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private int price;

    private int count;

    private boolean visible;

    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_option_group_id")
    private MenuOptionGroup menuOptionGroup;

    @Builder
    public MenuOption(String name, int price, int count, boolean visible, Integer priority) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.visible = visible;
        this.priority = priority;
    }

    public void changeMenuOptionGroup(MenuOptionGroup menuOptionGroup) {
        this.menuOptionGroup = menuOptionGroup;
        menuOptionGroup.getMenuOptions().add(this);
    }
}
