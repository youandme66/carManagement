function checkAll(){
			var obj = document.getElementsByName('dels');
			for(i=0;i<obj.length;i++){
				if(obj[i].checked){
					obj[i].checked=false;
				}else{
					obj[i].checked=true;
				}
			}
			record();
		}
		function record(){
			var obj = document.getElementsByName('dels');
			var str = "";
			for(i=0;i<obj.length;i++){
				if(obj[i].checked){
					str+=obj[i].value+",";
				}else{
				
				}
			}
			document.getElementById('checked').value=str;
		}