// 扩展easyui validatebox校验
$.extend(
				$.fn.validatebox.defaults.rules,
				{
					alpha : {
						validator : function(value, param) {
							if (value) {
								return /^[a-zA-Z\u00A1-\uFFFF]*$/.test(value);
							} else {
								return true;
							}
						},
						message : '只能输入字母.'
					},
					number : {
						validator : function(value, param) {
							return /^\d+$/.test(value);
						},
						message : '请输入数字.'
					},
					alphanum : {
						validator : function(value, param) {
							if (value) {
								return /^[a-zA-Z0-9]*$/.test(value);
							} else {
								return true;
							}
						},
						message : '只能输入字母和数字.'
					},
					positive_int : {
						validator : function(value, param) {
							if (value) {
								return /^[0-9]*[1-9][0-9]*$/.test(value);
							} else {
								return true;
							}
						},
						message : '只能输入正整数.'
					},
					numeric : {
						validator : function(value, param) {
							if (value) {
								return /^[0-9]*(\.[0-9]+)?$/.test(value);
							} else {
								return true;
							}
						},
						message : '只能输入数字.'
					},
					chinese : {
						validator : function(value, param) {
							if (value) {
								return /[^\u4E00-\u9FA5]/g.test(value);
							} else {
								return true;
							}
						},
						message : '只能输入中文.'
					},
					length : {
						validator : function(value, param) {
//							value = $.trim(value); // 去空格
//							return value.length >= param[0];
							 return value.getBytes() >= param[0] && value.getBytes() <= param[1];
						},
						message : '长度为 {0}到{1}个字符 .'
					},
					minLength : {
						validator : function(value, param) {
							value = $.trim(value); // 去空格
							return value.length >= param[0];
						},
						message : '请输入至少 {0}个非空字符 .'
					},
					CHS : {
						validator : function(value, param) {
							return /^[\u0391-\uFFE5]+$/.test(value);
						},
						message : '请输入汉字.'
					},
					ZIP : {
						validator : function(value, param) {
							return /^[1-9]\d{5}$/.test(value);
						},
						message : '邮政编码不存在.'
					},
					QQ : {
						validator : function(value, param) {
							return /^[1-9]\d{4,13}$/.test(value);
						},
						message : 'QQ号码不正确.'
					},
					mobile : {
						validator : function(value, param) {
							return /^(1|1|1)\d{10}$/i.test(value);
						},
						message : '以1开头的11位手机号码.'
					},
					phone : {// 座机号码/固定电话验证
						validator : function(value, param) {
							return /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(value);
							
//							return /^(\([0-9]{3,4}\)|[0-9]{3,4}\-)[0-9]{7,8}$/
//									.test(value);
						},
						message : '电话号码输入有误，匹配格式：区号3位或4位，号码7位或8位，分机号1-4位，号码之间用"-"隔开.例如:"0791-88888888-1234"'
					},
					empty : {
						validator : function(value, param) {
							return /^\S+$/gi.test(value);
						},
						message : '不允许有空格.'

					},
					legalInput : {// 合法输入
						validator : function(value, param) {
							return /^[\u0391-\uFFE5\w]+$/.test(value);
						},
						message : '只允许输入汉字、英文字母、数字及下划线.'
					},
					safepass : {
						validator : function(value, param) {
							return safePassword(value);
						},
						message : '密码由字母和数字组成，至少6位.'
					},
					equalTo : {
						validator : function(value, param) {
							return value == $(param[0]).val();
						},
						message : '两次输入的字符不一至.'
					},
					idcard : {
						validator : function(value, param) {
//							return idCard(value);
							return IdCardValidate(value);
						},
						message : '请输入正确的身份证号码.'
					},
					//用于名字惟一性验证
					remoteSingle : {
						//参数1为URL 参数2为名字ID 参数3为ID的ID，参数4为提示信息
						validator : function(value, param) {
							var name = $('#' + param[1]).val();
							var verify = {};
							verify[param[1]] = name;
							var id = $('#' + param[2]).val();
							if(id!=null&&id!=''){
								verify[param[2]] = id;
							}
							console.info(param.length);
							if(param.length >3){
								var p2 = $('#' + param[3]).val();
								if(p2!=null&&p2!=''){
									verify[param[3]] = p2;
								}
							}
							var isValidate = $.ajax({
										type : "post",
										url : param[0],
										dataType : "json",
										data : verify,
										async : false,
										cache : false
									}).responseText;

							if(isValidate=='true'){
								return true;
							}else{
								return false;
							}
						},
						message : '已有该名'
					}
				});

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
			.test(value));
};

