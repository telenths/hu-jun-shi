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

$(document).ready(function() {
	initFooterLinks();

	$('#table_contact tr>td:even').addClass('center_align_left');
	$('#table_contact tr>td:odd').addClass('center_align_right');

	$('.salse_section tr>td:even').addClass('center_align_left');
	$('.salse_section tr>td:odd').addClass('center_align_right');
    
});
