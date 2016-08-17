/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.base.dao;

import gerenciador.incubadora.model.base.BaseDAO;
import gerenciador.incubadora.model.entity.CronogramaAnual;
import gerenciador.incubadora.model.entity.CronogramaManager;
import java.sql.Connection;

/**
 *
 * @author William
 */
public interface BaseCronogramaManagerDAO extends BaseDAO<CronogramaManager> {

  public boolean insertGestor(Connection conn, CronogramaAnual cronogramaAnual) throws Exception;
  
  public boolean insertPraticaChave(Connection conn, CronogramaAnual cronogramaAnual) throws Exception;
  
  public boolean deleteGestor(Connection conn, Long idCronograma, Long idGestor) throws Exception;

  public boolean deletePraticaChave(Connection conn, CronogramaAnual cronogramaAnual) throws Exception;
}
