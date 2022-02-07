<%-- 
    Document   : CovidMail
    Created on : 6 Feb, 2022, 9:50:05 AM
    Author     : sarav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>COVID</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
     <script type="text/javascript">
         while(true){
             if()
         }
        let statesData = ''
        
        let date = new Date()
        const months = ["Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"];

        let day = date.getDate()
        let month = months[date.getMonth()]
        let year = date.getFullYear()
        let Today = (day+"-"+month+"-"+year)
         statesData = "<h1>"+Today+"</h1>"
	fetch("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=India", {
	"method": "GET",
	"headers": {
		"x-rapidapi-host": "covid-19-coronavirus-statistics.p.rapidapi.com",
		"x-rapidapi-key": "0efa0ee6c4msh4964e75bed1718ep1964d7jsn47545e7d43b1"
	}
})
.then(response => response.json())
.then(response => {
	console.log(response);
	let states = response.data.covid19Stats;
	// alert(typeof(states))
	$('#head').append("<label> COUNRTY : "+states[0].country+"</label>");
	$('#head').append("<label> TIME : "+states[0].lastUpdate+"</label>");
	states.forEach(element => {
		let country = element.country;
		let state = element.province;
		if(state === null){
			state="";
		}else{
			state = state+" => "
		}
		let confirmed = element.confirmed;
		let deaths = element.deaths;
		let a = '<fieldset><legend >'+state+' '+country+'</legend><form><label>confirmed : <input style="height: 25px;font-size: 23px;font-weight:bold;color: blueviolet;" class="con" type="text" value='+confirmed+' disabled></label><br><br> <label>deaths     :    <input style="height: 25px;font-size: 23px;font-weight:bold;color: blueviolet;" class="dea" type="text" value='+deaths+' disabled></label>    </form></fieldset>'
                statesData+=a;
//		$('body').append(a);
		$('#datum').append(a);
                
	});
        callServlet()
	
})
.catch(err => {
	alert("ERROR") 	
	console.error(err);
});


function callServlet()
  {
      $.ajax({
	type: "POST",
	url: "Getcoviddata",
	data: {covid:statesData},
	success: function (response) {
		alert(response);
	}
});
  }
  
</script>
    <style type="text/css">
        body{
            font-size: 25px;
        }
        input{
            height: 25px;
            font-size: 23px;
            font-weight: bold;
            color: blue;
        }
        #head{
            display: flex;
            background-color: burlywood;
            justify-content: space-between;
            padding: 10px;
        }
    </style>
</head>
<body >
<div id="head">
</div>
    <div id="datum" >
        
    </div>

</body>
</html>
