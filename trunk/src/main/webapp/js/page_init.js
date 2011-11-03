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

function initBodyFooter(){
	html = "<table border='0' cellspacing='0' cellpadding='0' id='page_footer'>";
	html += "<tr><td colspan='2'>最新价格及详细内容欢迎咨询。电话：13764193800 邮件：bkcailiang@163.com</td></tr>";
	html += "</table>";
	
	$("#footer_links").before(html);
}

$(document).ready(function() {
	initFooterLinks();
	initBodyFooter();

	$('#table_contact tr>td:even').addClass('center_align_left');
	$('#table_contact tr>td:odd').addClass('center_align_right');

	$('.salse_section tr>td:even').addClass('center_align_left');
	$('.salse_section tr>td:odd').addClass('center_align_right');
    
});
