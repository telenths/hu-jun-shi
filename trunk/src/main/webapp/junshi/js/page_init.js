function initMenu() {
    $.getJSON('/junshi/json/menu_nav.json', function(data) {
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
    $.getJSON('/junshi/json/menu_solutions.json', function(data) {
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

function initFooter(){
	var html = "君实科技（上海）  电话：13764193800（蔡亮）";
	$('#footer_text').prepend(html);
}

$(document).ready(function() {
    initMenu();
    if($("#menu_solution")){
    	initMenuSolution();
    }
    initFooter();
    
});
