package com.gasparscienza.prueba_sginnova.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasparscienza.prueba_sginnova.model.Proyect;
import com.gasparscienza.prueba_sginnova.repository.ProyectRepository;
import com.gasparscienza.prueba_sginnova.service.IProyectService;
import java.time.LocalDate;

@Service
public class ProyectService implements IProyectService{

    @Autowired
    private ProyectRepository proyectRepository;

    @Override
    public void addProyect(Proyect proyect) {
        proyectRepository.save(proyect);
    }

    @Override
    public List<Proyect> getProyects() {
        return proyectRepository.findAll();
    }

    @Override
    public void delProyect(Long id) {
        proyectRepository.deleteById(id);
    }

    @Override
    public Optional<Proyect> findProyect(Long id) {
        return proyectRepository.findById(id);
    }

    @Override
    public void editProyect(Long id, String name, String description, LocalDate startDate, LocalDate endDate) {
        Optional<Proyect> editProyect = this.findProyect(id);
        editProyect.ifPresent(proyect->{
            proyect.setName(name);
            proyect.setDescription(description);
            proyect.setEndDate(endDate);
            proyect.setStartDate(startDate);
            this.addProyect(proyect);
        });
    }
    
}
