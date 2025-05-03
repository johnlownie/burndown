function notify (type, icon, title, message, container, timer) {
    $.niftyNoty({
        type: type,
        icon: "fa fa-lg fa-" + icon,
        title: title,
        message: message,
        container: container,
        timer: timer
    });
}

function notifyError() {
    notify ( "#content-container" );
}

function notifyError(container) {
    notify ( "danger", "exclamation", "Server Error", "The response from the server timed out. Please try again later.", container, 8000 );
}

function notifyObject (notification) {
    notify (notification.type, notification.icon, notification.title, notification.message, notification.container, notification.timer );
}
