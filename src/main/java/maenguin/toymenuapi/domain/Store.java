package maenguin.toymenuapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    public Store(String name) {
        this.name = name;
    }
}
