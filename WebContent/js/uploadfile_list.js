$(document).ready(function() {


	$("#file_upload_1")
	.uploadify(
			{
				removeCompleted : false,
				buttonImage : './img/bt.png',
				height : 36,
				swf : './css/uploadify.swf',
				uploader : './upload.servlet',
				width : 106,
				itemTemplate : '<div id="${fileID}" class="uploadify-queue-item" style="border: 1px solid #ccc; margin: 10px;width: 303px;float: left;margin-left: 3px;"><div class="cancel"><a href="javascript:deleteItem(\'${instanceID}\', \'${fileID}\')">X</a></div><span class="fileName">${fileName} (${fileSize})</span><span class="data"></span><input type="hidden" class="realpath" name="uploadfiles"></input><input type="hidden" name="uploadfilenames" value="${fileName}" /></div>',
				'onUploadSuccess' : function(file,
						data, response) {
					//alert(file.id);
					var obj = jQuery
							.parseJSON(data);
					if (obj.type == 'success') {
						$('#' + file.id).find(
								'.realpath').val(
								obj.path);
					}

					//alert('The file was saved to: ' + obj.plugin);
				},
				'onUploadComplete' : function(file) {
					//alert('The file ' + file.name + ' finished processing.');
				}
			});
	
});