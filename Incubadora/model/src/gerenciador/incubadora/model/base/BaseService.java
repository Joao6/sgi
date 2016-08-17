package gerenciador.incubadora.model.base;

import java.util.List;
import java.util.Map;

public interface BaseService<E extends BaseEntity> {

  public void create(E e) throws Exception;

  public E readById(Long id) throws Exception;

  public List<E> readByCriteria(Map<String, Object> criteria) throws Exception;

  public void update(E e) throws Exception;

  public void delete(Long id) throws Exception;
  
  public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception;
  
  public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception;

}
