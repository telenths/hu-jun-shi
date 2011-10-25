function initMenu() {
    $.getJSON('/json/menu_nav.json?t=$[DATETIME]', function(data) {
        var html = "";
        $.each(data, function(entryIndex, entry) {
            if (entry['menu']) {
//                if (window.location.pathname == entry['link'])
//                    html += "<td class='current_menu_nav_item'>" + entry['menu'] + "</td>";
//                else
                    html += "<td class='menu_nav_item'><a style='width:100%' href='" + entry['link'] + "'><div style='width:100%'>" + entry['menu'] + "</div></a></td>";
            }
        });
        $('#menu_nav').prepend(html);
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
    initMenu();
    if($("#menu_solution")){
    	initMenuSolution();
    }
    

	$('#table_contact tr>td:even').addClass('center_align_left');
	$('#table_contact tr>td:odd').addClass('center_align_right');

	$('.salse_section tr>td:even').addClass('center_align_left');
	$('.salse_section tr>td:odd').addClass('center_align_right');
	$('.salse_section tr:first-child>td:even').removeClass('center_align_left');
    
});
