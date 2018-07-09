function parseIdcard(idcard){
	var isRight=IdCardValidate(idcard);
	if(isRight){
		var n=0;
		if(idcard.length==18){
			n=2;
		}
		var obj={
			province:idcard.substring(0,2),
			city:idcard.substring(2,4),
			county:idcard.substring(4,6),
			year:idcard.substring(6,8+n),
			month:idcard.substring(8+n,10+n),
			day:idcard.substring(10+n,12+n),
			number:idcard.substring(12+n,14+n),
			sex:idcard.substring(14+n,15+n),
			flag:n==2?idcard.substring(17,18):null
		}
		return obj;
	}else{
		return {};
	}
	
}