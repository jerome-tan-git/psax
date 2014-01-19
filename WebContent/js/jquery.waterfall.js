/*!
 * jQuery Waterfall v1.2
 * 
 * Author		: LeoLai
 * Blog			: http://leolai.cnblogs.com/
 * Mail 		: leolai.mail@qq.com
 * QQ 			: 657448678 
 * Date 		: 2013-4-19 
 * Last Update 	: 2013-5-23
 *
 **************************************************************
 * 1. ����ҳ���С�Զ�����
 * 2. �Զ����첽������������JSON��json��ʽ��htmlģ���Ӧ���ɣ�Ĭ�ϸ�ʽ�뿴demo json.js��
 * 3. �Զ���htmlģ��
 * 4. ͼƬ�Զ�����������
 * 5. �Ƿ���ʾ��ҳ(δ���)
 * usage: url�������������ʹ��Ĭ������
	$('#id').waterfall({
		itemClass: 'wf_item',	// ש������
		imgClass: 'thumb_img',	// ͼƬ����
		colWidth: 235,			// �п�
		marginLeft: 15,			// ÿ�е�����
		marginTop: 15,			// ÿ�е��ϼ��
		perNum: 'auto',			// ÿ������ʱ��ʾ���ٸ�(Ĭ��������)
		isAnimation: true,		// �Ƿ�ʹ�ö���Ч��
		ajaxTimes: 'infinite',	// ���Ƽ��صĴ���(int) �ַ���'infinite'��ʾ���޼��� 
		url: null,				// ������Դ(ajax���أ�����json��ʽ)��������ajaxFunc�������˲�������Ч
		ajaxFunc: null,			// �Զ����첽����, ��һ������Ϊ�ɹ��ص��������ڶ�������Ϊʧ�ܻص�����
								// ��ִ�гɹ��ص�����ʱ�����뷵�ص�JSON������Ϊ����
		createHtml: null		// �Զ�������html�ַ�������,����Ϊһ����Ϣ���ϣ�����һ��html�ַ���
	});
 *
 */
 
