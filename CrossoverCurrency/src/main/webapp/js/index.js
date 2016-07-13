$(document).ready(function() {
  
  var animating = false,
      submitPhase1 = 1100,
      submitPhase2 = 400,
      logoutPhase1 = 800,
      uri='http://localhost:8080/CrossoverCurrency/currency/currency/',
      trHTMLi='<tr><th style="font-size: 10px;">Currency</th><th style="font-size: 10px;width:100%;">Rate</th></tr>',
      tri='<tr><td style="text-align:center;font-size: 10px;">',
      tdi='</td><td style="text-align:center;font-size: 10px;">',
      $login = $(".login"),
      $app = $(".app");
  
  function ripple(elem, e) {
    $(".ripple").remove();
    var elTop = elem.offset().top,
        elLeft = elem.offset().left,
        x = e.pageX - elLeft,
        y = e.pageY - elTop;
    var $ripple = $("<div class='ripple'></div>");
    $ripple.css({top: y, left: x});
    elem.append($ripple);
  };
  
  $(document).on("click", ".rate__submit", function(e) {
    if (animating) return;
    animating = true;
    var that = this;
    ripple($(that), e);
    $(that).addClass("processing");
    setTimeout(function() {
      $(that).addClass("success");
      setTimeout(function() {
        $app.show();
        $app.css("top");
        $app.addClass("active");
      }, submitPhase2 - 70);
      setTimeout(function() {
        $login.hide();
        $login.addClass("inactive");
        animating = false;
        $(that).removeClass("success processing");
      }, submitPhase2);
    }, submitPhase1);
    
    if($('#from').val()==''){
    	$('#from').val('USD');
    }
    
    $.ajax({
        url: uri+$('#from').val(),
        type: 'GET',
        success: function (response) {
            var trHTML = trHTMLi;
            $.each(response.rates, function (key, value) {
                trHTML += tri + key + tdi + value + '</td></tr>';
            });
            $('#records_table').empty();
            $('#records_table').append(trHTML);
            $('#from2').val($('#from').val());
        }
    });
    
  });
  
  $(document).on("click", ".refresh_curr", function(e) {
	  if($('#from2').val()==''){
	    	$('#from2').val('USD');
	  }
	  $.ajax({
	        url: uri+$('#from2').val(),
	        type: 'GET',
	        success: function (response) {
	            var trHTML = trHTMLi;
	            $.each(response.rates, function (key, value) {
	                trHTML += tri + key + tdi + value + '</td></tr>';
	            });
	            $('#records_table').empty();
	            $('#records_table').append(trHTML);
	            $('#from').val($('#from2').val());
	        }
	    });
  });
  
  $(document).on("click", ".login__submit", function(e) {
	  $.ajax({
	        url: uri+$('#from').val()+'/'+$('#to').val()+'/'+$('#amount').val(),
	        type: 'GET',
	        success: function (response) {
	        	if(response.ok==true){
	        		$('#result').val($('#amount').val()+' '+$('#from').val()+' = '+ response.message+' '+$('#to').val());
	        	}else{
	        		$('#result').val('Enter a valid 3 digit currency');
	        	}
	        },
	        error: function (request, error) {
	        	$('#result').val('Enter a valid 3 digit currency');
	        }
	    });
  });
  
  $(document).on("click", ".app__logout", function(e) {
    if (animating) return;
    $(".ripple").remove();
    animating = true;
    var that = this;
    $(that).addClass("clicked");
    setTimeout(function() {
      $app.removeClass("active");
      $login.show();
      $login.css("top");
      $login.removeClass("inactive");
    }, logoutPhase1 - 120);
    setTimeout(function() {
      $app.hide();
      animating = false;
      $(that).removeClass("clicked");
    }, logoutPhase1);
  });
  
});