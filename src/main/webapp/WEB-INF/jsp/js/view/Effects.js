function fadeOut($element) {
    // $element.children(".tools").empty();
    $element.animate({
        width: "+=0",
    },100, function () {
        $element.animate({
            height: 0,
            marginTop : 0,
            marginBottom : 0,
            paddingTop: 0,
            paddingBottom: 0,
            border: 0,
            opacity: - 5
            // left: "+=50",
        },400, function () {
            console.log("afterFadeOutCall")
            $element.hide();
    });

    });
}