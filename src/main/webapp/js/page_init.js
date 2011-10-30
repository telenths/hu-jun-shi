function initFooterLinks() {
    $.getJSON('/json/link_coop.json?t=$[DATETIME]', function(data) {
        var html = "";
        $.each(data, function(entryIndex, entry) {
            if (entry['img']) {
            	html += "<a target='_blank' href='"+entry['link']+"'><img src='"+entry['img']+"' alt='"+entry['alt']+"'></a>"
            }
        });
        $('#coop_links').prepend(html);
    });
}

function initMenuSolution(){
    $.getJSON('/json/menu_solutions.json?t=$[DATETIME]', function(data) {
        var html = "";
        $.each(data, function(entryIndex, entry) {
            if (entry['menu']) {
                html += "<a style='width:100%' href='" + entry['link'] + "'><div class='"+entry['class']+"'>" + entry['menu'] + "</div></a>";
            }
            
            if(entry['data']){
            	$.each(entry['data'], function(entryIndex, entry){
                  if (window.location.pathname == entry['link'])
                	  html += "<div class='"+entry['class']+"_current'>" + entry['item'] + "</div>";
                  else
                	  html += "<a style='width:100%' href='" + entry['link'] + "'><div class='"+entry['class']+"'>" + entry['item'] + "</div></a>";
            	});
            }
            
        });
        $('#menu_solution').prepend(html);
    });
}

$(document).ready(function() {
	initFooterLinks();
    if($("#menu_solution")){
    	initMenuSolution();
    }
    

	$('#table_contact tr>td:even').addClass('center_align_left');
	$('#table_contact tr>td:odd').addClass('center_align_right');

	$('.salse_section tr>td:even').addClass('center_align_left');
	$('.salse_section tr>td:odd').addClass('center_align_right');
	$('.salse_section tr:first-child>td:even').removeClass('center_align_left');
    
});
