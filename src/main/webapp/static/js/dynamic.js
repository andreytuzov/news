function editArticle(id) {
	location.href = '/news/edit/' + id;
}


$("input:checkbox").on("change", function() {
	var checked = $('input:checkbox:checked');
	if (checked.length > 0) {
		$('#delete-list-article').css('display', 'inline');
	} else {
		$('#delete-list-article').css('display', 'none');
	}	
});

function deleteListArticle() {
	// Собираем какие id выбраны
	var data = '';
	$('input:checkbox:checked').each(function() {
		data += $(this).closest('li').data('id') + ",";
	});
	data = data.substring(0, data.length - 1);
	
	var result = confirm('Вы уверены, что хотите выполнить удаление ?');
	
	if (result) {
		$.ajax({
			type: "post",
			url: "/news/deletelist",
			data: {stringIDs : data},
			success: function(msg) {
				alert(msg);
				// Удаляем ранее выделенные checkbox
				$('input:checkbox:checked').each(function() {
					$(this).closest('li').remove();
				});		
				$("#delete-list-article").css('display', 'none');
			},
			error: function() {
				alert("Ошибка при удалении")
			}
		});
	}
}

function deleteArticle(articleId) {
	
	var result = confirm('Вы уверены, что хотите удалить статью ?');
	
	if (result) {
		$.ajax({
			type: "post",
			url: "/news/delete",
			data: {id : articleId},
			success: function(msg) {
				alert(msg);
				location.href = '/news/list';
			},
			error: function() {
				alert("Ошибка при удалении статьи")
			}
		});
	}
}

function goback() {
	location.href = document.referrer;
}