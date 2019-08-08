function restructureJson(obj){
	var object = JSON.parse(obj);
	
	//convert date to timestamp
	//var openedAt = object.mand.openedAt;
	//var date = new Date(openedAt);
	//object.mand.openedAt = date.getTime();
	return JSON.stringify(object);	
}

restructureJson(obj);

