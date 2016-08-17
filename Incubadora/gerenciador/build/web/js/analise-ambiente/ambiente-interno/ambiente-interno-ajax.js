  /* 
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  
   $(document).ready(function(){
      var pontosFortes = [];
      var pontosFracos = [];
      
      
      $(this).on('submit', '#form-ambiente-interno', function(e){
         $(this).find(".pontos-fortes").each(function(){
            pontosFortes.push($(this).val());
         });
         
         $(this).find(".pontos-fracos").each(function(){
            pontosFracos.push($(this).val());
         });
         
      });
      
      //TODO 
      
      
   });
