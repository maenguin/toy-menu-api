package maenguin.toymenuapi.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name"})
public class MenuOptionGroup {

    @Id
    @GeneratedValue
    @Column(name = "menu_option_group_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer maxSelectCount;

    private Integer minSelectCount;

    private boolean visible;

    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "menuOptionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 100)
    private List<MenuOption> menuOptions = new ArrayList<>();

    @Builder
    public MenuOptionGroup(String name, Integer maxSelectCount, Integer minSelectCount, boolean visible, Integer priority) {
        this.name = name;
        this.maxSelectCount = maxSelectCount;
        this.minSelectCount = minSelectCount;
        this.visible = visible;
        this.priority = priority;
    }

    public void changeMenu(Menu menu) {
        this.menu = menu;
        menu.getMenuOptionGroups().add(this);
    }

}
