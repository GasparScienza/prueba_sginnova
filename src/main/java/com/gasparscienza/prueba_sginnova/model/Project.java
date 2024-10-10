package com.gasparscienza.prueba_sginnova.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre del proyecto no puede ser nulo")
    @NotBlank(message = "El nombre del proyecto no puede estar en blanco")
    @Size(max = 25, message = "El nombre debe tener como máximo 25 caracteres")
    private String name;
    
    @NotNull(message = "La descripcion no puede ser nula")
    @NotBlank(message = "La descripcion no puede estar en blanco")
    private String description;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
}
