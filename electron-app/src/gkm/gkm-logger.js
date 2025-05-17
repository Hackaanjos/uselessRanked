const gkmListener = require('./gkm-listener');
const KeyboardEvent = require('../models/keyboard-event');


function initialize() {
    gkmListener.events.on('keyboard', async function (keyboardEventData) {
        if (keyboardEventData.eventType !== 'RELEASED') return;
        
        await KeyboardEvent.query().insert({
            keyCode: keyboardEventData.keyCode,
        });
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
