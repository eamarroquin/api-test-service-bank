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
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta", nullable = false)
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 50)
    private String numeroCuenta;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_tipo_cuenta", nullable = false, referencedColumnName = "id_tipo")
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo", nullable = false, scale = 2, columnDefinition = "double default 0")
    private Double saldo;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_cliente", nullable = false, referencedColumnName = "id_cliente")
    private Cliente cliente;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_ciudad", nullable = false, referencedColumnName = "id_ciudad")
    private Ciudad ciudad;

    @ToString.Exclude
    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos  = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cuenta cuenta = (Cuenta) o;
        return id != null && Objects.equals(id, cuenta.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
