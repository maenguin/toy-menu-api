package maenguin.toymenuapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name"})
public class MenuGroup {

    @Id
    @GeneratedValue
    @Column(name = "menu_group_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "menuGroup", cascade = CascadeType.MERGE)
    private List<Menu> menus = new ArrayList<>();


    @Builder
    private MenuGroup(Long id, String name, Integer priority, Store store) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.store = store;
    }

    public void update(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

}
