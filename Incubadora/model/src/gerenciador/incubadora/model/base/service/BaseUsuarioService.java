package gerenciador.incubadora.model.base.service;

import gerenciador.incubadora.model.base.BaseService;
import gerenciador.incubadora.model.entity.Usuario;

public interface BaseUsuarioService extends BaseService<Usuario> {

  public Usuario login(String usuario, String senha) throws Exception;

  public void getCodedEntity(Usuario usuario) throws Exception;

}
