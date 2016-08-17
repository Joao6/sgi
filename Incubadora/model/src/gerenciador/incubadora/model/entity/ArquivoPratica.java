/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciador.incubadora.model.entity;

import gerenciador.incubadora.model.base.BaseArquivo;

/**
 *
 * @author William
 */
public class ArquivoPratica extends BaseArquivo {

  public static final String CRITERION_PATH_ARQUIVO_DEFAULT = "C:\\imagens";

  public ArquivoPratica(String path) {
    super(path);
  }

}
