/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.base;

/**
 *
 * @author William
 */
public abstract class BaseArquivo extends BaseEntity{

 private final String path;

  public String getPath() {
    return path;
  }

  public BaseArquivo(String path) {
    this.path = path;
  }
}
