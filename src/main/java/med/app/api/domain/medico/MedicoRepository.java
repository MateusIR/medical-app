package med.app.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
select m from Medico m
where m.ativo = true
and m.especialidade = :especialidade
and m.id not in (
select c.medico.id from Consulta c
where c.data = :data
and c.cancelada = false
)
order by rand()
limit 1
""")
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);



}
