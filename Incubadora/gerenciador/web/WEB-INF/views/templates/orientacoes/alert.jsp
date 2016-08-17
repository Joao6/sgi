<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="modal-alert-${orientacao.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <i class="modal-title glyphicon glyphicon-alert"></i>
      </div>
      <div class="modal-body">
        <p class="alert alert-warning">Deseja realmente excluir esta Orientação <strong>${orientacao.categoria.nome}</strong>?<br/>          
        </p>
        <p class="alert alert-info">
          <small><strong><i class="glyphicon glyphicon-info-sign"></i> Esta opera&ccedil;&atilde;o n&atilde;o poder&aacute; ser desfeita posteriormente!</strong></small>
        </p>
      </div>
      <div class="modal-footer">
        <a href="<c:url value="/orientacao/${orientacao.id}/excluir"/>"><button type="button" class="btn btn-danger">Confirmar</button></a>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->