var idCard = function(value) {
	if (value.length == 18 && 18 != value.length)
		return false;
	var number = value.toLowerCase();
	var d, sum = 0, v = '10x98765432', w = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
			9, 10, 5, 8, 4, 2 ], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	var re = number
			.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
	if (re == null || a.indexOf(re[1]) < 0)
		return false;
	if (re[2].length == 9) {
		number = number.substr(0, 6) + '19' + number.substr(6);
		d = [ '19' + re[4], re[5], re[6] ].join('-');
	} else
		d = [ re[9], re[10], re[11] ].join('-');
	if (!isDateTime.call(d, 'yyyy-MM-dd'))
		return false;
	for (var i = 0; i < 17; i++)
		sum += number.charAt(i) * w[i];
	return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
};

var isDateTime = function(format, reObj) {
	format = format || 'yyyy-MM-dd';
	var input = this, o = {}, d = new Date();
	var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format
			.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
	var len = f1.length, len1 = f3.length;
	if (len != f2.length || len1 != f4.length)
		return false;
	for (var i = 0; i < len1; i++)
		if (f3[i] != f4[i])
			return false;
	for (var i = 0; i < len; i++)
		o[f1[i]] = f2[i];
	o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
	o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
	o.dd = s(o.dd, o.d, d.getDate(), 31);
	o.hh = s(o.hh, o.h, d.getHours(), 24);
	o.mm = s(o.mm, o.m, d.getMinutes());
	o.ss = s(o.ss, o.s, d.getSeconds());
	o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
	if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0)
		return false;
	if (o.yyyy < 100)
		o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
	d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
	var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM
			&& d.getDate() == o.dd && d.getHours() == o.hh
			&& d.getMinutes() == o.mm && d.getSeconds() == o.ss
			&& d.getMilliseconds() == o.ms;
	return reVal && reObj ? d : reVal;
	function s(s1, s2, s3, s4, s5) {
		s4 = s4 || 60, s5 = s5 || 2;
		var reVal = s3;
		if (s1 != undefined && s1 != '' || !isNaN(s1))
			reVal = s1 * 1;
		if (s2 != undefined && s2 != '' && !isNaN(s2))
			reVal = s2 * 1;
		return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
	}
};
/**
 * validatebox方法扩展 coder by 小雪转中雪
 */

$.extend($.fn.validatebox.methods, {

	keyCtr : function(jq) {
		return jq.each(function() {
			var grid = $(this);
			grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind(
					'keydown',
					function(e) {
						switch (e.keyCode) {
						case 38: // up
							var selected = grid.datagrid('getSelected');
							if (selected) {
								var index = grid.datagrid('getRowIndex',
										selected);
								grid.datagrid('selectRow', index - 1);
							} else {
								var rows = grid.datagrid('getRows');
								grid.datagrid('selectRow', rows.length - 1);
							}
							break;
						case 40: // down
							var selected = grid.datagrid('getSelected');
							if (selected) {
								var index = grid.datagrid('getRowIndex',
										selected);
								grid.datagrid('selectRow', index + 1);
							} else {
								grid.datagrid('selectRow', 0);
							}
							break;
						}
					});
		});
	},
	removeTip : function(jq) {
		return jq.each(function() {
			var tip = $.data(this, "validatebox").tip;
			if (tip) {
				tip.remove();
				$.data(this, "validatebox").tip = null;
			}
		});
	},
	createTipToShow : function(jq) {
		return jq.each(function() {
			var message = $.data(this, "validatebox").message;
			var tip = $.data(this, "validatebox").tip;
			if (!tip) {
				tip = $(
						"<div class=\"validatebox-tip\">"
								+ "<span class=\"validatebox-tip-content\">"
								+ "</span>"
								+ "<span class=\"validatebox-tip-pointer\">"
								+ "</span>" + "</div>").appendTo("body");
				$.data(this, "validatebox").tip = tip;
			}
			tip.find(".validatebox-tip-content").html(message);
			$(this).validatebox('showTip');
		});
	},
	showTip : function(jq) {
		return jq.each(function() {
			var data = $.data(this, "validatebox");
			if (!data) {
				return;
			}
			var tip = data.tip;
			if (tip) {
				var box = $(this);
				var pointer = tip.find(".validatebox-tip-pointer");
				var content = tip.find(".validatebox-tip-content");
				tip.show();
				tip.css("top", box.offset().top
						- (content._outerHeight() - box._outerHeight()) / 2);
				if (data.options.tipPosition == "left") {
					tip.css("left", box.offset().left - tip._outerWidth());
					tip.addClass("validatebox-tip-left");
				} else {
					// alert($.data(this,'combo'));
					if (box.hasClass('combo-text')) {
						tip.css("left", box.offset().left + box._outerWidth()
								+ 20);
					} else {
						tip.css("left", box.offset().left + box._outerWidth());
					}
					tip.removeClass("validatebox-tip-left");
				}
				pointer.css("top", (content._outerHeight() - pointer
						._outerHeight()) / 2);
			}
		});
	},
	/**
	 * 设置验证的触发方式
	 * 
	 * @param {jQuery
	 *            object} jq jq对象
	 * @param {number}
	 *            param 触发方式 0为默认方式；1为失去焦点触发
	 */
	setValidateTrigger : function(jq, param) {
		return jq.each(function() {
			var box = $(this);
			var validatebox = $.data(this, "validatebox");
			var that = this;
			switch (param) {
			case 0:
				// 默认方式
				box.unbind(".validatebox").bind(
						"focus.validatebox",
						function() {
							validatebox.validating = true;
							validatebox.value = undefined;
							(function() {
								if (validatebox.validating) {
									if (validatebox.value != box.val()) {
										validatebox.value = box.val();
										if (validatebox.timer) {
											clearTimeout(validatebox.timer);
										}
										validatebox.timer = setTimeout(
												function() {
													$(that).validatebox(
															"validate");
												}, validatebox.options.delay);
									} else {
										$(that).validatebox('showTip');
									}
									setTimeout(arguments.callee, 200);
								}
							})();
						}).bind("blur.validatebox", function() {
					if (validatebox.timer) {
						clearTimeout(validatebox.timer);
						validatebox.timer = undefined;
					}
					validatebox.validating = false;
					$(that).validatebox('removeTip');
				}).bind("mouseenter.validatebox", function() {
					if (box.hasClass("validatebox-invalid")) {
						$(that).validatebox('createTipToShow');
					}
				}).bind("mouseleave.validatebox", function() {
					if (!validatebox.validating) {
						$(that).validatebox('removeTip');
					}
				});
				break;
			case 1:
				// blur时触发
				box.unbind(".validatebox").bind("focus.validatebox",
						function() {
							validatebox.validating = false;
							$(that).removeClass("validatebox-invalid");
							$(that).validatebox('removeTip');
						}).bind("blur.validatebox", function() {
					validatebox.validating = true;
					if (validatebox.timer) {
						clearTimeout(validatebox.timer);
						validatebox.timer = undefined;
					}
					validatebox.timer = setTimeout(function() {
						$(that).validatebox("validate");
					}, validatebox.options.delay);
				}).bind("mouseenter.validatebox", function() {
					if (box.hasClass("validatebox-invalid")) {
						$(that).validatebox('createTipToShow');
					}
				});
				break;
			default:
				break;
			}
		});
	}
});

