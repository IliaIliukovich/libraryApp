package com.telran.libraryapp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", columnDefinition = "int")
    private Long id;

    private String name;

    private String address;

    @Column(columnDefinition = "tinyint")
    private boolean hasReadingRoom;

    @OneToMany(mappedBy = "building")
    List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return hasReadingRoom == building.hasReadingRoom && Objects.equals(id, building.id) && Objects.equals(name, building.name) && Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, hasReadingRoom);
    }
}
