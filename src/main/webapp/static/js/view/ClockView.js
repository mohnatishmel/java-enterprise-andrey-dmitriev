function showTime(){
    let date = new Date();
    let h = date.getHours(); // 0 - 23
    let m = date.getMinutes(); // 0 - 59
    // var s = date.getSeconds(); // 0 - 59
    let session = "am";

    if(h === 0){
        h = 12;
    }

    if(h > 12){
        h = h - 12;
        session = "pm";
    }

    h = (h < 10) ? "0" + h : h;
    m = (m < 10) ? "0" + m : m;
    // s = (s < 10) ? "0" + s : s;

    let time = h + ":" + m + " " + session;
    // var time = h + ":" + m + ":" + s + " " + session;
    $(".clock-container").text(time);
    // document.getElementById("clock").innerText = time;
    // document.getElementById("clock").textContent = time;

    setTimeout(showTime, 1000);

}