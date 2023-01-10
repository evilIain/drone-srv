package org.example.entity;

import lombok.*;
import org.example.enums.Model;
import org.example.enums.State;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drone")
public class DroneEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    @NotBlank
    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private Model model;

    @NotNull
    @Column(name = "weightLimit")
    private Integer weightLimit;

    @NotNull
    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MedicationEntity> medications;
}

