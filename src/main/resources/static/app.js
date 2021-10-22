var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/ticker', function (ticker) {
            var json = JSON.parse(ticker.body)
            showGreeting(json.datetime + ": " + json.code + ", " + json.price);
        });

        stompClient.subscribe('/user/queue/position-updates', function (updates) {
            var json = JSON.parse(updates.body)
            showGreeting2("stock updates: " + ": " + json.code + ", " + json.price);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/ticker", {}, JSON.stringify({'code': $("#name").val()}));
}

function updateStocks() {
    stompClient.send("/app/stock-updates");
}

function personalMessage() {
    stompClient.send("/app/principal-message")
}

function showGreeting(message) {
    $("#greetings").html("<tr><td>" + message + "</td></tr>");
}

function showGreeting2(message) {
    $("#greetings2").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#update" ).click(function() { updateStocks(); });
    $( "#message" ).click(function() { personalMessage(); });
});
