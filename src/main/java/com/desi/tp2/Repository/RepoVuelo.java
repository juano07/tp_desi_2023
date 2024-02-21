package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoVuelo extends JpaRepository <ModelVuelo, Integer>{

    @Query("SELECT v FROM ModelVuelo v ORDER BY v.fechayhora")
    List<ModelVuelo> findAllOrderedByDate();
    
    @Query("SELECT v FROM ModelVuelo v WHERE DATE(v.fechayhora) = DATE(:fechayhora)")
    List<ModelVuelo> findVuelosByFecha(@Param("fechayhora") LocalDateTime fechayhora);

    @Query("SELECT v FROM ModelVuelo v WHERE v.avion.idAvion = :idAvion AND DATE(v.fechayhora) = DATE(:fechayhora)")
    Optional<ModelVuelo> findVueloInConflict(@Param("idAvion") int idAvion, @Param("fechayhora") LocalDateTime fechayhora);
}
