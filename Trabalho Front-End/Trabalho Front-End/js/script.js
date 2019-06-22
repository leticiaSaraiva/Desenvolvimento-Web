
$(document).ready(function() {

	$("#dialog").hide();
	$(".verMaisPratos").hide();
	$("#telefone").mask("(99) 9 9999-9999");

	$("#verMais").click(MostrarMais);

})

function MostrarMais(){
    $(".verMaisPratos").show();
	$("#verMais").hide();
}


$(function() {
  var assuntos = [
    "Pratos",
    "Sobre o restaurante",
    "Localização",
    "Outro"
  ];
  $("#assunto").autocomplete({
    source: assuntos
  });
});


var input = document.querySelector('input'),
    form = document.querySelector('form');

form.addEventListener('submit', validaForm, false);

function validaForm(event) {
 
	event.preventDefault();

	var value = input.value;

	form.submit();
	alert("Agradecemos sua solicitação de contato, retornaremos em breve!");
}
