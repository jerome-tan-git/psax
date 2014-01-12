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
											itemTemplate : '<div id="${fileID}" class="uploadify-queue-item" style="border: 1px solid #ccc;"><div class="cancel"><a href="javascript:deleteItem(\'${instanceID}\', \'${fileID}\')">X</a></div><span class="fileName">${fileName}</span><span class="data"></span><input type="hidden" class="realpath"></input></div>',
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
						
						$("#image_file_uploader")
						.uploadify(
								{
									removeCompleted : false,
									buttonImage : './img/bt.png',
									multi: false,
									height : 36,
									fileTypeExts : '*.gif; *.jpg; *.png',
									swf : './css/uploadify.swf',
									uploader : './upload.servlet',
									width : 106,
									itemTemplate : '<div id="${fileID}" class="uploadify-queue-item" style="border: 1px solid #ccc;"><div class="cancel"><a href="javascript:deleteItem(\'${instanceID}\', \'${fileID}\')">X</a></div><span class="fileName">${fileName}</span><span class="data"></span><input type="hidden" class="image_realpath" name="picurl"></input></div>',
									'onUploadSuccess' : function(file,
											data, response) {
										
										//alert(file.id);
										var obj = jQuery
												.parseJSON(data);
										if (obj.type == 'success') {
											
											$('#' + file.id).find(
													'.image_realpath').val(
													obj.path);
											$("#image_file_uploader-queue").find(".uploadify-queue-item").each(function(){
												if($(this).attr("id")!=file.id)
													{
														deleteItem("image_file_uploader", $(this).attr("id"));
													}
											});
											if($("#Exist_image").size()>0)
												{
													deleteItem("image_file_uploader", "Exist_image");
												}
										}

										//alert('The file was saved to: ' + obj.plugin);
									},
									'onUploadComplete' : function(file) {
										//alert('The file ' + file.name + ' finished processing.');
									}
								});
});