var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X
function IdCardValidate(idCard) {
	idCard = trim(idCard.replace(/ /g, ""));
	if (idCard.length == 15) {
		return isValidityBrithBy15IdCard(idCard);
	} else if (idCard.length == 18) {
		var a_idCard = idCard.split("");// 得到身份证数组
		if (isValidityBrithBy18IdCard(idCard)
				&& isTrueValidateCodeBy18IdCard(a_idCard)) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}
/**
 * 判断身份证号码为18位时最后的验证位是否正确
 * 
 * @param a_idCard
 *            身份证号码数组
 * @return
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
	var sum = 0; // 声明加权求和变量
	if (a_idCard[17].toLowerCase() == 'x') {
		a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
	}
	for (var i = 0; i < 17; i++) {
		sum += Wi[i] * a_idCard[i];// 加权求和
	}
	valCodePosition = sum % 11;// 得到验证码所位置
	if (a_idCard[17] == ValideCode[valCodePosition]) {
		return true;
	} else {
		return false;
	}
}

/**
 * 通过身份证判断是男是女
 * 
 * @param idCard
 *            15/18位身份证号码
 * @return 'female'-女、'male'-男
 */
function maleOrFemalByIdCard(idCard) {
	idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。
	if (idCard.length == 15) {
		if (idCard.substring(14, 15) % 2 == 0) {
			return 'female';
		} else {
			return 'male';
		}
	} else if (idCard.length == 18) {
		if (idCard.substring(14, 17) % 2 == 0) {
			return 'female';
		} else {
			return 'male';
		}
	} else {
		return null;
	}
}

/**
 * 验证18位数身份证号码中的生日是否是有效生日
 * 
 * @param idCard
 *            18位书身份证字符串
 * @return
 */
function isValidityBrithBy18IdCard(idCard18) {
	var year = idCard18.substring(6, 10);
	var month = idCard18.substring(10, 12);
	var day = idCard18.substring(12, 14);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	// 这里用getFullYear()获取年份，避免千年虫问题
	if (temp_date.getFullYear() != parseFloat(year)
			|| temp_date.getMonth() != parseFloat(month) - 1
			|| temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}
/**
 * 验证15位数身份证号码中的生日是否是有效生日
 * 
 * @param idCard15
 *            15位书身份证字符串
 * @return
 */
function isValidityBrithBy15IdCard(idCard15) {
	var year = idCard15.substring(6, 8);
	var month = idCard15.substring(8, 10);
	var day = idCard15.substring(10, 12);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
	if (temp_date.getYear() != parseFloat(year)
			|| temp_date.getMonth() != parseFloat(month) - 1
			|| temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}
// 去掉字符串头尾空格
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.getBytes = function() {      
    var cArr = this.match(/[^\x00-\xff]/ig);      
    return this.length + (cArr == null ? 0 : cArr.length);   
} 