;(function($, window, document){
	$.fn.waterfall = function(options){
		var // ������Ϣ
			opts = $.extend({}, $.fn.waterfall.defaults, options), 
			
			isIE6 = !-[1,] && !window.XMLHttpRequest,
		
			ajaxTimes = 0,		// �������������Ĵ���
			isLoading = false,	// �Ƿ����ڼ�������
			isFinish = false,	// trueʱ�������������������
			
			colsHeight = [],	// ���ڴ洢ÿ�еĸ߶�
			minColsIndex = 0,	// ������е��±�
			
			jsonCache = [],		// ���������ص�JSON��������
			
			wf_box_top = 0,		// $wf_box �������ͼ��λ�ø߶�
			wf_item_top = 0,	// �ٲ������top, leftֵ
			wf_item_left = 0,
			
			// һЩjQ����
			$wf_box, $wf_col, 
			$wf_col_temp, $wf_col_items,
			$wf_result, $backTop,
			
			// �첽������
			ajaxFunc = $.isFunction(opts.ajaxFunc) ?
						opts.ajaxFunc :
						function(success, error){
							$.ajax({
								type: 'GET',
								url: opts.url,
								cache: false,
								data: opts.params,
								dataType:'json',
								timeout: 60000,
								success: success,
								error: error
							});
						},
			// ����html�ַ�������
			createHtml = $.isFunction(opts.createHtml) ?��
					opts.createHtml��:
					function(data){
						return '<div class="wf_item_inner">' +
								  '<a href="'+ data.href +'" class="thumb" target="_blank">' +
									'<img class="'+opts.imgClass+'"  src="'+ data.imgSrc +'" />' +
								  '</a>' +
								  '<h3 class="title"><a href="'+ data.href +'" target="_blank">'+ data.title +'</a></h3>' +
								  '<p class="desc">'+ data.describe +'</p>' +
							  '</div>';
					};
		
		
		
		// usage:
		// fixedPosition(elem, {top:0, left:0});
		// fixedPosition(elem, {bottom:0, right:0});
		var fixedPosition = function(){
			var html = document.getElementsByTagName('html')[0],
				dd = document.documentElement,
				db = document.body,
				doc = dd || db;
			
			// ��IE6 fixed �ṩһ��"�������Ļ���"
			// ֻ��Ҫ html �� body ��ǩ��һʹ�ñ�����ֹ��λ������IE6�¹������϶�Ԫ��Ҳ���ᶶ��
			// ע�⣺IE6��� body �Ѿ������˱���ͼ��ֹ��λ�󻹸� html ��ǩ���û��� body ���õı�����ֹ(fixed)ʧЧ
			if (isIE6 && db.currentStyle.backgroundAttachment !== 'fixed') {
				html.style.backgroundImage = 'url(about:blank)';
				html.style.backgroundAttachment = 'fixed';
			};
			
			// pos = {top:0, right:0, bottom:0, left:0}
			return isIE6 ? 
				function(elem, pos){
					var style = elem.style,
						dom = '(document.documentElement || document.body)'; 
					
					if(typeof pos.left !== 'number'){
						pos.left = doc.clientWidth - pos.right - elem.offsetWidth; 
					}
					if(typeof pos.top !== 'number'){
						pos.top = doc.clientHeight - pos.bottom - elem.offsetHeight; 
					}
					
					elem.style.position = 'absolute';
					style.removeExpression('left');
					style.removeExpression('top');
					style.setExpression('left', 'eval(' + dom + '.scrollLeft + ' + pos.left + ') + "px"');
					style.setExpression('top', 'eval(' + dom + '.scrollTop + ' + pos.top + ') + "px"');
				} : 
				function(elem, pos){
					var style = elem.style;
						
					style.position = 'fixed';
					
					if(typeof pos.left === 'number'){
						style.left = pos.left + 'px';
					}else{
						style.left = 'auto'; 
						style.right = pos.right + 'px';
					}
					
					if(typeof pos.top === 'number'){
						style.top = pos.top + 'px';
					}else{
						style.top = 'auto'; 
						style.bottom = pos.bottom + 'px';
					}
				 
				};
		}();
		
		
		// �첽��ȡ����
		function getJSONData(){
			if(!(isFinish || isLoading)){ // ȷ����һ�μ�����ϲŷ����µ�����
				// ����������ʱ�ж��Ƿ���Ҫ��������������ݻ����Ǵ���������
				if(colsHeight.minHeight + wf_box_top < $(window).height() + $(window).scrollTop()){
					// ������滹�����ݣ�ֱ�Ӵ�������
					if(jsonCache.length > 0){
						dealData();
					}else{
						if(opts.ajaxTimes === 'infinite' || ajaxTimes < opts.ajaxTimes){
							showMsg('loading');
							// ���θ�������
							opts.params.ajax = ++ajaxTimes;
							ajaxFunc(
								function(jsonData){
									try{
										if(typeof jsonData === 'string') jsonData = $.parseJSON(jsonData);
										if($.isEmptyObject(jsonData) || typeof jsonData === 'string'){
											showMsg('finish');
										}else{
											jsonCache = jsonCache.concat(jsonData).reverse();
											dealData();
										}
									}
									catch(e){
										showMsg('error');
									}
								}, 
								function(){
									showMsg('error');
								}
							);
									
						}else{
							showMsg('finish');
						}
					}
					
				}
			}
		}
		
		// �����ص�����
		function dealData(){
			var perNum = typeof opts.perNum === 'number' ? opts.perNum : opts.colNum,
				data = null,
				wf_col_height = $wf_col.height(),
				$wf_item, $wf_img, htmlStr;
			// ȷ������ͼƬ����֪���
			loadImg(jsonCache, opts.imgUrlName, function(){
				while(perNum-- > 0 && (data = jsonCache.pop())){
					
					minColsIndex = getColsIndex(colsHeight)[0];
					
					wf_item_left = minColsIndex * (opts.colWidth + opts.marginLeft);
					wf_item_top = colsHeight[minColsIndex] + opts.marginTop;
					
					htmlStr = createHtml(data);
					
					$wf_item = $('<div>').addClass(opts.itemClass).html(htmlStr)
								.css({width:opts.colWidth, left: wf_item_left, top: wf_item_top})
								.appendTo($wf_col);
					$wf_img = $wf_item.find('.'+opts.imgClass);
					$wf_img.height($wf_img.width() / data.width * data.height);
					
					if(opts.isAnimation){
						$wf_item.css({opacity:0}).animate({
													opacity: 1
												}, 800);
					}
					
					// ����ÿ�еĸ߶�
					colsHeight[minColsIndex] = wf_item_top + $wf_item.outerHeight();
					if( colsHeight[minColsIndex] > colsHeight.maxHeight ){
						colsHeight.maxHeight = colsHeight[minColsIndex];
					}
					$wf_col.height(colsHeight.maxHeight);
				}
				
				isLoading = false;
				$wf_result.hide();
				
				// ��֤�������Ч����
				getJSONData();
				
			});
		}
		
		// �����ٲ����Ŀ�
		function realign(){
			var colNum = 0,
				i = 0,
				backTop_left =  0,
				speed = 0;
			
			// �������ǰ��Ļ�����Ŷ�����
			colNum = Math.floor(($wf_box.width() + opts.marginLeft) / (opts.colWidth + opts.marginLeft));
			
			if(colNum > 0 && colNum !== opts.colNum){
				opts.colNum = colNum;
				$wf_col.width((opts.colWidth+opts.marginLeft) * opts.colNum - opts.marginLeft);
				
				// ���µ����洢��
				for(i=0; i<opts.colNum; i++){
					colsHeight[i] = 0;
				}
				colsHeight.length = opts.colNum;
				
				$wf_col_items = $wf_col.children('.wf_item');
				$wf_col_items.each(function(num, value){
					minColsIndex = getColsIndex(colsHeight)[0];
					wf_item_top = colsHeight[minColsIndex] + opts.marginTop;
					wf_item_left = minColsIndex * (opts.colWidth + opts.marginLeft);
					
					
					if(opts.isAnimation) speed = 300;
					$(this).width(opts.colWidth).animate({
													left:wf_item_left, 
													top:wf_item_top
												}, speed);
					
					
					colsHeight[minColsIndex] = wf_item_top + $(this).outerHeight();
				});
				
				getColsIndex(colsHeight);
				$wf_col.height(colsHeight.maxHeight);
				
				getJSONData();
			}
			
			// ���ض�����ťλ��
			backTop_left = $wf_col.offset().left + ($wf_col.width() + $wf_box.width()) / 2 - $backTop.width(); 
			
			fixedPosition($backTop[0], {
				left: backTop_left,
				bottom: 0
			});
			
		}
		
		// ��ʾ�����Ϣ
		function showMsg(type){
			switch(type){
				case 'loading':
					isLoading = true;
					$wf_result.html('').addClass('wf_loading').show();
					break;
				case 'error':
					$wf_result.removeClass('wf_loading').show().html('���ݸ�ʽ�����뷵�ر�׼��Json���ݻ�Json��ʽ�ַ�����');
					isFinish =  true;
					break;
				case 'finish':
					$wf_result.removeClass('wf_loading').show().html('�Ѽ�����ϣ�û�и����ˣ�');
					isFinish = true;
					break;
			}
		}
		
		return this.each(function(){
			if($(this).data('_wf_is_done_')) return true;
			
			$wf_box = $(this).addClass('waterfall').data('_wf_is_done_', true);
			wf_box_top = $wf_box.offset().top;	// ���� $wf_box �������ͼ��λ�ø߶�
			
			$wf_col = $wf_box.children('.wf_col');
			$wf_col.length === 0 && ($wf_col = $('<div>').addClass('wf_col').appendTo($wf_box));
			$wf_result = $('<div>').addClass('wf_result').appendTo($wf_box);
			
			// ���ӷ��ض�����ť
			$backTop = $('<a></a>').attr('id', 'backTop').attr('title', '���ض���').appendTo(document.body);
			$backTop.css('opacity', 0).bind('click', function(){
				$('body,html').stop(true).animate({
					scrollTop: wf_box_top
				}, 500);
			});
			
			$(document.body).css('overflow', 'scroll');
			// �����Ѿ����ڵ��ٲ�����
			realign();
			$(document.body).css('overflow', 'auto');
			
			// ��һ����ȡͼƬʱ����֤ͼƬ�����������ֹ���
			getJSONData();
			
			// ע��������¼�
			$(window).bind('scroll', function(){
				if($(window).scrollTop() > wf_box_top){
					$backTop.stop(true).animate({opacity: 1}, 500);
				}else{
					$backTop.stop(true).animate({opacity: 0}, 500);
				}
				getJSONData();
				
			// ע�ᴰ�ڸı��С�¼�
			}).bind('resize', function(){
				throttle(realign);
			});
		});
	};
	
	// Ĭ������
	$.fn.waterfall.defaults = {
		itemClass: 'wf_item',	// ש������
		imgClass: 'thumb_img',	// ͼƬ����
		colWidth: 235,			// �п�(int)
		marginLeft: 15,			// ÿ�е�����(int)
		marginTop: 15,			// ÿ�е��ϼ��(int)
		perNum: 'auto',			// ÿ������ʱ��ʾ���ٸ�(Ĭ��������)
		isAnimation: true,		// �Ƿ�ʹ�ö���Ч��
		ajaxTimes: 'infinite',	// �����첽����Ĵ���(int) �ַ���'infinite'��ʾ���޼���
		imgUrlName: 'imgSrc',	// ��json���ʾͼƬ·������������(����Ԥ����ͼƬ��ȡ�߿�)
		params: {},				// ��ֵ�ԣ����͵������������ݡ����Զ�ת��Ϊ�����ַ�����ʽ��
								// �� {foo:["bar1", "bar2"]} ת��Ϊ "&foo=bar1&foo=bar2"��
		url: '',				// ������Դ(ajax���أ�����json��ʽ)��������ajaxFunc�������˲�����ʡ��(string)
		// �Զ����첽����, ��һ������Ϊ�ɹ��ص��������ڶ�������Ϊʧ�ܻص�����
		// ��ִ�гɹ��ص�����ʱ�����뷵�ص�JSON������Ϊ����
		ajaxFunc: null,		// (function)
		createHtml: null	// �Զ�������html�ַ�������,����Ϊһ����Ϣ���ϣ�����һ��html�ַ���(function)
		
	};
	
	
	/*****************һЩȫ�ֺ���*********************/
	/**
	 * ͼƬͷ���ݼ��ؾ����¼�
	 * @�ο� 	http://www.planeart.cn/?p=1121
	 * @param	{String}	ͼƬ·��
	 * @param	{Function}	�ߴ���� (����1����width; ����2����height)
	 * @param	{Function}	������� (��ѡ. ����1����width; ����2����height)
	 * @param	{Function}	���ش��� (��ѡ)
	 */
	var imgReady = (function(){
		var list = [], intervalId = null,
		
		// ����ִ�ж���
		tick = function () {
			var i = 0;
			for (; i < list.length; i++) {
				list[i].end ? list.splice(i--, 1) : list[i]();
			};
			!list.length && stop();
		},

		// ֹͣ���ж�ʱ������
		stop = function () {
			clearInterval(intervalId);
			intervalId = null;
		};

		return function (url, ready, load, error) {
			var check, width, height, newWidth, newHeight,
				img = new Image();
			
			
			if(!url){
				error && error();
				return;
			}
			
			img.src = url;

			// ���ͼƬ�����棬��ֱ�ӷ��ػ�������
			if (img.complete) {
				ready(img.width, img.height);
				load && load(img.width, img.height);
				return;
			};
			
			// ���ͼƬ��С�ĸı�
			width = img.width;
			height = img.height;
			check = function () {
				newWidth = img.width;
				newHeight = img.height;
				if (newWidth !== width || newHeight !== height ||
					// ���ͼƬ�Ѿ��������ط����ؿ�ʹ��������
					newWidth * newHeight > 1024
				) {
					ready(newWidth, newHeight);
					check.end = true;
				};
			};
			check();
			
			// ���ش������¼�
			img.onerror = function () {
				error && error();
				check.end = true;
				img = img.onload = img.onerror = null;
			};
			
			// ��ȫ������ϵ��¼�
			img.onload = function () {
				load && load(img.width, img.height);
				!check.end && check();
				// IE gif������ѭ��ִ��onload���ÿ�onload����
				img = img.onload = img.onerror = null;
			};

			// ��������ж���ִ��
			if (!check.end) {
				list.push(check);
				// ���ۺ�ʱֻ�������һ����ʱ��������������������
				if (intervalId === null) intervalId = setInterval(tick, 40);
			};
		};
	})();
	
	// ���ٻ�ȡͼƬͷ���ݣ����ؾ�����ִ�лص�����
	function loadImg(jsonData, imgUrlName, callback){
		var count = 0,
			i = 0,
			intervalId = null,
			data = null,
			imgSrc = 
			done = function(){
				 if(count === jsonData.length) {
					 clearInterval(intervalId);
					 callback && callback();
				 }
			};
		for(; i<jsonData.length; i++){
			data = jsonData[i];
			data.height = parseInt(data.height);
			data.width = parseInt(data.width);
			
			// �����֪ͼƬ�ĸ߶ȣ�������
			if(data.height >= 0 && data.width >= 0){
				++count;
			}else{
				(function(data){
					imgReady(data[imgUrlName], function(width,height){
						// ͼƬͷ���ݼ��ؾ�����������
						data.width = width;
						data.height = height;
						++count;
					}, null, function(){
						// ͼƬ����ʧ�ܣ��滻��Ĭ��ͼƬ
						data.width = 208;
						data.height = 240;
						data.imgSrc = 'images/default.jpg';
						++count;
					});
				})(data);
			}
		}
		
		intervalId = setInterval(done, 40);
	}
	
	/*
	 * ����������������Ϊ��Ƶ�ʵĸ��ĵ��������������������onresize�¼���������Ը��ӵ�DOM����
	 * ˼·����һ��ʱ�����ظ�ִ��ĳ����ִֻ��һ�Ρ�
	 */
	function throttle(method, context){
		clearTimeout(method.tid);
		context = context || null;
		method.tid = setTimeout(function(){
			method.call(context);				
		},100);
	}
	
	// ���ش�С���������������±������
	// e.g. ��������[300,200,250,400] ����[1,2,0,3]
	function getColsIndex(arr){
		var clone = arr.slice(),	// ���鸱��������ı�ԭ����
			ret = [], 	// ��Ӧ�±�����
			len = arr.length,
			i, j, temp;
			
		for(i=0;i<len;i++){
			ret[i] = i;
		}
		
		//���ѭ��(ð�����򷨣���С����)
		for(i=0;i<len;i++){
			//�ڲ�ѭ��
			for(j=i;j<len;j++){
				if(clone[j] < clone[i]){
					//��������Ԫ�ص�λ��
					temp=clone[i];
					clone[i]=clone[j];
					clone[j]=temp;
					
					temp=ret[i];
					ret[i]=ret[j];
					ret[j]=temp;
				}
			}
		}
		arr.minHeight = arr[ret[0]];
		arr.maxHeight = arr[ret[ret.length -1]];
		return ret;
	}

})(jQuery, window, document);