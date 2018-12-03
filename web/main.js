function betolt(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{},
        success:function(valasz){
            var adatokDiv = document.getElementById("adatok");
            for(var i = 0; i < valasz.length; i++){
                adatokDiv.innerHTML += "<div>";
//                    adatokDiv.innerHTML += "<span>" + valasz[i].id + "</span>";
//                    adatokDiv.innerHTML += "<span>" + valasz[i].nev + "</span>";
                    adatokDiv.innerHTML += "<span>" + valasz[i].mennyiseg + "</span>";
                    adatokDiv.innerHTML += "<span>" + valasz[i].ara + "</span>";
                adatokDiv.innerHTML += "</div>";
            }
            
        },
        error:function(){alert("hiba")}
    });
}


