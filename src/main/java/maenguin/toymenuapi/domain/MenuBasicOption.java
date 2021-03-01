package maenguin.toymenuapi.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(of = {"name"})
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuBasicOption {


    private String name;

    private int price;

    public MenuBasicOption(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
