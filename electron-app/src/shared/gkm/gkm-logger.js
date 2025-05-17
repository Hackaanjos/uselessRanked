const gkmListener = require('./gkm-listener');

function initialize() {
    gkmListener.events.on('keyboard', function (keyboardEvent) {
        console.log('Keyboard event:', keyboardEvent);
    });

    gkmListener.events.on('mouse', function (mouseEvent) {
        console.log('Mouse event:', mouseEvent);
    });

    gkmListener.events.on('mousewheel', function (mousewheelEvent) {
        console.log('Mousewheel event:', mousewheelEvent);
    });
}

module.exports = {
    initialize
};
