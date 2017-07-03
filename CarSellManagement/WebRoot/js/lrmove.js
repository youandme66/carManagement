
function move(a,b)
{	
	var s=document.getElementById(a);
	var t=document.getElementById(b);
	for(var i=0;i<s.length;i++)
	{
	 	if(s[i].selected)
	 	{
	    	var op=new Option(s[i].text);
	    	op.value=s[i].value;
	    	s.remove(i);
	    	t.add(op);
			i--;
	  	}
	}
	var right = document.getElementById("right");
	var option = right.childNodes;
	var val = "";
	var authority_id = document.getElementById("authority_id");
	for(var i=1;i<option.length;i++){
		val+=option[i].value+",";
	}
	authority_id.value=val;
}

function selectedAll()
{
	var right = document.getElementById("right");
	for(var i = 0; i < right.length; i++)
	{
		right[i].selected = true;
	}

}