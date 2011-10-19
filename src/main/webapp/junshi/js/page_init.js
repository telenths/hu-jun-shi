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

$(document).ready(function() {
    initMenu();
});
