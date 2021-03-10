function fadeOut($element) {
    // $element.children(".tools").empty();
    $element.animate({
        height: 0,
        marginTop : 0,
        marginBottom : 0,
        paddingTop: 0,
        paddingBottom: 0,
        border: 0,
        opacity: -3,
    },400, function () {
        $element.hide()
    });
}