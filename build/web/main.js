function betoltA(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltA"},
        success:function(valasz){
            var adatokDiv = document.getElementById("akacmez");
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

function betoltH(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltH"},
        success:function(valasz){
            var adatokDiv = document.getElementById("harsmez");
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

function betoltN(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltN"},
        success:function(valasz){
            var adatokDiv = document.getElementById("nyarivirag");
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

function betoltT(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltT"},
        success:function(valasz){
            var adatokDiv = document.getElementById("tavaszivirag");
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

function betoltR(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltR"},
        success:function(valasz){
            var adatokDiv = document.getElementById("repcemez");
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

function betoltF(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltF"},
        success:function(valasz){
            var adatokDiv = document.getElementById("nap");
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

function betoltD(){
    $.ajax({
        url:"Controller",
        type:"post",
        data:{"task":"betoltD"},
        success:function(valasz){
            var adatokDiv = document.getElementById("disz");
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
