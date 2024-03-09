package com.bluesoft.servicebank.model.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ciudad")
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad", nullable = false)
    private Long id;

    @Column(name = "descripcion", length = 50, nullable = false, unique = true)
    private String descripcion;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_departamento", nullable = false, referencedColumnName = "id_departamento")
    private Departamento departamento;

    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    private List<Cuenta> cuentas  = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "ciudad")
    private List<Movimiento> movimientos  = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ciudad ciudad = (Ciudad) o;
        return id != null && Objects.equals(id, ciudad.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
